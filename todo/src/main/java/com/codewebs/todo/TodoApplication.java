package com.codewebs.todo;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.web.bind.annotation.RestController;




@SpringBootApplication
@ComponentScan(basePackages = { "com.codewebs.todo" })
public class TodoApplication extends SpringBootServletInitializer {
	
	
	@Override
	protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
		return application.sources(TodoApplication.class);
	}
	public static void main(String[] args) {
		SpringApplication.run(TodoApplication.class, args);
	}

//	@Bean
//	public InternalResourceViewResolver getViewResolver() {
//		InternalResourceViewResolver viewResolver = new InternalResourceViewResolver();
//		viewResolver.setPrefix("/WEB-INF/jsp/");
//		// viewResolver.setPrefix("/resources/css/");
//		viewResolver.setSuffix(".jsp");
//		// viewResolver.setSuffix(".css");
//		return viewResolver;
//	}

}
