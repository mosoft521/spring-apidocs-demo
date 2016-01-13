package com.example;

import com.example.controllers.PersonController;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@EnableSwagger2
@ComponentScan(basePackageClasses = {
		PersonController.class
})
@SpringBootApplication
public class ApiDocumentationDemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(ApiDocumentationDemoApplication.class, args);
	}
}
