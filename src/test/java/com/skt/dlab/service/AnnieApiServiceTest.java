package com.skt.dlab.service;

import com.skt.dlab.api.app_analytics.IAPList;
import com.skt.dlab.domain.Product;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * Created by Rock Kang(cserock@gmail.com) on 2016. 12. 8..
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class AnnieApiServiceTest {

	static final Logger log = LoggerFactory.getLogger(AnnieApiServiceTest.class);

	@Autowired
	private AnnieApiService annieApiService;

	/*
	@MockBean
	private AnnieApiService annieApiServiceMock;
	*/

	/*
	@Test
	public void AccountConnectionProductMockTest(){


		List<Product> productList = new ArrayList<>();
		Product product = new Product();
		product.setProductId("901633885");
		product.setProductName("코드씨씨엠");
		productList.add(product);

		AccountConnectionProduct accountConnectionProduct = new AccountConnectionProduct();
		accountConnectionProduct.setProducts(productList);

		try{
			given(annieApiServiceMock.getAccountConnectionProduct(0, 0)).willReturn(accountConnectionProduct);

			AccountConnectionProduct resultAccountConnectionProduct = annieApiServiceMock.getAccountConnectionProduct(0, 0);
			assertThat(resultAccountConnectionProduct.getProducts().get(0).getProductName()).isEqualTo("코드씨씨엠");

		} catch(Exception ex) {
			ex.printStackTrace();
		}

	}


	@Test
	public void AccountConnectionProductTest(){

		try{

			Product product = new Product();
			product.setProductId("901633885");
			product.setProductName("코드씨씨엠");

			AccountConnectionProduct accountConnectionProduct = annieApiService.getAccountConnectionProduct("165847", 0);

			assertThat(accountConnectionProduct.getProducts().get(0).getProductId()).isEqualTo(product.getProductId());
			assertThat(accountConnectionProduct.getProducts().get(0).getProductName()).isEqualTo(product.getProductName());

		} catch(Exception ex) {
			ex.printStackTrace();

		}

	}
	*/

	@Test
	public void getIAPListTest(){

		try{

			Product product = new Product();
			product.setProductId("901633885");
			product.setProductName("코드씨씨엠");

			IAPList iapList = annieApiService.getIAPList("165847", "901633885",0);

			log.debug(iapList.toString());

//			assertThat(accountConnectionProduct.getProducts().get(0).getProductId()).isEqualTo(product.getProductId());
//			assertThat(accountConnectionProduct.getProducts().get(0).getProductName()).isEqualTo(product.getProductName());

		} catch(Exception ex) {
			ex.printStackTrace();
		}


	}


}
