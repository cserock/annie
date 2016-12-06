package com.skt.dlab.domain;

import lombok.Data;

/**
 * Created by Rock Kang(cserock@gmail.com) on 2016. 12. 6..
 */
@Data
public class Error {
	private int code;
	private String error;

	public Error(int code, String error){
		this.code = code;
		this.error = error;
	}
}
