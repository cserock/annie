package com.skt.dlab;

import com.skt.dlab.api.app_analytics.AccountConnectionProduct;
import com.skt.dlab.api.app_analytics.AccountConnections;
import com.skt.dlab.api.app_analytics.IAPList;
import com.skt.dlab.api.app_analytics.SharedProducts;
import com.skt.dlab.domain.Error;
import com.skt.dlab.service.AppAnalyticsApiService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.HttpClientErrorException;

/**
 * Created by Rock Kang(cserock@gmail.com) on 2016. 12. 15..
 */
@Api(value = "AppAnalyticsApi", description = "App Analytics APIs")
@RestController
@RequestMapping("/api")
public class AppAnalyticsApiController extends ApiController {

	static final Logger log = LoggerFactory.getLogger(AppAnalyticsApiController.class);

	@Autowired AppAnalyticsApiService appAnalyticsApiService;

	@ApiOperation(value = "Retrieve all account connections available in an App Annie user account")
	@RequestMapping(value="/accounts", method= RequestMethod.GET, produces = "application/json")
	public ResponseEntity<?> accounts(@RequestParam(value="page_index", defaultValue = "0") int pageIndex){

		try{

			AccountConnections accountConnections = appAnalyticsApiService.getAccountConnections(pageIndex);

			if(accountConnections.getAccounts().isEmpty()){
				return new ResponseEntity<>(accountConnections, HttpStatus.NOT_FOUND);
			}

			return new ResponseEntity<>(accountConnections, HttpStatus.OK);

		} catch(HttpClientErrorException ex) {
			ex.printStackTrace();
			String message = ex.getClass().toString();
			return new ResponseEntity<>(new Error(ex.getStatusCode().value(), message), ex.getStatusCode());

		}  catch(Exception ex) {
			ex.printStackTrace();
			String message = ex.getMessage();
			return new ResponseEntity<>(new Error(HttpStatus.INTERNAL_SERVER_ERROR.value(), message), HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@ApiOperation(value = "Retrieve the product list of an Analytics Account Connection")
	@RequestMapping(value="/account/products", method= RequestMethod.GET, produces = "application/json")
	public ResponseEntity<?> accountProducts(@RequestParam(value="account_id", required = true) String accountId, @RequestParam(value="page_index", defaultValue = "0") int pageIndex){

		try{

			AccountConnectionProduct accountConnectionProduct = appAnalyticsApiService.getAccountConnectionProduct(accountId, pageIndex);

			if(accountConnectionProduct.getProducts().isEmpty()){
				return new ResponseEntity<>(accountConnectionProduct, HttpStatus.NOT_FOUND);
			}

			return new ResponseEntity<>(accountConnectionProduct, HttpStatus.OK);

		} catch(HttpClientErrorException ex) {
			ex.printStackTrace();
			String message = ex.getClass().toString();
			return new ResponseEntity<>(new Error(ex.getStatusCode().value(), message), ex.getStatusCode());

		}  catch(Exception ex) {
			ex.printStackTrace();
			String message = ex.getMessage();
			return new ResponseEntity<>(new Error(HttpStatus.INTERNAL_SERVER_ERROR.value(), message), HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@ApiOperation(value = "Retrieve the In Product Purchase list of one product")
	@RequestMapping(value="/account/products/iaps", method= RequestMethod.GET, produces = "application/json")
	public ResponseEntity<?> accountProductsIaps(@RequestParam(value="account_id", required = true) String accountId, @RequestParam(value="product_id", required = true) String productId, @RequestParam(value="page_index", defaultValue = "0") int pageIndex){

		try{

			IAPList iapList = appAnalyticsApiService.getIAPList(accountId, productId, pageIndex);

			if(iapList.getIAPs().isEmpty()){
				return new ResponseEntity<>(iapList, HttpStatus.NOT_FOUND);
			}

			return new ResponseEntity<>(iapList, HttpStatus.OK);

		} catch(HttpClientErrorException ex) {
			ex.printStackTrace();
			String message = ex.getClass().toString();
			return new ResponseEntity<>(new Error(ex.getStatusCode().value(), message), ex.getStatusCode());

		}  catch(Exception ex) {
			ex.printStackTrace();
			String message = ex.getMessage();
			return new ResponseEntity<>(new Error(HttpStatus.INTERNAL_SERVER_ERROR.value(), message), HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}


	@ApiOperation(value = "Retrieve the list of shared products a user may have access to")
	@RequestMapping(value="/sharing/products", method= RequestMethod.GET, produces = "application/json")
	public ResponseEntity<?> sharingProducts(@RequestParam(value="page_index", defaultValue = "0") int pageIndex){

		try{

			SharedProducts sharedProducts = appAnalyticsApiService.getSharedProducts(pageIndex);

			if(sharedProducts.getSharings().isEmpty()){
				return new ResponseEntity<>(sharedProducts, HttpStatus.NOT_FOUND);
			}

			return new ResponseEntity<>(sharedProducts, HttpStatus.OK);

		} catch(HttpClientErrorException ex) {
			ex.printStackTrace();
			String message = ex.getClass().toString();
			return new ResponseEntity<>(new Error(ex.getStatusCode().value(), message), ex.getStatusCode());

		}  catch(Exception ex) {
			ex.printStackTrace();
			String message = ex.getMessage();
			return new ResponseEntity<>(new Error(HttpStatus.INTERNAL_SERVER_ERROR.value(), message), HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
}