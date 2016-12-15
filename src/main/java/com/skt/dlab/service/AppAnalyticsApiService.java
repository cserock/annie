package com.skt.dlab.service;

import com.skt.dlab.api.app_analytics.AccountConnectionProduct;
import com.skt.dlab.api.app_analytics.AccountConnections;
import com.skt.dlab.api.app_analytics.IAPList;
import com.skt.dlab.api.app_analytics.SharedProducts;
import com.skt.dlab.domain.Account;
import com.skt.dlab.domain.IAP;
import com.skt.dlab.domain.Product;
import com.skt.dlab.domain.Sharing;
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
public class AppAnalyticsApiService extends AnnieApiService {

	static final Logger log = LoggerFactory.getLogger(AppAnalyticsApiService.class);

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



	public AccountConnectionProduct getAccountConnectionProduct(String accountId, int pageIndex) throws Exception {
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

		List<Product> productList = setProducts(products);

		accountConnectionProduct.setProducts(productList);
		return accountConnectionProduct;
	}


	public IAPList getIAPList(String accountId, String productId, int pageIndex) throws Exception {
		final String uri = "https://api.appannie.com/v1.2/accounts/" + accountId + "/products/" + productId + "/iaps?page_index=" + pageIndex;

		JSONObject jsonObject = request(uri, HttpMethod.GET);

		if(jsonObject.containsKey("error")){
			throw new Exception(jsonObject.get("message").toString());
		}

		IAPList iAPList = new IAPList();

		iAPList.setCode(Integer.parseInt(jsonObject.get("code").toString()));
		iAPList.setProductId(jsonObject.get("product_id").toString());
		iAPList.setPageNum(Integer.parseInt(jsonObject.get("page_num").toString()));
		iAPList.setPageIndex(Integer.parseInt(jsonObject.get("page_index").toString()));

		String prevPage = "";
		if(jsonObject.get("prev_page") != null){
			prevPage = jsonObject.get("prev_page").toString();
		}

		String nextPage = "";
		if(jsonObject.get("next_page") != null){
			nextPage = jsonObject.get("next_page").toString();
		}

		iAPList.setNextPage(nextPage);
		iAPList.setPrevPage(prevPage);

		/*
		JSONArray iaps = new JSONArray();

		JSONObject obj = new JSONObject();
		obj.put("name", "test_1");
		obj.put("sku", "sku_1");
		obj.put("type", "type_1");

		iaps.add(obj);

		obj = new JSONObject();
		obj.put("name", "test_2");
		obj.put("sku", "sku_2");
		obj.put("type", "type_2");

		iaps.add(obj);

		obj = new JSONObject();
		obj.put("name", "test_3");
		obj.put("sku", "sku_3");
		obj.put("type", "type_3");

		iaps.add(obj);
		*/

		JSONArray iaps = (JSONArray) jsonObject.get("iaps");

		List<IAP> iapArray = new ArrayList<>();

		for(int i=0; i<iaps.size(); i++){

			JSONObject iapObject = (JSONObject) iaps.get(i);

			IAP iap = new IAP();
			iap.setName(iapObject.get("name").toString());
			iap.setSku(iapObject.get("sku").toString());
			iap.setType(iapObject.get("type").toString());

			iapArray.add(iap);
		}

		iAPList.setIAPs(iapArray);

		return iAPList;
	}


	public SharedProducts getSharedProducts(int pageIndex) throws Exception {

		final String uri = "https://api.appannie.com/v1.2/sharing/products?page_index=" + pageIndex;
		JSONObject jsonObject = request(uri, HttpMethod.GET);

		// sample json
		//		String jsonString = "{\"code\":200,\"sharings\":[{\"vertical\":\"apps\",\"owner_account_id\":11223,\"owner_name\":\"Appannie\",\"products\":[{\"product_id\":\"345697\",\"product_name\":\"Super Fun Game\",\"icon\":\"http://www.appannie.com/pics/12.png\",\"status\":true,\"first_sales_date\":\"2012-12-12\",\"last_sales_date\":\"2013-01-12\"}]}],\"page_num\":1,\"page_index\":0,\"prev_page\": null,\"next_page\": null}";
		//		JSONParser jsonParser = new JSONParser();
		//		JSONObject jsonObject = (JSONObject) jsonParser.parse(jsonString);

		if(jsonObject.containsKey("error")){
			throw new Exception(jsonObject.get("message").toString());
		}

		log.debug(jsonObject.toString());

		SharedProducts sharedProducts = new SharedProducts();

		sharedProducts.setCode(Integer.parseInt(jsonObject.get("code").toString()));

		sharedProducts.setPageNum(Integer.parseInt(jsonObject.get("page_num").toString()));
		sharedProducts.setPageIndex(Integer.parseInt(jsonObject.get("page_index").toString()));

		String prevPage = "";
		if(jsonObject.get("prev_page") != null){
			prevPage = jsonObject.get("prev_page").toString();
		}

		String nextPage = "";
		if(jsonObject.get("next_page") != null){
			nextPage = jsonObject.get("next_page").toString();
		}

		sharedProducts.setNextPage(nextPage);
		sharedProducts.setPrevPage(prevPage);


		JSONArray sharings = (JSONArray) jsonObject.get("sharings");

		List<Sharing> sharingArray = new ArrayList<>();

		for(int i=0; i<sharings.size(); i++){

			JSONObject sharingObject = (JSONObject) sharings.get(i);

			Sharing sharing = new Sharing();
			sharing.setVertical(sharingObject.get("vertical").toString());
			sharing.setOwnerAccountId(Integer.parseInt(sharingObject.get("owner_account_id").toString()));
			sharing.setOwnerName(sharingObject.get("owner_name").toString());

			JSONArray products = (JSONArray) sharingObject.get("products");

			List<Product> productArray = setProducts(products);

			sharing.setProducts(productArray);
			sharingArray.add(sharing);
		}

		sharedProducts.setSharings(sharingArray);

		return sharedProducts;
	}

	private List<Product> setProducts(JSONArray products){

		List<Product> productArrayList = new ArrayList<>();

		for(int i=0; i<products.size(); i++){

			JSONObject productObject = (JSONObject) products.get(i);


			// set devices
			JSONArray devicesArray = (JSONArray) productObject.get("devices");
			List<String> devices = new ArrayList<>();

			if(devicesArray != null){
				for(int j=0; j<devicesArray.size(); j++){
					String device = devicesArray.get(j).toString();
					devices.add(device);
				}
			}

			// set device codes
			JSONArray deviceCodesArray = (JSONArray) productObject.get("device_codes");
			List<String> deviceCodes = new ArrayList<>();

			if(deviceCodesArray != null){
				for(int k=0; k<deviceCodesArray.size(); k++){
					String deviceCode = deviceCodesArray.get(k).toString();
					deviceCodes.add(deviceCode);
				}
			}

			Product product = new Product();
			product.setProductId(productObject.get("product_id").toString());
			product.setProductName(productObject.get("product_name").toString());
			product.setIcon(productObject.get("icon").toString());
			product.setStatus((boolean)productObject.get("status"));
			product.setFirstSalesDate(productObject.get("first_sales_date").toString());
			product.setLastSalesDate(productObject.get("last_sales_date").toString());
			product.setDevices(devices);
			product.setDeviceCodes(deviceCodes);

			productArrayList.add(product);
		}

		return productArrayList;
	}

}
