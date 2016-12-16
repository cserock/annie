package com.skt.dlab;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.bind.annotation.RequestMethod;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.builders.ResponseMessageBuilder;
import springfox.documentation.schema.ModelRef;
import springfox.documentation.service.ResponseMessage;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * Created by Rock Kang(cserock@gmail.com) on 2016. 12. 6..
 */
@Configuration
@EnableSwagger2
public class SwaggerConfig {
	@Bean
	public Docket api() {

		ArrayList<ResponseMessage> responseMessages = new ArrayList<>(
				Arrays.asList(
						new ResponseMessageBuilder()
							.code(401)
							.message("Unauthorized")
							.responseModel(new ModelRef("Error"))
							.build(),
						new ResponseMessageBuilder()
								.code(403)
								.message("Forbidden")
								.responseModel(new ModelRef("Error"))
								.build(),
						new ResponseMessageBuilder()
								.code(404)
								.message("Not Found")
								.responseModel(new ModelRef("Error"))
								.build(),
						new ResponseMessageBuilder()
								.code(500)
								.message("Failure")
								.responseModel(new ModelRef("Error"))
								.build()
						)
		);

		return new Docket(DocumentationType.SWAGGER_2)
				.select()
				.apis(RequestHandlerSelectors.basePackage("com.skt.dlab"))
				.paths(PathSelectors.ant("/api/**"))
				.build()
				.useDefaultResponseMessages(false)
				.globalResponseMessage(RequestMethod.GET, responseMessages);
	}
}
