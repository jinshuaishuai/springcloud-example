package com.jin.common.config;

import lombok.Data;
import lombok.ToString;
@Data
@ToString
public class Rest<T> {
	private T original;
}
