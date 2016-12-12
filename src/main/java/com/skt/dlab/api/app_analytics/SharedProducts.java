package com.skt.dlab.api.app_analytics;

import com.skt.dlab.domain.Sharing;
import lombok.Data;

import java.util.List;

/**
 * Created by Rock Kang(cserock@gmail.com) on 2016. 12. 12..
 */
@Data
public class SharedProducts {

	int code;
	int pageNum;
	int pageIndex;
	String prevPage;
	String nextPage;

	List<Sharing> sharings;
}
