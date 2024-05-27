package com.ruoyi.app.service.impl;

import cn.hutool.core.date.DateUtil;
import com.alibaba.fastjson2.JSONObject;
import com.ruoyi.common.utils.DateUtils;
import com.ruoyi.common.utils.StringUtils;
import com.ruoyi.app.domain.WxClickLog;
import com.ruoyi.app.mapper.WxClickLogMapper;
import com.ruoyi.app.mapper.WxClickServiceLogMapper;
import com.ruoyi.app.service.IWxClickLogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * 小程序使用日志Service业务层处理
 *
 * @author ruoyi
 * @date 2024-04-28
 */
@Service
public class WxClickLogServiceImpl implements IWxClickLogService
{
    @Autowired
    private WxClickLogMapper wxClickLogMapper;

    @Autowired
    private WxClickServiceLogMapper wxClickServiceLogMapper;

    /**
     * 查询小程序使用日志
     *
     * @param id 小程序使用日志主键
     * @return 小程序使用日志
     */
    @Override
    public WxClickLog selectWxClickLogById(Long id)
    {
        return wxClickLogMapper.selectWxClickLogById(id);
    }

    /**
     * 查询小程序使用日志列表
     *
     * @param wxClickLog 小程序使用日志
     * @return 小程序使用日志
     */
    @Override
    public List<WxClickLog> selectWxClickLogList(WxClickLog wxClickLog)
    {
        return wxClickLogMapper.selectWxClickLogList(wxClickLog);
    }

    /**
     * 新增小程序使用日志
     *
     * @param wxClickLog 小程序使用日志
     * @return 结果
     */
    @Override
    public int insertWxClickLog(WxClickLog wxClickLog)
    {
        wxClickLog.setCreateTime(DateUtils.getNowDate());
        return wxClickLogMapper.insertWxClickLog(wxClickLog);
    }

    /**
     * 修改小程序使用日志
     *
     * @param wxClickLog 小程序使用日志
     * @return 结果
     */
    @Override
    public int updateWxClickLog(WxClickLog wxClickLog)
    {
        wxClickLog.setUpdateTime(DateUtils.getNowDate());
        return wxClickLogMapper.updateWxClickLog(wxClickLog);
    }

    /**
     * 批量删除小程序使用日志
     *
     * @param ids 需要删除的小程序使用日志主键
     * @return 结果
     */
    @Override
    public int deleteWxClickLogByIds(Long[] ids)
    {
        return wxClickLogMapper.deleteWxClickLogByIds(ids);
    }

    /**
     * 删除小程序使用日志信息
     *
     * @param id 小程序使用日志主键
     * @return 结果
     */
    @Override
    public int deleteWxClickLogById(Long id)
    {
        return wxClickLogMapper.deleteWxClickLogById(id);
    }

    @Override
    public JSONObject trendCountByDay(String time,String serviceId){
        JSONObject result = new JSONObject();

        if(StringUtils.isEmpty(time)){
            time =  DateUtil.format(DateUtils.getNowDate(), "yyyy-MM-dd");
        }

        //pv
        List<JSONObject> pvList;
        if(StringUtils.isEmpty(serviceId)){
            pvList = wxClickLogMapper.trendCountByDay("pv",time);
        }else {
            pvList = wxClickServiceLogMapper.trendCountByDayByServiceId("pv",serviceId,time);
        }
        result.put("pv",pvList);

        //uv
        List<JSONObject> uvList;
        if(StringUtils.isEmpty(serviceId)){
            uvList = wxClickLogMapper.trendCountByDay("uv",time);
        }else {
            uvList = wxClickServiceLogMapper.trendCountByDayByServiceId("uv",serviceId,time);
        }
        result.put("uv",uvList);

        return result;
    }

    @Override
    public JSONObject trendCountByMonth(String time,String serviceId){
        JSONObject result = new JSONObject();

        if(StringUtils.isEmpty(time)){
            time =  DateUtil.format(DateUtils.getNowDate(), "yyyy-MM");
        }

        //pv
        List<JSONObject> pvList;
        if(StringUtils.isEmpty(serviceId)){
            pvList = wxClickLogMapper.trendCountByMonth("pv",time);
        }else {
            pvList = wxClickServiceLogMapper.trendCountByMonthByServiceId("pv",serviceId,time);
        }
        pvList =  handleTrendMonth(time,pvList);
        result.put("pv",pvList);

        //uv
        List<JSONObject> uvList;
        if(StringUtils.isEmpty(serviceId)){
            uvList = wxClickLogMapper.trendCountByMonth("uv",time);
        }else {
            uvList = wxClickServiceLogMapper.trendCountByMonthByServiceId("uv",serviceId,time);
        }
        uvList =  handleTrendMonth(time,uvList);
        result.put("uv",uvList);


        return result;
    }

