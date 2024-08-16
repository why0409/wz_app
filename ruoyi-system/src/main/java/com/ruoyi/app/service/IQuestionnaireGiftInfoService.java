package com.ruoyi.app.service;

import com.ruoyi.app.domain.QuestionnaireGiftInfo;

import java.util.List;

/**
 * 问卷调查-申领礼品信息Service接口
 *
 * @author ruoyi
 * @date 2024-07-31
 */
public interface IQuestionnaireGiftInfoService
{
    /**
     * 查询问卷调查-申领礼品信息
     *
     * @param id 问卷调查-申领礼品信息主键
     * @return 问卷调查-申领礼品信息
     */
    public QuestionnaireGiftInfo selectQuestionnaireGiftInfoById(Long id);

    /**
     * 查询问卷调查-申领礼品信息列表
     *
     * @param questionnaireGiftInfo 问卷调查-申领礼品信息
     * @return 问卷调查-申领礼品信息集合
     */
    public List<QuestionnaireGiftInfo> selectQuestionnaireGiftInfoList(QuestionnaireGiftInfo questionnaireGiftInfo);

    /**
     * 新增问卷调查-申领礼品信息
     *
     * @param questionnaireGiftInfo 问卷调查-申领礼品信息
     * @return 结果
     */
    public int insertQuestionnaireGiftInfo(QuestionnaireGiftInfo questionnaireGiftInfo);

    /**
     * 修改问卷调查-申领礼品信息
     *
     * @param questionnaireGiftInfo 问卷调查-申领礼品信息
     * @return 结果
     */
    public int updateQuestionnaireGiftInfo(QuestionnaireGiftInfo questionnaireGiftInfo);

    /**
     * 批量删除问卷调查-申领礼品信息
     *
     * @param ids 需要删除的问卷调查-申领礼品信息主键集合
     * @return 结果
     */
    public int deleteQuestionnaireGiftInfoByIds(Long[] ids);

    /**
     * 删除问卷调查-申领礼品信息信息
     *
     * @param id 问卷调查-申领礼品信息主键
     * @return 结果
     */
    public int deleteQuestionnaireGiftInfoById(Long id);

    public QuestionnaireGiftInfo selectTodayQuestionnaireInfoByPhone(String phone);
}
