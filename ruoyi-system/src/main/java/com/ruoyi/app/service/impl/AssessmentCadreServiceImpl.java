package com.ruoyi.app.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import com.ruoyi.app.domain.AssessmentCadre;
import com.ruoyi.app.mapper.AssessmentCadreMapper;
import com.ruoyi.app.service.IAssessmentCadreService;
import com.ruoyi.common.utils.DateUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional; // 1. 导入事务注解
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.ruoyi.common.utils.StringUtils;

/**
 * 被测评干部Service业务层处理
 * * @author wanghongyu
 *
 * @date 2025-11-04
 */
@Service
public class AssessmentCadreServiceImpl extends ServiceImpl<AssessmentCadreMapper, AssessmentCadre> implements IAssessmentCadreService {
    /**
     * 查询被测评干部列表
     * * @param assessmentCadre 被测评干部
     *
     * @return 被测评干部
     */
    @Override
    public List<AssessmentCadre> selectAssessmentCadreList(AssessmentCadre assessmentCadre) {
        LambdaQueryWrapper<AssessmentCadre> lqw = new LambdaQueryWrapper<AssessmentCadre>();
        lqw.like(StringUtils.isNotEmpty(assessmentCadre.getCadreName()), AssessmentCadre::getCadreName, assessmentCadre.getCadreName());
        lqw.like(StringUtils.isNotEmpty(assessmentCadre.getUnitName()), AssessmentCadre::getUnitName, assessmentCadre.getUnitName());
        lqw.like(StringUtils.isNotEmpty(assessmentCadre.getPostTitle()), AssessmentCadre::getPostTitle, assessmentCadre.getPostTitle());
        lqw.eq(StringUtils.isNotEmpty(assessmentCadre.getStatus()), AssessmentCadre::getStatus, assessmentCadre.getStatus());

        // --- 修改说明 ---
        // 1. 请在 AssessmentCadre 实体类和数据库中添加 Integer sortNum 字段
        // 2. 取消下方注释，改为按 sortNum 排序，这样才能在更新模式下保证和 Excel 顺序一致
        lqw.orderByAsc(AssessmentCadre::getSortNum);

        // 如果还没有添加字段，暂时保留按 ID 排序（但在更新模式下顺序可能会乱）
//        lqw.orderByAsc(AssessmentCadre::getCadreId);

        return this.list(lqw);
    }

    /**
     * 导入干部数据
     */
    @Override
    @Transactional(rollbackFor = Exception.class) // 2. 添加事务，确保 删除+插入 要么都成功，要么都回滚
    public String importCadres(List<AssessmentCadre> cadreList, boolean updateSupport, String operName) {
        if (StringUtils.isNull(cadreList) || cadreList.isEmpty()) {
            return "导入数据不能为空！";
        }

        try {
            // 1. 获取数据库中所有现有的干部数据 (用于比对 ID)
            List<AssessmentCadre> dbList = this.list();

            // 建立 ID Card -> ID 的映射
            Map<String, Long> idCardMap = dbList.stream()
                    .filter(c -> StringUtils.isNotEmpty(c.getIdCard()))
                    .collect(Collectors.toMap(AssessmentCadre::getIdCard, AssessmentCadre::getCadreId, (k1, k2) -> k1));

            // 建立 Name + Unit -> ID 的映射 (作为兜底)
            // 如果有重名的，toMap 的第三个参数保证取已有的
            Map<String, Long> existMap = dbList.stream().collect(Collectors.toMap(
                    c -> c.getCadreName() + "_" + c.getUnitName(),
                    AssessmentCadre::getCadreId,
                    (oldVal, newVal) -> oldVal
            ));

            // 用于记录本次导入涉及到的 ID集合 (用于后续判断哪些旧数据需要删除)
            List<Long> involvedIds = new ArrayList<>();

            // 2. 遍历导入列表
            int sortIndex = 1;
            int updateCount = 0;
            int insertCount = 0;

            for (AssessmentCadre cadre : cadreList) {
                // 设置基础字段
                cadre.setCreateBy(operName);
                if (StringUtils.isEmpty(cadre.getStatus())) {
                    cadre.setStatus("0");
                }

                // 设置排序号
                cadre.setSortNum(sortIndex++);

                // 优先尝试匹配身份证号
                Long existId = null;
                if (StringUtils.isNotEmpty(cadre.getIdCard())) {
                    existId = idCardMap.get(cadre.getIdCard());
                }

                // 如果没匹配到，尝试匹配 姓名 + 单位
                if (existId == null) {
                    String key = cadre.getCadreName() + "_" + cadre.getUnitName();
                    existId = existMap.get(key);
                }

                if (existId != null) {
                    // --- 关键逻辑：匹配到了，复用旧 ID ---
                    cadre.setCadreId(existId);
                    cadre.setUpdateBy(operName);
                    cadre.setUpdateTime(DateUtils.getNowDate());

                    // 记录这个 ID 被用到了
                    involvedIds.add(existId);
                    updateCount++;
                } else {
                    // --- 没匹配到，这是纯新增数据 ---
                    cadre.setCreateTime(DateUtils.getNowDate());
                    insertCount++;
                }
            }

            // 3. 批量保存或更新
            // MyBatis-Plus 会根据是否有 ID 自动判断是 UPDATE 还是 INSERT
            this.saveOrUpdateBatch(cadreList);

            // 4. 处理删除逻辑："删除表格中不存在的人员信息"
            if (updateSupport) {
                // 找出所有在 DB 中存在，但本次导入没有涉及到的 ID (即表格里删掉的人)
                List<Long> deleteIds = dbList.stream()
                        .map(AssessmentCadre::getCadreId)
                        .filter(id -> !involvedIds.contains(id))
                        .collect(Collectors.toList());

                if (!deleteIds.isEmpty()) {
                    this.removeByIds(deleteIds);
                }
                return "导入成功";
//                return "导入成功：更新 " + updateCount + " 条，新增 " + insertCount + " 条，删除 " + deleteIds.size() + " 条冗余数据";
            }

            return "导入成功：更新 " + updateCount + " 条，新增 " + insertCount + " 条";

        } catch (Exception e) {
            throw new RuntimeException("导入失败：" + e.getMessage());
        }
    }
}