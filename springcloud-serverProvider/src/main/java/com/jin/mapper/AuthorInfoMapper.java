package com.jin.mapper;

import org.apache.ibatis.annotations.Mapper;

import com.jin.entity.DO.AuthorInfoDO;

@Mapper
public interface AuthorInfoMapper {

	void saveAuthorInfo(AuthorInfoDO author);
	
}
