package com.jin.service.impl;

import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.jin.entity.AO.BookAO;
import com.jin.entity.DO.BookDO;
import com.jin.entity.VO.BookVO;
import com.jin.entity.query.BookQuery;
import com.jin.mapper.BookMapper;
import com.jin.service.BookService;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class BookServiceImpl implements BookService {

	@Autowired
	private BookMapper bookMapper;
	
	@Override
	public void saveBook(BookAO bookAo) {
		log.info("saveBook接口入参为：bookAo------------>", bookAo);
		
		BookDO bookDo = new BookDO();
		BeanUtils.copyProperties(bookAo, bookDo);
		bookMapper.saveBook(bookDo);
	}

	@Override
	public List<BookVO> getBookList() {
		return bookMapper.getBookList();
	}

	@Override
	public BookVO getBookById(Integer id) {
		log.info("getBookById接口入参为：------------->{}",id);
		BookDO bookById = bookMapper.getBookById(id);
		log.info("查询的结果为：-------------->{}", bookById);
		if(null == bookById) {
			return null;
		}
		BookVO bookVo = new BookVO();
		BeanUtils.copyProperties(bookById, bookVo);
		return bookVo;
	}

	@Override
	public Page<BookVO> getBookByParams(BookQuery params) {

		log.info("service层入参为--->{}", params);
		PageHelper.startPage(params.getStart(), params.getCount());
		Page<BookDO> bookDOs = bookMapper.getBookByParams(params);
		Page<BookVO> bookVOs = new Page<>();
		for(BookDO book : bookDOs) {
			BookVO bookVO = new BookVO();
			BeanUtils.copyProperties(book, bookVO);
			bookVOs.add(bookVO);
		}
		bookVOs.setPageNum(bookDOs.getPageNum());
		bookVOs.setPages(bookDOs.getPages());
		bookVOs.setPageSize(bookDOs.getPageSize());
		bookVOs.setTotal(bookDOs.getTotal());
		return bookVOs;
	}

}
