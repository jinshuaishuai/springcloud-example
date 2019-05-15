package com.jin.mapper;

import org.apache.ibatis.annotations.Mapper;

import com.jin.entity.DO.BookDO;

@Mapper
public interface BookMapper {
	
	/**
	 * 保存一本书
	 * @param bookDo
	 */
	void saveBook(BookDO bookDo);
	
}
