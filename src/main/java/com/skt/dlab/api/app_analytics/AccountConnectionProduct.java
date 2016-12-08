package com.skt.dlab.api.app_analytics;

import com.skt.dlab.domain.Product;
import lombok.Data;

import java.util.List;

/**
 * Created by Rock Kang(cserock@gmail.com) on 2016. 12. 6..
 */
@Data
public class AccountConnectionProduct {
	int code;
	int pageNum;
	int pageIndex;
	String prevPage;
	String nextPage;
	List<Product> products;
}
