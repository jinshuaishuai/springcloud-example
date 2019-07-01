package com.jin.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.jin.entity.DO.BookDO;
import com.jin.entity.VO.BookVO;

@Mapper
public interface BookMapper {
	
	/**
	 * 保存一本书
	 * @param bookDo
	 */
	void saveBook(BookDO bookDo);
	/**
	 * 获取图书列表
	 * @return
	 */
	List<BookVO> getBookList();
	
}
