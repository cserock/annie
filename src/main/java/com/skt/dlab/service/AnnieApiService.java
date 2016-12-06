package com.skt.dlab.service;

import com.skt.dlab.api.AccountConnectionProduct;
import com.skt.dlab.api.AccountConnections;
import com.skt.dlab.domain.Account;
import com.skt.dlab.domain.Product;
import org.json.simple.JSONArray;
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

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by Rock Kang(cserock@gmail.com) on 2016. 12. 6..
 */
@Service
public class AnnieApiService {

	static final Logger log = LoggerFactory.getLogger(AnnieApiService.class);

	@Value("${app-annie.api.key}")
	private String apiKey;


	private JSONObject request(String uri, HttpMethod httpMethod){

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

			log.debug(jsonObject.toString());

		} catch (HttpClientErrorException e) {
			throw e;
		} catch (ParseException e){
			String message = e.getClass().toString() + ": (error_type : " + e.getErrorType() + ", position : " + e.getPosition() + ")";
			jsonObject.put("error", true);
			jsonObject.put("message", message);
		}

		return jsonObject;
	}

	public AccountConnections getAccountConnections(int pageIndex) throws Exception {
		final String uri = "https://api.appannie.com/v1.2/accounts?page_index=" + pageIndex;

		JSONObject jsonObject = request(uri, HttpMethod.GET);

		if(jsonObject.containsKey("error")){
			throw new Exception(jsonObject.get("message").toString());
		}

		AccountConnections accountConnections = new AccountConnections();

		accountConnections.setCode(Integer.parseInt(jsonObject.get("code").toString()));
		accountConnections.setPageNum(Integer.parseInt(jsonObject.get("page_num").toString()));
		accountConnections.setPageIndex(Integer.parseInt(jsonObject.get("page_index").toString()));

		String prevPage = "";
		if(jsonObject.get("prev_page") != null){
			prevPage = jsonObject.get("prev_page").toString();
		}

		String nextPage = "";
		if(jsonObject.get("next_page") != null){
			nextPage = jsonObject.get("next_page").toString();
		}

		accountConnections.setNextPage(nextPage);
		accountConnections.setPrevPage(prevPage);

		JSONArray accounts = (JSONArray) jsonObject.get("accounts");

		List<Account> accountList = new ArrayList<>();

		for(int i=0; i<accounts.size(); i++){

			JSONObject accountObject = (JSONObject) accounts.get(i);
			int accountId = Integer.parseInt(accountObject.get("account_id").toString());

			Account account = new Account();
			account.setAccountId(accountId);
			account.setAccountName((String)accountObject.get("account_name"));
			account.setLastSalesDate((String)accountObject.get("last_sales_date"));
			account.setMarket((String)accountObject.get("market"));
			account.setFirstSalesDate((String)accountObject.get("first_sales_date"));
			account.setPublisherName((String)accountObject.get("publisher_name"));
			account.setVertical((String)accountObject.get("vertical"));
			account.setAccountStatus((String)accountObject.get("account_status"));

			accountList.add(account);
		}

		accountConnections.setAccounts(accountList);
		return accountConnections;
	}



	public AccountConnectionProduct getAccountConnectionProduct(int accountId, int pageIndex) throws Exception {
		final String uri = "https://api.appannie.com/v1.2/accounts/" + accountId + "/products?page_index=" + pageIndex;

		JSONObject jsonObject = request(uri, HttpMethod.GET);

		if(jsonObject.containsKey("error")){
			throw new Exception(jsonObject.get("message").toString());
		}

		AccountConnectionProduct accountConnectionProduct = new AccountConnectionProduct();

		accountConnectionProduct.setCode(Integer.parseInt(jsonObject.get("code").toString()));
		accountConnectionProduct.setPageNum(Integer.parseInt(jsonObject.get("page_num").toString()));
		accountConnectionProduct.setPageIndex(Integer.parseInt(jsonObject.get("page_index").toString()));

		String prevPage = "";
		if(jsonObject.get("prev_page") != null){
			prevPage = jsonObject.get("prev_page").toString();
		}

		String nextPage = "";
		if(jsonObject.get("next_page") != null){
			nextPage = jsonObject.get("next_page").toString();
		}

		accountConnectionProduct.setNextPage(nextPage);
		accountConnectionProduct.setPrevPage(prevPage);

		JSONArray products = (JSONArray) jsonObject.get("products");

		List<Product> productList = new ArrayList<>();

		for(int i=0; i<products.size(); i++){

			JSONObject productObject = (JSONObject) products.get(i);

			Product product = new Product();
			product.setProductId(productObject.get("product_id").toString());
			product.setProductName(productObject.get("product_name").toString());
			product.setIcon(productObject.get("icon").toString());
			product.setStatus((boolean)productObject.get("status"));
			product.setFirstSalesDate(productObject.get("first_sales_date").toString());
			product.setLastSalesDate(productObject.get("last_sales_date").toString());

			productList.add(product);
		}

		accountConnectionProduct.setProducts(productList);
		return accountConnectionProduct;
	}


}