package com.jin.service.impl;

import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jin.entity.AO.BookAO;
import com.jin.entity.DO.BookDO;
import com.jin.entity.VO.BookVO;
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

}
