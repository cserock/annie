package com.skt.dlab.domain;

import lombok.Data;

import java.util.List;

/**
 * Created by Rock Kang(cserock@gmail.com) on 2016. 12. 6..
 */
@Data
public class Product {
	String productId;
	String productName;
	List<String> devices;
	List<String> deviceCodes;
	String icon;
	Boolean status;
	String firstSalesDate;
	String lastSalesDate;
}
