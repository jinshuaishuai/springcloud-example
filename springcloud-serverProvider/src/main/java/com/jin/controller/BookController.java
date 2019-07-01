package com.jin.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
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
	public RestResponse<String> saveBook(@RequestBody @Valid BookAO book, BindingResult result) {
		log.info("saveBook接口入参为：------------->{}",book);
		if(result.hasErrors()) {
			StringBuffer sb = new StringBuffer();
			for(ObjectError error : result.getAllErrors()) {
				sb.append(error.getDefaultMessage());
			}
			log.info("sb--------->{}",sb.toString());
			return RestResponse.error("500", sb.toString());
		}
		bookService.saveBook(book);
		return null;
	}
	
	@GetMapping("/getBookList")
	public RestResponse<List<BookVO>> getBookList() {
		List<BookVO> books = bookService.getBookList();
		return RestResponse.success(books);
	}
	
	@GetMapping("/getBookById")
	public RestResponse<BookVO> getBookById(@RequestParam(name = "id",required = true) Integer id) {
		if(id == null) {
			return RestResponse.error("FAIL", "id不能为空");
		}
		log.info("id的值为：--------->{}", id);
		BookVO bookVO = bookService.getBookById(id);
		return RestResponse.success(bookVO);
	}
}
