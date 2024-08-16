package com.ruoyi.app.service.impl;

import com.alibaba.fastjson2.JSONObject;
import com.ruoyi.app.domain.QuestionnaireInfo;
import com.ruoyi.app.domain.vo.QuestionnaireInfoVo;
import com.ruoyi.app.mapper.QuestionnaireInfoMapper;
import com.ruoyi.app.service.IQuestionnaireInfoService;
import com.ruoyi.common.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 问卷调查信息Service业务层处理
 *
 * @author ruoyi
 * @date 2024-07-31
 */
@Service
public class QuestionnaireInfoServiceImpl implements IQuestionnaireInfoService
{
    @Autowired
    private QuestionnaireInfoMapper questionnaireInfoMapper;

    /**
     * 查询问卷调查信息
     *
     * @param id 问卷调查信息主键
     * @return 问卷调查信息
     */
    @Override
    public QuestionnaireInfo selectQuestionnaireInfoById(String id)
    {
        return questionnaireInfoMapper.selectQuestionnaireInfoById(id);
    }

    /**
     * 查询问卷调查信息列表
     *
     * @param questionnaireInfo 问卷调查信息
     * @return 问卷调查信息
     */
    @Override
    public List<QuestionnaireInfo> selectQuestionnaireInfoList(QuestionnaireInfo questionnaireInfo)
    {
        return questionnaireInfoMapper.selectQuestionnaireInfoList(questionnaireInfo);
    }

    /**
     * 新增问卷调查信息
     *
     * @param questionnaireInfo 问卷调查信息
     * @return 结果
     */
    @Override
    public int insertQuestionnaireInfo(QuestionnaireInfo questionnaireInfo)
    {
        questionnaireInfo.setCreateTime(DateUtils.getNowDate());
        return questionnaireInfoMapper.insertQuestionnaireInfo(questionnaireInfo);
    }

    /**
     * 修改问卷调查信息
     *
     * @param questionnaireInfo 问卷调查信息
     * @return 结果
     */
    @Override
    public int updateQuestionnaireInfo(QuestionnaireInfo questionnaireInfo)
    {
        questionnaireInfo.setUpdateTime(DateUtils.getNowDate());
        return questionnaireInfoMapper.updateQuestionnaireInfo(questionnaireInfo);
    }

    /**
     * 批量删除问卷调查信息
     *
     * @param ids 需要删除的问卷调查信息主键
     * @return 结果
     */
    @Override
    public int deleteQuestionnaireInfoByIds(Long[] ids)
    {
        return questionnaireInfoMapper.deleteQuestionnaireInfoByIds(ids);
    }

    /**
     * 删除问卷调查信息信息
     *
     * @param id 问卷调查信息主键
     * @return 结果
     */
    @Override
    public int deleteQuestionnaireInfoById(Long id)
    {
        return questionnaireInfoMapper.deleteQuestionnaireInfoById(id);
    }

    @Override
    public List<QuestionnaireInfoVo> selectQuestionnaireInfoVoList(QuestionnaireInfo questionnaireInfo){
        return questionnaireInfoMapper.selectQuestionnaireInfoVoList(questionnaireInfo);
    }

    @Override
    public QuestionnaireInfoVo selectQuestionnaireInfoVoById(String id){
        return questionnaireInfoMapper.selectQuestionnaireInfoVoById(id);
    }

    @Override
    public List<JSONObject> staticsCountByWindows(String windowNumber){
        return questionnaireInfoMapper.staticsCountByWindows(windowNumber);
    }

    @Override
    public List<JSONObject> staticsSatisfaction(){
        return questionnaireInfoMapper.staticsSatisfaction();
    }
}
