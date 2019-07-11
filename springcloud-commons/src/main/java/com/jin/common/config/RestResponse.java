package com.jin.common.config;

import java.io.Serializable;

import org.apache.commons.lang3.StringUtils;

import com.github.pagehelper.PageInfo;

import lombok.Data;

@Data
public class RestResponse<T> implements Serializable {
    //响应代码
    private String code = "SUCCESS";

    //响应描述
    private String message = "操作成功";

    //响应体
    private T result = null;

    private Pagination pagination;

    //构造函数
    public RestResponse() {
        super();
    }

    //构造函数
    public RestResponse(String code, String msg, T result) {
        this.code = code;
        this.message = msg;
        this.result = result;
    }

    /**
     * 适合响应成功，不牵涉分页，没有返回结果类型。
     * @return
     */
    public static RestResponse success() {
        return new RestResponse();
    }
    /**
     * 适合响应成功，有结果返回类型
     * @param result
     * @return
     */
    public static <T> RestResponse<T> success(T result) {
        RestResponse<T> restResponse = new RestResponse<>();
        restResponse.setResult(result);
        return restResponse;
    }

    /**
     * 适合响应成功，分页类型
     * @param page
     * @return
     */
    public static <T> RestResponse<T> success(PageInfo page) {
        RestResponse response = new RestResponse<>();
        if (page != null) {
            response.setResult(page.getList());
            response.setPagination(
                    new Pagination(page.getPageSize(), page.getPageNum(), page.getTotal(),
                            page.getPages()));
        }
        return response;
    }
    /**
     * 适合响应成功，有结果返回，分页类型。
     * @param result
     * @param pagination
     * @return
     */
    public static <T> RestResponse<T> success(T result, Pagination pagination) {
        RestResponse<T> response = new RestResponse<>();
        response.setResult(result);
        response.setPagination(pagination);
        return response;
    }
    /**
     * 适合响应失败，错误码，错误信息类型。
     * @param code
     * @param errorMsg
     * @return
     */
    public static <T> RestResponse<T> error(String code, String errorMsg) {
        RestResponse<T> restResponse = new RestResponse<>();
        restResponse.setCode(code);
        restResponse.setMessage(errorMsg);
        return restResponse;
    }
    /**
     * 适合响应失败，错误码，错误信息类型
     * @param e
     * @return
     */
    public static <T> RestResponse<T> error(ResponseStatus e) {
        RestResponse<T> response = new RestResponse<>();
        response.setCode("FAIL");
        if (e != null) {
            response.setCode(e.getCode());
            response.setMessage(e.getMessage());
        }
        return response;
    }
    /**
     * 适合响应失败，如果有错误码，返回错误码，错误信息不为null，返回错误信息
     * @param e
     * @param errorMsg
     * @return
     */
    public static <T> RestResponse<T> error(ResponseStatus e, String errorMsg) {
        RestResponse<T> response = new RestResponse<>();
        response.setCode("FAIL");
        if (e != null) {
            response.setCode(e.getCode());
        }
        if (StringUtils.isNotBlank(errorMsg)) {
            response.setMessage(errorMsg);
        }
        return response;
    }
}
