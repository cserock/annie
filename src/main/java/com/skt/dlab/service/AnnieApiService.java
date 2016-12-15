package com.skt.dlab.service;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;

/**
 * Created by Rock Kang(cserock@gmail.com) on 2016. 12. 6..
 */
@Service
public class AnnieApiService {

	static final Logger log = LoggerFactory.getLogger(AnnieApiService.class);

	@Value("${app-annie.api.key}")
	protected String apiKey;


	protected JSONObject request(String uri, HttpMethod httpMethod){

		JSONObject jsonObject = new JSONObject();
		RestTemplate restTemplate = new RestTemplate();
		HttpHeaders headers = new HttpHeaders();
		String resultJsonString = "";

		try {

			headers.add("Authorization", "Bearer " + apiKey );
			headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
			HttpEntity<String> entity = new HttpEntity<String>("parameters", headers);
			ResponseEntity<String> result = restTemplate.exchange(uri, httpMethod, entity, String.class);
			resultJsonString = result.getBody().toString();

			JSONParser jsonParser = new JSONParser();
			jsonObject = (JSONObject) jsonParser.parse(resultJsonString);

//			log.debug(jsonObject.toString());

		} catch (HttpClientErrorException e) {
			throw e;
		} catch (ParseException e){
			String message = e.getClass().toString() + ": (error_type : " + e.getErrorType() + ", position : " + e.getPosition() + ")";
			jsonObject.put("error", true);
			jsonObject.put("message", message);
		}

		return jsonObject;
	}



}
