package com.jin.service;

import java.util.List;

import com.github.pagehelper.Page;
import com.jin.entity.AO.BookAO;
import com.jin.entity.VO.BookVO;
import com.jin.entity.query.BookQuery;

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
	
	/**
	 * 根据id查询一本书
	 */
	BookVO getBookById(Integer id);
	
	/**
	 * 条件查询图书信息，伴随着分页
	 * @param params
	 * @return
	 */
	Page<BookVO> getBookByParams(BookQuery params);

}
