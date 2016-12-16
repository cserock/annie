package com.skt.dlab.api.app_analytics;

import com.skt.dlab.domain.IAP;
import io.swagger.annotations.ApiModel;
import lombok.Data;

import java.util.List;

/**
 * Created by Rock Kang(cserock@gmail.com) on 2016. 12. 9..
 */
@Data
@ApiModel
public class IAPList {
	int code;
	String productId;
	int pageNum;
	int pageIndex;
	String prevPage;
	String nextPage;
	List<IAP> iAPs;
}
