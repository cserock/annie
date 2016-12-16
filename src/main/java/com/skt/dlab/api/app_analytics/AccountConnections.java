package com.skt.dlab.api.app_analytics;

import com.skt.dlab.domain.Account;
import io.swagger.annotations.ApiModel;
import lombok.Data;

import java.util.List;

/**
 * Created by Rock Kang(cserock@gmail.com) on 2016. 12. 6..
 */
@Data
@ApiModel
public class AccountConnections {
	int code;
	int pageNum;
	int pageIndex;
	String prevPage;
	String nextPage;
	List<Account> accounts;
}
