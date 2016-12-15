package com.skt.dlab;

import com.skt.dlab.domain.Error;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

/**
 * Created by Rock Kang(cserock@gmail.com) on 2016. 12. 6..
 */
@RestController
public class ApiController {

	@ExceptionHandler({MissingServletRequestParameterException.class, IllegalArgumentException.class})
	public ResponseEntity<?> handleBadRequests(Exception e) throws IOException {
		String message = e.getMessage();
		return new ResponseEntity<>(new Error(HttpStatus.BAD_REQUEST.value(), message), HttpStatus.BAD_REQUEST);
	}
}
