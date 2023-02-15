package org.avismart;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.thymeleaf.spring5.SpringTemplateEngine;
import org.thymeleaf.spring5.view.ThymeleafViewResolver;
import org.thymeleaf.templateresolver.ClassLoaderTemplateResolver;

@ComponentScan(basePackages = { AvismartApplication.MAIN_GROUP })
@EnableJpaRepositories(basePackages = { AvismartApplication.MAIN_GROUP })
@EntityScan(basePackages = { AvismartApplication.MAIN_GROUP })
@SpringBootApplication
public class AvismartApplication extends SpringBootServletInitializer {
	
	public static final String MAIN_GROUP = "org.avismart";

	public static void main(String[] args) {
		SpringApplication.run(AvismartApplication.class, args);
	}
	
	
	@Override
	protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
		return application.sources(AvismartApplication.class);
	}
	
	@Bean(name = "templateResolver")
	public ClassLoaderTemplateResolver getTemplateResolver() {
		ClassLoaderTemplateResolver templateResolver = new ClassLoaderTemplateResolver();
		templateResolver.setPrefix("/templates/WEB-INF/views/thymeleaf/");
		templateResolver.setSuffix(".html");
		templateResolver.setTemplateMode("XHTML");
		templateResolver.setCacheable(false);
        templateResolver.setOrder(1);
		return templateResolver;
	}
	
	@Bean(name = "templateEngine")
	public SpringTemplateEngine getTemplateEngine() {
		SpringTemplateEngine templateEngine = new SpringTemplateEngine();
		templateEngine.setTemplateResolver(getTemplateResolver());
		return templateEngine;
	}
	
	

	@Bean(name = "viewResolver")
	public ThymeleafViewResolver getViewResolver() {
		ThymeleafViewResolver viewResolver = new ThymeleafViewResolver();
		viewResolver.setTemplateEngine(getTemplateEngine());
		viewResolver.setCharacterEncoding("UTF-8");
		viewResolver.setOrder(0);
		return viewResolver;
	}


}
