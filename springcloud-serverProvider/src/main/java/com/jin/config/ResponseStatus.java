package com.jin.config;
/**
 * 通用的结果响应
 * @author shuai.jin
 * @datetime 2019-03-18 19:11:23
 * @see		ResponseStatusEnum
 *
 */
public interface ResponseStatus {

	public String getCode();

    public String getMessage();
}
