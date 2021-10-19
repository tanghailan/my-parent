package com.kanavi.response.api;

import lombok.Data;

/**
 * @author hailan
 * @version 1.0
 * @className ResponseBean
 * @description TODO
 * @date 2020-09-08 17:07
 */
@Data
public class ResponseBean {

    /** 200:操作成功  -1：操作失败**/

    // http 状态码
    private int code;

    // 返回信息
    private String msg;

    // 返回的数据
    private Object data;

    public ResponseBean(){}

    public ResponseBean(int code, String msg, Object data) {
        this.code = code;
        this.msg = msg;
        this.data = data;
    }

    public static ResponseBean error(String message) {
        ResponseBean responseBean = new ResponseBean();
        responseBean.setMsg(message);
        responseBean.setCode(-1);
        return responseBean;
    }

    public static ResponseBean error(int code,String message) {
        ResponseBean responseBean = new ResponseBean();
        responseBean.setMsg(message);
        responseBean.setCode(code);
        return responseBean;
    }

    public static ResponseBean success(Object data) {
        ResponseBean responseBean = new ResponseBean();
        responseBean.setData(data);
        responseBean.setCode(ResultCode.SUCCESS.getCode());
        responseBean.setMsg(ResultCode.SUCCESS.getMessage());
        return responseBean;
    }

    public static ResponseBean success(String message) {
        ResponseBean responseBean = new ResponseBean();
        responseBean.setData(null);
        responseBean.setCode(ResultCode.SUCCESS.getCode());
        responseBean.setMsg(message);
        return responseBean;
    }

    public static ResponseBean success() {
        ResponseBean responseBean = new ResponseBean();
        responseBean.setData(null);
        responseBean.setCode(ResultCode.SUCCESS.getCode());
        responseBean.setMsg(ResultCode.SUCCESS.getMessage());
        return responseBean;
    }
}

