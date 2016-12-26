package com.skt.dlab;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.support.SpringBootServletInitializer;

/**
 * Created by Rock Kang(cserock@gmail.com) on 2016. 12. 6..
 */

@SpringBootApplication
public class AnnieApplication extends SpringBootServletInitializer {

	@Override
	protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
		return application.sources(AnnieApplication.class);
	}

	public static void main(String[] args) {
		SpringApplication.run(AnnieApplication.class, args);
	}
}
