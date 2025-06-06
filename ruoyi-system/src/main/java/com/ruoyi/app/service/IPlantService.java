package com.ruoyi.app.service;


import com.alibaba.fastjson2.JSONArray;
import com.alibaba.fastjson2.JSONObject;
import com.ruoyi.app.domain.PlantPermissions;
import com.ruoyi.app.domain.vo.QueryAssetPlantVo;
import com.ruoyi.app.domain.vo.QueryCommentVo;
import com.ruoyi.app.domain.vo.QueryPlantVo;

import java.util.List;

public interface IPlantService {
    @Deprecated
    JSONArray getPlantList(QueryPlantVo plant);

    JSONArray getAssetPlant(QueryAssetPlantVo queryAssetPlantVo);

    /**
     * 新建留言
     *
     * @param queryCommentVo
     * @return
     */
    JSONObject createComment(QueryCommentVo queryCommentVo);
}
