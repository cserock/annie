package com.skt.dlab.domain;

import lombok.Data;

/**
 * Created by Rock Kang(cserock@gmail.com) on 2016. 12. 6..
 */

@Data
public class Account {
	private int accountId;
	private String lastSalesDate;
	private String market;
	private String firstSalesDate;
	private String publisherName;
	private String accountName;
	private String vertical;
	private String accountStatus;
}
