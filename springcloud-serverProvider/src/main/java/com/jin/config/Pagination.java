package com.jin.config;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
/**
 * 分页对象
 * @author shuai.jin
 * @see        GlobalPageParam
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Pagination implements Serializable {
	
	private static final long serialVersionUID = 7456008675430735870L;

	private static final Integer DEFAULT_PAGE_SIZE = GlobalPageParam.COUNT;

    private static final Integer DEFAULT_CURRENT = GlobalPageParam.DEFAULT_CURRENT;

    private Integer pageSize = DEFAULT_PAGE_SIZE;		//每页数量

    private Integer current = DEFAULT_CURRENT;			//当前页号，默认为1

    private Long total;									//总条数

    private Integer totalPageNum;						//总页数
    
}
