package com.ruoyi.activities.service.impl;

import cn.hutool.core.date.DateUtil;
import com.alibaba.fastjson2.JSONObject;
import com.ruoyi.activities.domain.ActivitiesInfo;
import com.ruoyi.activities.domain.HomestayRegisteredInfo;
import com.ruoyi.activities.domain.vo.HomestayRegisteredInfoUseVo;
import com.ruoyi.activities.domain.vo.HomestayRegisteredInfoVo;
import com.ruoyi.activities.domain.vo.RegisteredInfoVoByGovernment;
import com.ruoyi.activities.mapper.ActivitiesInfoMapper;
import com.ruoyi.activities.mapper.HomestayRegisteredInfoMapper;
import com.ruoyi.activities.service.IHomestayRegisteredInfoService;
import com.ruoyi.common.utils.DateUtils;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 民宿体验季活动Service业务层处理
 *
 * @author ruoyi
 * @date 2024-06-19
 */
@Service
public class HomestayRegisteredInfoServiceImpl implements IHomestayRegisteredInfoService
{
    @Autowired
    private HomestayRegisteredInfoMapper homestayRegisteredInfoMapper;

    @Autowired
    private ActivitiesInfoMapper activitiesInfoMapper;

    /**
     * 查询民宿体验季活动
     *
     * @param id 民宿体验季活动主键
     * @return 民宿体验季活动
     */
    @Override
    public HomestayRegisteredInfo selectHomestayRegisteredInfoById(Long id)
    {
        return homestayRegisteredInfoMapper.selectHomestayRegisteredInfoById(id);
    }

    /**
     * 查询民宿体验季活动列表
     *
     * @param homestayRegisteredInfo 民宿体验季活动
     * @return 民宿体验季活动
     */
    @Override
    public List<HomestayRegisteredInfo> selectHomestayRegisteredInfoList(HomestayRegisteredInfo homestayRegisteredInfo)
    {
        return homestayRegisteredInfoMapper.selectHomestayRegisteredInfoList(homestayRegisteredInfo);
    }

    /**
     * 新增民宿体验季活动
     *
     * @param homestayRegisteredInfo 民宿体验季活动
     * @return 结果
     */
    @Override
    public int insertHomestayRegisteredInfo(HomestayRegisteredInfo homestayRegisteredInfo)
    {
        homestayRegisteredInfo.setCreateTime(DateUtils.getNowDate());
        return homestayRegisteredInfoMapper.insertHomestayRegisteredInfo(homestayRegisteredInfo);
    }

    /**
     * 修改民宿体验季活动
     *
     * @param homestayRegisteredInfo 民宿体验季活动
     * @return 结果
     */
    @Override
    public int updateHomestayRegisteredInfo(HomestayRegisteredInfo homestayRegisteredInfo)
    {
        homestayRegisteredInfo.setUpdateTime(DateUtils.getNowDate());
        return homestayRegisteredInfoMapper.updateHomestayRegisteredInfo(homestayRegisteredInfo);
    }

    /**
     * 批量删除民宿体验季活动
     *
     * @param ids 需要删除的民宿体验季活动主键
     * @return 结果
     */
    @Override
    public int deleteHomestayRegisteredInfoByIds(Long[] ids)
    {
        return homestayRegisteredInfoMapper.deleteHomestayRegisteredInfoByIds(ids);
    }

    /**
     * 删除民宿体验季活动信息
     *
     * @param id 民宿体验季活动主键
     * @return 结果
     */
    @Override
    public int deleteHomestayRegisteredInfoById(Long id)
    {
        return homestayRegisteredInfoMapper.deleteHomestayRegisteredInfoById(id);
    }

    @Override
    public int checkCountByWxPhone(String phone, Long activitiesId){
        return homestayRegisteredInfoMapper.checkCountByWxPhone(phone, activitiesId);
    }

    @Override
    public int checkCountByIdNumber(String idNumber, Long activitiesId){
        return homestayRegisteredInfoMapper.checkCountByIdNumber(idNumber, activitiesId);
    }

