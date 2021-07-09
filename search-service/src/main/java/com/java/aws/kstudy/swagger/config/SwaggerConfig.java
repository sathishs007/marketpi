package com.java.aws.kstudy.swagger.config;

import java.util.Arrays;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.ParameterBuilder;
import springfox.documentation.schema.ModelRef;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2
public class SwaggerConfig {

	private ApiInfo apiInfo() {
		return new ApiInfoBuilder().title("Search API")
				.description("This Api help to View and search the company and stock details")
				.termsOfServiceUrl("http://test.com")
				.contact("test@gmail.com").license("804222 License")
				.licenseUrl("test@gmail.com").version("1.0").build();
	}
	@Bean
	public Docket postsApi() {
		return new Docket(DocumentationType.SWAGGER_2).globalOperationParameters(Arrays.asList(new ParameterBuilder().name("Authorisation").
				description("description of header").modelRef(new ModelRef("string")).parameterType("header").required(false).build()))
				.apiInfo(apiInfo()).select().build();
		
	}
}