    public List<JSONObject> handleTrendMonth(String time,List<JSONObject> list) {
        int endDay = DateUtil.endOfMonth(DateUtil.parse(time, "yyyy-MM")).getDate();
        List<JSONObject> result = new ArrayList<>();
        for (JSONObject o : list) {
            int day = o.getInteger("time");
            if(day <= endDay){
                result.add(o);
            }
        }
        return result;
    }

    @Override
    public JSONObject trendCountByYear(String time, String serviceId){
        JSONObject result = new JSONObject();

        if(StringUtils.isEmpty(time)){
            time =  DateUtil.format(DateUtils.getNowDate(), "yyyy");
        }

        //pv
        List<JSONObject> pvList;
        if(StringUtils.isEmpty(serviceId)){
            pvList = wxClickLogMapper.trendCountByYear("pv",time);
        }else {
            pvList = wxClickServiceLogMapper.trendCountByYearByServiceId("pv",serviceId,time);
        }
        result.put("pv",pvList);

        //uv
        List<JSONObject> uvList;
        if(StringUtils.isEmpty(serviceId)){
            uvList = wxClickLogMapper.trendCountByYear("uv",time);
        }else {
            uvList = wxClickServiceLogMapper.trendCountByYearByServiceId("uv",serviceId,time);
        }
        result.put("uv",uvList);

        return result;
    }

    @Override
    public JSONObject staticsCountByServiceId(String serviceId) {
        JSONObject result = new JSONObject();
        //获取当前时间天、月、年
        String day = DateUtil.format(DateUtils.getNowDate(), "yyyy-MM-dd");
        String month = DateUtil.format(DateUtils.getNowDate(), "yyyy-MM");
        String year = DateUtil.format(DateUtils.getNowDate(), "yyyy");

        //累计
        JSONObject allResult;
        if(StringUtils.isEmpty(serviceId)){
            allResult = wxClickLogMapper.staticsCountByAll();
        }else {
            allResult = wxClickServiceLogMapper.staticsCountByAllByServiceId(serviceId);
        }
        result.put("all",allResult);

        //按天
        JSONObject dayResult;
        if(StringUtils.isEmpty(serviceId)){
            dayResult = wxClickLogMapper.staticsCountByDay(day);
        }else {
            dayResult = wxClickServiceLogMapper.staticsCountByDayByServiceId(serviceId,day);
        }
        result.put("day",dayResult);

        //按月
        JSONObject monthResult;
        if(StringUtils.isEmpty(serviceId)){
            monthResult = wxClickLogMapper.staticsCountByMonth(month);
        }else {
            monthResult = wxClickServiceLogMapper.staticsCountByMonthByServiceId(serviceId,month);
        }
        result.put("month",monthResult);

        //按年
        JSONObject yearResult;
        if(StringUtils.isEmpty(serviceId)){
            yearResult = wxClickLogMapper.staticsCountByYear(year);
        }else {
            yearResult = wxClickServiceLogMapper.staticsCountByYearByServiceId(serviceId,year);
        }
        result.put("year",yearResult);

        return result;
    }

    @Override
    public JSONObject staticsCount() {
        JSONObject result = new JSONObject();
        //获取当前时间天、月、年
        String day = DateUtil.format(DateUtils.getNowDate(), "yyyy-MM-dd");
        String month = DateUtil.format(DateUtils.getNowDate(), "yyyy-MM");
        String year = DateUtil.format(DateUtils.getNowDate(), "yyyy");

        //累计
        List<JSONObject> allResult = new ArrayList<>();
        allResult.add(wxClickLogMapper.staticsCountByAll());
        allResult.addAll(wxClickServiceLogMapper.staticsCountByAll());
        result.put("all",allResult);

        //按天
        List<JSONObject> dayResult = new ArrayList<>();
        dayResult.add(wxClickLogMapper.staticsCountByDay(day));
        dayResult.addAll(wxClickServiceLogMapper.staticsCountByDay(day));
        result.put("day",dayResult);

        //按月
        List<JSONObject> monthResult = new ArrayList<>();
        monthResult.add(wxClickLogMapper.staticsCountByMonth(month));
        monthResult.addAll(wxClickServiceLogMapper.staticsCountByMonth(month));
        result.put("month",monthResult);

        //按年
        List<JSONObject> yearResult = new ArrayList<>();
        yearResult.add(wxClickLogMapper.staticsCountByYear(year));
        yearResult.addAll(wxClickServiceLogMapper.staticsCountByYear(year));
        result.put("year",yearResult);

        return result;
    }

}
