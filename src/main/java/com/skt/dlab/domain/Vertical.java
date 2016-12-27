package com.skt.dlab.domain;

import lombok.Data;

import java.util.List;

/**
 * Created by Rock Kang(cserock@gmail.com) on 2016. 12. 27..
 */
@Data
public class Vertical {
	private String verticalName;
	private String verticalCode;
	List<Market> markets;
}
