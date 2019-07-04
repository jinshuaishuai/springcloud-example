package com.jin.service;

import com.jin.entity.AO.AuthorInfoAO;

public interface AuthorInfoServer {
	
	/**
	 * 保存一条作者信息
	 * @param authorInfoAO
	 */
	void saveAuthorInfo(AuthorInfoAO authorInfoAO);

}
