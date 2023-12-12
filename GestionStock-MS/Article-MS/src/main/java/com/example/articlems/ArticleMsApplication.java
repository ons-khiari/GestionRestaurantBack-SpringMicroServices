package com.example.articlems;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class ArticleMsApplication {

	public static void main(String[] args) {
		SpringApplication.run(ArticleMsApplication.class, args);
	}
	
	@Autowired
	private ArticleRepository repository;
	
	@Bean
	ApplicationRunner init() {
		return (args) -> {
			// save
			

		};
	}

}
