package com.ruoyi.hikvision.Dto;
/*
 * @Description 统一返回信息
 * @Author xiemj
 * @Date  2021/5/13
 * @return
 **/
public class CommonResponseDto {
    //响应代码 0000-成功 ，9999-失败
    private String errorCode ;
    //响应信息
    private String errorMessage;
    //返回数据
    private Object data;



    public String getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(String errorCode) {
        this.errorCode = errorCode;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }
}
