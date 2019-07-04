package com.jin.service.impl;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jin.entity.AO.AuthorInfoAO;
import com.jin.entity.DO.AuthorInfoDO;
import com.jin.mapper.AuthorInfoMapper;
import com.jin.service.AuthorInfoServer;

import lombok.extern.slf4j.Slf4j;
/**
 * 
 * @author shuai.jin
 *
 */

@Service
@Slf4j
public class AuthorInfoServiceImpl implements AuthorInfoServer {
	
	@Autowired
	private AuthorInfoMapper authorInfoMapper;
	
	@Override
	public void saveAuthorInfo(AuthorInfoAO authorInfoAO) {
		log.info("params value is ---->{}",authorInfoAO);
		AuthorInfoDO author = new AuthorInfoDO();
		BeanUtils.copyProperties(authorInfoAO, author);
		authorInfoMapper.saveAuthorInfo(author);
	}

}
