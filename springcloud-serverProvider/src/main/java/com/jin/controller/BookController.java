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

import com.github.pagehelper.Page;
import com.github.pagehelper.PageInfo;
import com.jin.config.RestResponse;
import com.jin.entity.AO.BookAO;
import com.jin.entity.VO.BookVO;
import com.jin.entity.query.BookQuery;
import com.jin.service.BookService;

import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/provider1")
@Slf4j
public class BookController {
	
	@Autowired BookService bookService;
	
	@PostMapping("/saveBook1")
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
	
	@PostMapping("/saveBook")
	public RestResponse<String> saveBook(
	@RequestParam String bookName,
			@RequestParam Integer bookPrice,
			@RequestParam String address,
			@RequestParam String phone
	
			/*
			 * @RequestBody BookAO bookAO*/
			/*
			 * HttpServletRequest request
			 * */) {
			 
		/*
		Enumeration<String> names = request.getParameterNames();
		log.info("names ------->{}", names.toString());
		System.out.println(names.hasMoreElements());
		while(names.hasMoreElements()) {
			
			String element = names.nextElement();
			System.out.println(element);
			System.out.println(request.getParameter(element));
			log.info(" " + request.getParameter(element));
		}
		*/
		log.info("bookName -> {},bookPrice -> {},address -> {},phone -> {}",bookName,bookPrice,address,phone);
//		log.info("book -> {}", bookAO.toString());
		BookAO bookAO = new BookAO();
		bookAO.setBookName(bookName);
		bookAO.setBookPrice(bookPrice);
		bookAO.setAddress(address);
		bookAO.setPhone(phone);
		try {
			bookService.saveBook(bookAO);
		} catch(Exception e) {
			return RestResponse.error("500", e.toString());
		}
		return RestResponse.success("SUCCESS");
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
	
	@ApiOperation(value = "条件查询图书信息伴随分页")
	@PostMapping("/getBookByParams")
	public RestResponse<Page<BookVO>> getBookByParams(@RequestBody BookQuery params) {
		log.info("接口入参为：--->{}", params);
		try {
			Page<BookVO> books = bookService.getBookByParams(params);
			PageInfo<BookVO> pageInfo = new PageInfo<>(books);
			return RestResponse.success(pageInfo);
		} catch (Exception e) {
			log.error("getBookByParams接口出现错误信息--->{}", e.toString());
			return RestResponse.error("500", e.toString());
		}
	}
}
