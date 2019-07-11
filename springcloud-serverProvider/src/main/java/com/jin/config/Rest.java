package com.jin.config;

import lombok.Data;
import lombok.ToString;
@Data
@ToString
public class Rest<T> {
	private T original;
}
