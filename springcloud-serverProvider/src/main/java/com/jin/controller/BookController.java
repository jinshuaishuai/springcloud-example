package com.jin.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.jin.config.RestResponse;
import com.jin.entity.AO.BookAO;
import com.jin.entity.VO.BookVO;
import com.jin.service.BookService;

import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/provider1")
@Slf4j
public class BookController {
	
	@Autowired BookService bookService;
	
	@PostMapping("/saveBook")
	public void saveBook(@RequestBody BookAO book) {
		log.info("saveBook接口入参为：------------->{}",book);
		bookService.saveBook(book);
	}
	
	@GetMapping("/getBookList")
	public RestResponse<List<BookVO>> getBookList() {
		List<BookVO> books = bookService.getBookList();
		return RestResponse.success(books);
	}
}
