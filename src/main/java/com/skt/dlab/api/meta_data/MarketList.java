package com.skt.dlab.api.meta_data;

import com.skt.dlab.domain.Vertical;
import io.swagger.annotations.ApiModel;
import lombok.Data;

import java.util.List;

/**
 * Created by Rock Kang(cserock@gmail.com) on 2016. 12. 27..
 */
@Data
@ApiModel
public class MarketList {
	int code;
	List<Vertical> verticals;
}
