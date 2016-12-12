package com.skt.dlab.domain;

import lombok.Data;

import java.util.List;

/**
 * Created by Rock Kang(cserock@gmail.com) on 2016. 12. 12..
 */
@Data
public class Sharing {
	private String vertical;
	private int ownerAccountId;
	private String ownerName;
	private List<Product> products;
}
