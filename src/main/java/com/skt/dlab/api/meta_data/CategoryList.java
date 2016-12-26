package com.skt.dlab.api.meta_data;

import io.swagger.annotations.ApiModel;
import lombok.Data;

import java.util.HashMap;
import java.util.List;

/**
 * Created by Rock Kang(cserock@gmail.com) on 2016. 12. 26..
 */
@Data
@ApiModel
public class CategoryList {
	int code;
	List<String> appAnnieCategories;
	List<String> categories;
	HashMap<String, String> categoryLabels;
}
