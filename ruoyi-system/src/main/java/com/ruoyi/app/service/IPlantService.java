package com.ruoyi.app.service;


import com.alibaba.fastjson2.JSONArray;
import com.ruoyi.app.domain.PlantPermissions;
import com.ruoyi.app.domain.vo.QueryPlantVo;

import java.util.List;

public interface IPlantService {
    JSONArray getPlantList(QueryPlantVo plant);

}
