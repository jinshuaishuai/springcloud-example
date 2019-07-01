package com.jin.service;

import java.util.List;

import com.jin.entity.AO.BookAO;
import com.jin.entity.VO.BookVO;

public interface BookService {
	
	/**
	 * 保存一本书
	 * @param bookAo
	 */
	void saveBook(BookAO bookAo);
	/**
	 * 查询所有的图书
	 * @return
	 */
	List<BookVO> getBookList();

}
