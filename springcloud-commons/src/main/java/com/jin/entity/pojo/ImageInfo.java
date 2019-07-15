package com.jin.entity.pojo;

import java.io.Serializable;

import lombok.Data;
import lombok.ToString;
/**
 * 
 * @author shuai.jin
 * @description		返回图片信息
 *
 */
@Data
@ToString
public class ImageInfo implements Serializable{
	

	private static final long serialVersionUID = 1106739757835749720L;

	private String url;
	
	private Integer width;
	
	private Integer size;
	
	private Integer height;
}