    @Override
    public HomestayRegisteredInfo selectHomestayRegisteredInfoByWxPhone(String phone, Long activitiesId){
        return homestayRegisteredInfoMapper.selectHomestayRegisteredInfoByWxPhone(phone, activitiesId);
    }

    @Override
    public HomestayRegisteredInfoVo selectHomestayRegisteredInfoVoById(Long id){
        return homestayRegisteredInfoMapper.selectHomestayRegisteredInfoVoById(id);
    }

    @Override
    public List<HomestayRegisteredInfoVo> selectHomestayRegisteredInfoVoList(HomestayRegisteredInfo homestayRegisteredInfo){
        return homestayRegisteredInfoMapper.selectHomestayRegisteredInfoVoList(homestayRegisteredInfo);
    }

    @Override
    public int updateIsWinByIds(String isWin, String winStatus, List<Long> ids){
        return homestayRegisteredInfoMapper.updateIsWinByIds(isWin, winStatus, ids);
    }

    @Override
    public List<Long> selectNotWinIdsByWxPhones(Long activitiesId, List<String> list){
        return homestayRegisteredInfoMapper.selectNotWinIdsByWxPhones(activitiesId,list);
    }

    @Override
    public List<HomestayRegisteredInfoVo> selectHomestayRegisteredInfoVoByIds(List<Long> ids){
        return homestayRegisteredInfoMapper.selectHomestayRegisteredInfoVoByIds(ids);
    }

    @Override
    public int updateHomestayRegisteredInfoByWxPhone(HomestayRegisteredInfo homestayRegisteredInfo){
        return homestayRegisteredInfoMapper.updateHomestayRegisteredInfoByWxPhone(homestayRegisteredInfo);
    }

    @Override
    public List<HomestayRegisteredInfoUseVo> selectHomestayRegisteredInfoUseVoList(Long homestayId, Long activitiesId){
        return homestayRegisteredInfoMapper.selectHomestayRegisteredInfoUseVoList(homestayId,activitiesId );
    }

