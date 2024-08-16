package com.ruoyi.app.mapper;

import com.ruoyi.app.domain.QuestionnaireGiftInfo;
import com.ruoyi.app.domain.QuestionnaireInfo;

import java.util.List;

/**
 * 问卷调查-申领礼品信息Mapper接口
 *
 * @author ruoyi
 * @date 2024-07-31
 */
public interface QuestionnaireGiftInfoMapper
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
     * 删除问卷调查-申领礼品信息
     *
     * @param id 问卷调查-申领礼品信息主键
     * @return 结果
     */
    public int deleteQuestionnaireGiftInfoById(Long id);

    /**
     * 批量删除问卷调查-申领礼品信息
     *
     * @param ids 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteQuestionnaireGiftInfoByIds(Long[] ids);

    QuestionnaireGiftInfo selectTodayQuestionnaireInfoByPhone(String phone);
}
