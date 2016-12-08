package com.skt.dlab.api.app_analytics;

import com.skt.dlab.domain.Account;
import lombok.Data;

import java.util.List;

/**
 * Created by Rock Kang(cserock@gmail.com) on 2016. 12. 6..
 */
@Data
public class AccountConnections {
	int code;
	int pageNum;
	int pageIndex;
	String prevPage;
	String nextPage;
	List<Account> accounts;
}
