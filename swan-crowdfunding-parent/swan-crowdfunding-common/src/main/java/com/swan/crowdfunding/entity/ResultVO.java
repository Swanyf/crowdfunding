package com.swan.crowdfunding.entity;

/**
 * 解决handler方法登录的问题
 * 将所有的ajax请求的响应都封装为ResultVO的对象作为一个统一的规范
 * @author Swan
 *
 */
public class ResultVO<T> {
    // 请求成功
    public static final String SUCCESS = "SUCCESS";
    // 请求失败
    public static final String FAILED = "FAILED";
    //
    public static final String NO_MSG = "NO_MSG";
    public static final String NO_DATA = "NO_DATA";

    // 响应的结果(可选值有SUCCESS,FAILED)
    private String result;

    // 响应成功（SUCCESS）时，响应的数据
    private T responseData;

    // 响应失败(FAILED)时，提示的信息,默认没有信息
    private String message = NO_MSG;

    public ResultVO() {
        super();
    }

    public ResultVO(String result, T responseData, String message) {
        super();
        this.result = result;
        this.responseData = responseData;
        this.message = message;
    }

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }

    public T getResponseData() {
        return responseData;
    }

    public void setResponseData(T responseData) {
        this.responseData = responseData;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    @Override
    public String toString() {
        return "ResultVO [result=" + result + ", responseData=" + responseData + ", message=" + message + "]";
    }

}