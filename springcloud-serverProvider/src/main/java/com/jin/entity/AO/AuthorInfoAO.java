package com.jin.entity.AO;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.Range;

import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class AuthorInfoAO {
	
	@NotEmpty(message = "姓名不能为空\n")
	private String name;

	@NotNull(message = "年龄不能为空\n")
	@Range(min = 0,max = 200)
    private Byte age;
	
	@NotEmpty(message = "手机号不能为空\n")
    private String phone;

	@NotEmpty(message = "住址不能为空")
	@Length(max = 128)
    private String address;

    private Byte isDelete;

    private String createTime;

    private String updateTime;
    
}
