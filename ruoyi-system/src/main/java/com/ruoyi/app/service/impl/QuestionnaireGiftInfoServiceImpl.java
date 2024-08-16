package com.ruoyi.app.service.impl;

import com.ruoyi.app.domain.QuestionnaireGiftInfo;
import com.ruoyi.app.mapper.QuestionnaireGiftInfoMapper;
import com.ruoyi.app.service.IQuestionnaireGiftInfoService;
import com.ruoyi.common.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 问卷调查-申领礼品信息Service业务层处理
 *
 * @author ruoyi
 * @date 2024-07-31
 */
@Service
public class QuestionnaireGiftInfoServiceImpl implements IQuestionnaireGiftInfoService
{
    @Autowired
    private QuestionnaireGiftInfoMapper questionnaireGiftInfoMapper;


    /**
     * 查询问卷调查-申领礼品信息
     *
     * @param id 问卷调查-申领礼品信息主键
     * @return 问卷调查-申领礼品信息
     */
    @Override
    public QuestionnaireGiftInfo selectQuestionnaireGiftInfoById(Long id)
    {
        return questionnaireGiftInfoMapper.selectQuestionnaireGiftInfoById(id);
    }

    /**
     * 查询问卷调查-申领礼品信息列表
     *
     * @param questionnaireGiftInfo 问卷调查-申领礼品信息
     * @return 问卷调查-申领礼品信息
     */
    @Override
    public List<QuestionnaireGiftInfo> selectQuestionnaireGiftInfoList(QuestionnaireGiftInfo questionnaireGiftInfo)
    {
        return questionnaireGiftInfoMapper.selectQuestionnaireGiftInfoList(questionnaireGiftInfo);
    }

    /**
     * 新增问卷调查-申领礼品信息
     *
     * @param questionnaireGiftInfo 问卷调查-申领礼品信息
     * @return 结果
     */
    @Override
    public int insertQuestionnaireGiftInfo(QuestionnaireGiftInfo questionnaireGiftInfo)
    {
        questionnaireGiftInfo.setCreateTime(DateUtils.getNowDate());
        return questionnaireGiftInfoMapper.insertQuestionnaireGiftInfo(questionnaireGiftInfo);
    }

    /**
     * 修改问卷调查-申领礼品信息
     *
     * @param questionnaireGiftInfo 问卷调查-申领礼品信息
     * @return 结果
     */
    @Override
    public int updateQuestionnaireGiftInfo(QuestionnaireGiftInfo questionnaireGiftInfo)
    {
        questionnaireGiftInfo.setUpdateTime(DateUtils.getNowDate());
        return questionnaireGiftInfoMapper.updateQuestionnaireGiftInfo(questionnaireGiftInfo);
    }

    /**
     * 批量删除问卷调查-申领礼品信息
     *
     * @param ids 需要删除的问卷调查-申领礼品信息主键
     * @return 结果
     */
    @Override
    public int deleteQuestionnaireGiftInfoByIds(Long[] ids)
    {
        return questionnaireGiftInfoMapper.deleteQuestionnaireGiftInfoByIds(ids);
    }

    /**
     * 删除问卷调查-申领礼品信息信息
     *
     * @param id 问卷调查-申领礼品信息主键
     * @return 结果
     */
    @Override
    public int deleteQuestionnaireGiftInfoById(Long id)
    {
        return questionnaireGiftInfoMapper.deleteQuestionnaireGiftInfoById(id);
    }

    @Override
    public QuestionnaireGiftInfo selectTodayQuestionnaireInfoByPhone(String phone){
        return questionnaireGiftInfoMapper.selectTodayQuestionnaireInfoByPhone(phone);
    }
}
