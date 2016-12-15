package com.skt.dlab.api.meta_data;

import com.skt.dlab.domain.Country;
import com.skt.dlab.domain.Region;
import lombok.Data;

import java.util.List;

/**
 * Created by Rock Kang(cserock@gmail.com) on 2016. 12. 15..
 */
@Data
public class CountryList {
	int code;
	List<Country> countries;
	List<Region> regions;
}
