package com.gachon.UntactPhotoBooth;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;

@SpringBootApplication
public class UntactPhotoBoothApplication extends SpringBootServletInitializer {

	public static final String APPLICATION_LOCATIONS = "spring.config.location="
			+ "classpath:application.properties,"
			+ "classpath:aws.yml";
	//public static void main(String[] args) {
	//	SpringApplication.run(, args);
	//}

	public static void main(String[] args) {
		new SpringApplicationBuilder(UntactPhotoBoothApplication.class)
				.properties(APPLICATION_LOCATIONS)
				.run(args);
	}

	@Override
	protected SpringApplicationBuilder configure(SpringApplicationBuilder builder){
		return builder.sources(UntactPhotoBoothApplication.class);
	}

}
