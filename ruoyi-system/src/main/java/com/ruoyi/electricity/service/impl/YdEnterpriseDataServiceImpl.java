package com.ruoyi.electricity.service.impl;

import com.alibaba.fastjson2.JSONObject;
import com.ruoyi.common.exception.ServiceException;
import com.ruoyi.common.utils.DateUtils;
import com.ruoyi.common.utils.StringUtils;
import com.ruoyi.electricity.domain.YdEnterpriseData;
import com.ruoyi.electricity.mapper.YdEnterpriseDataMapper;
import com.ruoyi.electricity.service.IYdEnterpriseDataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
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
    public List<Long> importData(List<YdEnterpriseData> dataList) throws ParseException {
        List<Long> updateList = new ArrayList<>();

        if (StringUtils.isNull(dataList) || dataList.isEmpty()) {
            throw new ServiceException("导入用户数据不能为空！");
        }
        for (YdEnterpriseData ydEnterpriseData : dataList) {
            String meterNumber = ydEnterpriseData.getMeterNumber();
            String totalActivePower = ydEnterpriseData.getTotalActivePower();

            //过滤无效数据
            if (StringUtils.isEmpty(meterNumber) || StringUtils.isEmpty(totalActivePower)) {
                continue;
            }

            String dataDate = ydEnterpriseData.getDataDate1();
            String dataTime = ydEnterpriseData.getDataTime();
            ydEnterpriseData.setDataDate(formatter1.parse(dataDate));
            String time = dataDate + " " + dataTime;
            // 拼接时间
            ydEnterpriseData.setFullTime(formatter.parse(time));

            //更新或者插入数据
            Long i = ydEnterpriseDataMapper.selectByParam(meterNumber, dataDate, dataTime);
            if (i != null) {
                updateList.add(i);
                // 更新
                ydEnterpriseData.setUpdateTime(new Date());
                int a = ydEnterpriseDataMapper.updateData(ydEnterpriseData);
            } else {
                ydEnterpriseData.setCreateTime(new Date());
                ydEnterpriseData.setUpdateTime(new Date());
                int j = ydEnterpriseDataMapper.insertYdEnterpriseData(ydEnterpriseData);
            }
        }

        return updateList;
    }

    @Override
    public List<JSONObject> getEleCount(String meterNumber, String flag) {
        // 查询最近的一天
        String time = ydEnterpriseDataMapper.getNewDay(meterNumber);
        // 天
        if ("0".equals(flag)) {
            return ydEnterpriseDataMapper.selectData(time, meterNumber);
        } else {
            return ydEnterpriseDataMapper.selectMonthData(time, meterNumber);
        }
    }

    @Override
    public String getMaxUpdateTime() {
        return ydEnterpriseDataMapper.getMaxUpdateTime();
    }
}
