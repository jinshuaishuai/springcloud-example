package com.jin.entity.query;

import java.io.Serializable;
import java.util.List;

import com.jin.config.GlobalPageParam;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
/**
 * 
 * @author shuai.jin
 * @datetime 2019.07.04 18:51
 */
@Data
@ApiModel(value = "BookQuery",description = "图书查询入参")
public class BookQuery implements Serializable {
	
	private static final long serialVersionUID = 2418556033107669998L;
	
	@ApiModelProperty(value = "start")
	private Integer start = GlobalPageParam.DEFAULT_CURRENT;
	
	@ApiModelProperty(value = "count")
	private Integer count = GlobalPageParam.COUNT;
	
	@ApiModelProperty(value = "id")
	private Integer id;
	
	@ApiModelProperty(value = "是否删除")
	private Byte isDelete;
	
	@ApiModelProperty(value = "ids")
	private List<Integer> ids;
	
}