    @Override
    public List<ActivitiesInfo> getStaticsCount(){
        List<ActivitiesInfo> activitiesInfoList = activitiesInfoMapper.selectActivitiesInfoList(new ActivitiesInfo());
        for (ActivitiesInfo ac : activitiesInfoList) {
            //设置活动当前状态
            Long registerStartTime = DateUtil.parseDateTime(ac.getRegistrationTime().split(",")[0]).getTime();
            Long registerEndTime = DateUtil.parseDateTime(ac.getRegistrationTime().split(",")[1]).getTime();
            Long releaseStartTime = DateUtil.parseDateTime(ac.getReleaseTime().split(",")[0]).getTime();
            Long releaseEndTime = DateUtil.parseDateTime(ac.getReleaseTime().split(",")[1]).getTime();
            Long activitiesEndTime = DateUtil.parseDateTime(ac.getConsumptionTime().split(",")[1]).getTime();
            if (System.currentTimeMillis() < registerStartTime){
                //活动未开始
                ac.setCurrentStatus("0");
            }else if(registerStartTime  <= System.currentTimeMillis() && System.currentTimeMillis() <= registerEndTime){
                //登记进行中
                ac.setCurrentStatus("1");
            }else if (registerEndTime < System.currentTimeMillis() && System.currentTimeMillis() < releaseStartTime) {
                //登记结束摇号未开始
                ac.setCurrentStatus("2");
            } else if(releaseStartTime <= System.currentTimeMillis() && System.currentTimeMillis() <= releaseEndTime){
                //摇号进行中
                ac.setCurrentStatus("3");
            } else if (releaseEndTime < System.currentTimeMillis() && System.currentTimeMillis() <= activitiesEndTime) {
                //摇号结束
                ac.setCurrentStatus("4");
            }else {
                //活动结束
                ac.setCurrentStatus("5");
            }

            Long activitiesId = ac.getId();

            JSONObject jsonObject = homestayRegisteredInfoMapper.getStaticsCount(activitiesId);
            int totalCount = jsonObject.getInteger("registerCount");

            List<JSONObject> staticsCountListByProvince = homestayRegisteredInfoMapper.getStaticsCountByProvince(activitiesId);
            for (JSONObject j : staticsCountListByProvince) {
                String code =  j.getString("code");
                List<JSONObject> children = new ArrayList<>();
                if ("340000".equals(code)) {
                    int wzCount = homestayRegisteredInfoMapper.getStaticsCountByXzqh(activitiesId,Arrays.asList("340221"));
                    JSONObject wz = new JSONObject();
                    wz.put("area", "湾沚区");
                    wz.put("count",wzCount);
                    children.add(wz);

                    int whCount = homestayRegisteredInfoMapper.getStaticsCountByXzqh(activitiesId,Arrays.asList("3402"));
                    JSONObject bswq = new JSONObject();
                    bswq.put("area", "本市外区");
                    bswq.put("count",whCount-wzCount);
                    children.add(bswq);

                    int hfCount = homestayRegisteredInfoMapper.getStaticsCountByXzqh(activitiesId,Arrays.asList("3401"));
                    JSONObject hf = new JSONObject();
                    hf.put("area", "合肥市");
                    hf.put("count",hfCount);
                    children.add(hf);

                    int xcCount = homestayRegisteredInfoMapper.getStaticsCountByXzqh(activitiesId,Arrays.asList("3418","3425"));
                    JSONObject xc = new JSONObject();
                    xc.put("area", "宣城市");
                    xc.put("count",xcCount);
                    children.add(xc);

                    int tlCount = homestayRegisteredInfoMapper.getStaticsCountByXzqh(activitiesId,Arrays.asList("3407"));
                    JSONObject tl = new JSONObject();
                    tl.put("area", "铜陵市");
                    tl.put("count",tlCount);
                    children.add(tl);

                    int masCount = homestayRegisteredInfoMapper.getStaticsCountByXzqh(activitiesId,Arrays.asList("3405"));
                    JSONObject mas = new JSONObject();
                    mas.put("area", "马鞍山市");
                    mas.put("count",masCount);
                    children.add(mas);
                } else if ("320000".equals(code)){
                    int njCount = homestayRegisteredInfoMapper.getStaticsCountByXzqh(activitiesId,Arrays.asList("3201"));
                    JSONObject nj = new JSONObject();
                    nj.put("area", "南京市");
                    nj.put("count",njCount);
                    children.add(nj);
                }

                j.put("children",children);
            }

            jsonObject.put("xzqh",staticsCountListByProvince);
            ac.setStaticInfo(jsonObject);
        }

        return activitiesInfoList;
    }

    @Override
    public List<RegisteredInfoVoByGovernment> selectRegisteredInfoVoByGovernmentList(HomestayRegisteredInfo homestayRegisteredInfo){
        return homestayRegisteredInfoMapper.selectRegisteredInfoVoByGovernmentList(homestayRegisteredInfo);
    }

    @Override
    public int getWinCount(Long activitiesId){
        return homestayRegisteredInfoMapper.getWinCount(activitiesId);
    }

    @Override
    public List<Long> selectNotWinIdsByWz(Long activitiesId){
        return homestayRegisteredInfoMapper.selectNotWinIdsByWz(activitiesId);
    }

    @Override
    public List<Long> selectNotWinIdsByWhNotWz(Long activitiesId){
        return homestayRegisteredInfoMapper.selectNotWinIdsByWhNotWz(activitiesId);
    }

    @Override
    public List<Long> selectNotWinIdsByOther(Long activitiesId){
        return homestayRegisteredInfoMapper.selectNotWinIdsByOther(activitiesId);
    }

    @Override
    public List<HomestayRegisteredInfo> selectHomestayRegisteredInfoByIds(List<Long> ids){
        return homestayRegisteredInfoMapper.selectHomestayRegisteredInfoByIds(ids);
    }

    @Override
    public int updateIsLotteryingShowByIds(String isLotteryingShow, List<Long> ids){
        return homestayRegisteredInfoMapper.updateIsLotteryingShowByIds(isLotteryingShow, ids);
    }

}
