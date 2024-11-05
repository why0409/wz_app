package com.ruoyi.electricity.service.impl;

import com.alibaba.fastjson2.JSONObject;
import com.ruoyi.common.core.domain.entity.SysUser;
import com.ruoyi.common.exception.ServiceException;
import com.ruoyi.common.utils.DateUtils;
import com.ruoyi.common.utils.SecurityUtils;
import com.ruoyi.common.utils.StringUtils;
import com.ruoyi.common.utils.bean.BeanValidators;
import com.ruoyi.electricity.domain.YdEnterpriseData;
import com.ruoyi.electricity.mapper.YdEnterpriseDataMapper;
import com.ruoyi.electricity.service.IYdEnterpriseDataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;

/**
 * 用电企业数据Service业务层处理
 *
 * @author ruoyi
 * @date 2024-11-04
 */
@Service
public class YdEnterpriseDataServiceImpl implements IYdEnterpriseDataService {
    @Autowired
    private YdEnterpriseDataMapper ydEnterpriseDataMapper;

    /**
     * 查询用电企业数据
     *
     * @param id 用电企业数据主键
     * @return 用电企业数据
     */
    @Override
    public YdEnterpriseData selectYdEnterpriseDataById(Long id) {
        return ydEnterpriseDataMapper.selectYdEnterpriseDataById(id);
    }

    /**
     * 查询用电企业数据列表
     *
     * @param ydEnterpriseData 用电企业数据
     * @return 用电企业数据
     */
    @Override
    public List<YdEnterpriseData> selectYdEnterpriseDataList(YdEnterpriseData ydEnterpriseData) {
        return ydEnterpriseDataMapper.selectYdEnterpriseDataList(ydEnterpriseData);
    }

    /**
     * 新增用电企业数据
     *
     * @param ydEnterpriseData 用电企业数据
     * @return 结果
     */
    @Override
    public int insertYdEnterpriseData(YdEnterpriseData ydEnterpriseData) {
        ydEnterpriseData.setCreateTime(DateUtils.getNowDate());
        return ydEnterpriseDataMapper.insertYdEnterpriseData(ydEnterpriseData);
    }

    /**
     * 修改用电企业数据
     *
     * @param ydEnterpriseData 用电企业数据
     * @return 结果
     */
    @Override
    public int updateYdEnterpriseData(YdEnterpriseData ydEnterpriseData) {
        ydEnterpriseData.setUpdateTime(DateUtils.getNowDate());
        return ydEnterpriseDataMapper.updateYdEnterpriseData(ydEnterpriseData);
    }

    /**
     * 批量删除用电企业数据
     *
     * @param ids 需要删除的用电企业数据主键
     * @return 结果
     */
    @Override
    public int deleteYdEnterpriseDataByIds(Long[] ids) {
        return ydEnterpriseDataMapper.deleteYdEnterpriseDataByIds(ids);
    }

    /**
     * 删除用电企业数据信息
     *
     * @param id 用电企业数据主键
     * @return 结果
     */
    @Override
    public int deleteYdEnterpriseDataById(Long id) {
        return ydEnterpriseDataMapper.deleteYdEnterpriseDataById(id);
    }

    SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm");
    SimpleDateFormat formatter1 = new SimpleDateFormat("yyyy-MM-dd");

    @Override
    public int importData(List<YdEnterpriseData> dataList) throws ParseException {
        if (StringUtils.isNull(dataList) || dataList.isEmpty()) {
            throw new ServiceException("导入用户数据不能为空！");
        }
        for (YdEnterpriseData ydEnterpriseData : dataList) {
            // 检验如果是同一个电表，同一时间段更新操作
            String meterNumber = ydEnterpriseData.getMeterNumber();
            String dataDate = ydEnterpriseData.getDataDate1();
            String dataTime = ydEnterpriseData.getDataTime();

            ydEnterpriseData.setDataDate(formatter1.parse(dataDate));
            String time = dataDate + " " + dataTime;
            // 拼接时间
            ydEnterpriseData.setFullTime(formatter.parse(time));
            int i = ydEnterpriseDataMapper.selectByParam(meterNumber, dataDate, dataTime);
            if (i > 0) {
                // 更新
                int a = ydEnterpriseDataMapper.updateData(ydEnterpriseData);
            } else {
                int j = ydEnterpriseDataMapper.insertYdEnterpriseData(ydEnterpriseData);
            }
            // int j = ydEnterpriseDataMapper.insertYdEnterpriseData(ydEnterpriseData);
        }
        return 1;
    }

    @Override
    public List<JSONObject> getEleCount(String meterNumber, String flag) {
        // 天
        if ("0".equals(flag)) {
            // 查询最近的一天
            String time = ydEnterpriseDataMapper.getNewDay();
            return ydEnterpriseDataMapper.selectData(time, meterNumber);
        } else {
            return ydEnterpriseDataMapper.selectMonthData(meterNumber);
        }
    }
}
