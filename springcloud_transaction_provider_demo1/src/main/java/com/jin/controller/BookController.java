package com.jin.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.jin.entity.AO.BookAO;
import com.jin.service.BookService;

import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/book")
@Slf4j
public class BookController {
	
	@Autowired
	private BookService bookService;
	
	@PostMapping(value = "/saveBook", produces = "application/json")
	public void saveBook(@RequestBody BookAO bookAo) {
		log.info("saveBook接口入参bookAo的值为：------------------>{}", bookAo);
		bookService.saveBook(bookAo);
	}
}
