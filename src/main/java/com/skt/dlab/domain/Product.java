package com.skt.dlab.domain;

import lombok.Data;

import java.util.List;

/**
 * Created by Rock Kang(cserock@gmail.com) on 2016. 12. 6..
 */
@Data
public class Product {
	private String productId;
	private String productName;
	private List<String> devices;
	private List<String> deviceCodes;
	private String icon;
	private Boolean status;
	private String firstSalesDate;
	private String lastSalesDate;
}
