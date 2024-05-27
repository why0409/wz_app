package com.ruoyi.web.controller.applet.utils;

import com.iflytek.fsp.shield.java.sdk.constant.HttpConstant;
import com.iflytek.fsp.shield.java.sdk.constant.SdkConstant;
import com.iflytek.fsp.shield.java.sdk.enums.Method;
import com.iflytek.fsp.shield.java.sdk.enums.ParamPosition;
import com.iflytek.fsp.shield.java.sdk.http.ApiClient;
import com.iflytek.fsp.shield.java.sdk.http.BaseApp;
import com.iflytek.fsp.shield.java.sdk.model.ApiRequest;
import com.iflytek.fsp.shield.java.sdk.model.ApiResponse;

/**
 * @Author: LJW
 * @Date: 2023/4/23 0023 9:43
 */
public class ShieldSyncApp extends BaseApp {

    public ShieldSyncApp() {
        this.apiClient = new ApiClient();
        this.apiClient.init();
        // 管理平台应用查看处获取并修改
        this.appId = "0746fa0b968345948f9858e6b2e60731";
        // 管理平台应用查看处获取并修改
        this.appSecret = "6D0710FCD09FEFE02039BD46F61E01ED";
        // 核心层ip
        this.host = "59.203.151.149";
        //核心层上下文
        this.contextPath ="";
        // 核心层暴露的http端口
        this.httpPort = 24989;
        // 核心层暴露的https端口
        this.httpsPort = 443;
        // sdk生成时选择的环境 RELEASE=线上  TEST=测试 PRE=预生产
        this.stage = "RELEASE";
        // 此参数暂时无用
        this.equipmentNo = "XXX";
        // 此参数暂时无用
        this.signStrategyUrl = "/getSignStrategy";
        // 此参数暂时无用
        this.tokenUrl = "/getTokenUrl";
        // 管理平台应用查看处获取并修改
        this.publicKey = "305C300D06092A864886F70D0101010500034B003048024100AC596399A59BE59FC63AAC2100AEEFABFE7AA4B25DB7DE6F14435CE71C8F9DC14C0B55059DCA5DA5DE83790F9873F7B92210CC18AEEFCA9878A8A99537C681210203010001";
        // 关闭云锁验证
        this.icloudlockEnabled = false;
    }

    /**
     * Version:202304211516335126
     */
    public ApiResponse atmosphericSite(byte[] body, String X_OS_Client_Id) {
        ApiRequest apiRequest = new ApiRequest(HttpConstant.SCHEME_HTTP, Method.POST, "/api/547312856271421981A9225B86874539", SdkConstant.AUTH_TYPE_ENCRYPT, "21ed457ab40643979b13fc87f0fdf108");
        apiRequest.setBody(body);

        apiRequest.addParam("X-OS-Client-Id", X_OS_Client_Id, ParamPosition.HEADER, true);

        return syncInvoke(apiRequest);
    }

}
