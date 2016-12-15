package com.skt.dlab.service;

import com.skt.dlab.api.meta_data.CountryList;
import com.skt.dlab.domain.Country;
import com.skt.dlab.domain.Region;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Rock Kang(cserock@gmail.com) on 2016. 12. 15..
 */
@Service
public class MetaDataApiService extends AnnieApiService {

	static final Logger log = LoggerFactory.getLogger(MetaDataApiService.class);

	public CountryList getCountries() throws Exception {

		final String uri = "https://api.appannie.com/v1.2/meta/countries";
		JSONObject jsonObject = request(uri, HttpMethod.GET);

		// sample json
		// {"region_list":[{"region_name":"Worldwide","region_code":"WW"}],"code":200,"country_list":[{"country_code":"AD","country_name":"Andorra"},{"country_code":"AE","country_name":"United Arab Emirates"},{"country_code":"AF","country_name":"Afghanistan"}]}

		if(jsonObject.containsKey("error")){
			throw new Exception(jsonObject.get("message").toString());
		}

//		log.debug(jsonObject.toString());

		CountryList countryList = new CountryList();

		countryList.setCode(Integer.parseInt(jsonObject.get("code").toString()));

		JSONArray countries = (JSONArray) jsonObject.get("country_list");
		List<Country> countryArray = new ArrayList<>();

		for(int i=0; i<countries.size(); i++){

			JSONObject countryObject = (JSONObject) countries.get(i);
			Country country = new Country();
			country.setCountryCode(countryObject.get("country_code").toString());
			country.setCountryName(countryObject.get("country_name").toString());
			countryArray.add(country);
		}
		countryList.setCountries(countryArray);

		JSONArray regions = (JSONArray) jsonObject.get("region_list");
		List<Region> regionsArray = new ArrayList<>();
		for(int i=0; i<regions.size(); i++){

			JSONObject regionObject = (JSONObject) regions.get(i);
			Region region = new Region();
			region.setRegionCode(regionObject.get("region_code").toString());
			region.setRegionName(regionObject.get("region_name").toString());
			regionsArray.add(region);
		}
		countryList.setRegions(regionsArray);

		return countryList;
	}

}