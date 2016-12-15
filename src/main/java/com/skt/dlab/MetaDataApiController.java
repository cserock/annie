package com.skt.dlab;

import com.skt.dlab.api.meta_data.CountryList;
import com.skt.dlab.domain.Error;
import com.skt.dlab.service.MetaDataApiService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.HttpClientErrorException;

/**
 * Created by Rock Kang(cserock@gmail.com) on 2016. 12. 15..
 */
@Api(value = "MetaDataApi", description = "Meta Data APIs")
@RestController
@RequestMapping("/api/meta")
public class MetaDataApiController extends ApiController {

	static final Logger log = LoggerFactory.getLogger(MetaDataApiController.class);

	@Autowired MetaDataApiService metaDataApiService;

	@ApiOperation(value = "Retrieve the list of countries, regions")
	@RequestMapping(value="/countries", method= RequestMethod.GET, produces = "application/json")
	public ResponseEntity<?> countries(){

		try{

			CountryList countryList = metaDataApiService.getCountries();

			if(countryList.getCountries().isEmpty()){
				return new ResponseEntity<>(countryList, HttpStatus.NOT_FOUND);
			}

			return new ResponseEntity<>(countryList, HttpStatus.OK);

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