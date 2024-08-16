package com.ruoyi.app.mapper;

import com.alibaba.fastjson2.JSONObject;
import com.ruoyi.app.domain.QuestionnaireInfo;
import com.ruoyi.app.domain.vo.QuestionnaireInfoVo;

import java.util.List;

/**
 * 问卷调查信息Mapper接口
 *
 * @author ruoyi
 * @date 2024-07-31
 */
public interface QuestionnaireInfoMapper
{
    /**
     * 查询问卷调查信息
     *
     * @param id 问卷调查信息主键
     * @return 问卷调查信息
     */
    public QuestionnaireInfo selectQuestionnaireInfoById(String id);

    /**
     * 查询问卷调查信息列表
     *
     * @param questionnaireInfo 问卷调查信息
     * @return 问卷调查信息集合
     */
    public List<QuestionnaireInfo> selectQuestionnaireInfoList(QuestionnaireInfo questionnaireInfo);

    /**
     * 新增问卷调查信息
     *
     * @param questionnaireInfo 问卷调查信息
     * @return 结果
     */
    public int insertQuestionnaireInfo(QuestionnaireInfo questionnaireInfo);

    /**
     * 修改问卷调查信息
     *
     * @param questionnaireInfo 问卷调查信息
     * @return 结果
     */
    public int updateQuestionnaireInfo(QuestionnaireInfo questionnaireInfo);

    /**
     * 删除问卷调查信息
     *
     * @param id 问卷调查信息主键
     * @return 结果
     */
    public int deleteQuestionnaireInfoById(Long id);

    /**
     * 批量删除问卷调查信息
     *
     * @param ids 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteQuestionnaireInfoByIds(Long[] ids);

    public List<QuestionnaireInfoVo> selectQuestionnaireInfoVoList(QuestionnaireInfo questionnaireInfo);

    public QuestionnaireInfoVo selectQuestionnaireInfoVoById(String id);

    public List<JSONObject> staticsCountByWindows(String windowNumber);

    public List<JSONObject> staticsSatisfaction();

}
