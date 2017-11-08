package com.property.manager.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

@Configuration
public class WebMvcConfig extends WebMvcConfigurerAdapter {

	/**
	 * Configure automated controllers, pre-configured with the response status code and/or view, to render the response
	 * body.
	 *
	 * @param registry
	 * 		Spring Boot registry class for view Controllers
	 */
	@Override
	public void addViewControllers(ViewControllerRegistry registry) {

		registry.addViewController("/log").setViewName("login");
		registry.addRedirectViewController("", "/log");
		registry.addRedirectViewController(" ", "/log");
		registry.addRedirectViewController("/", "/log");
		registry.addRedirectViewController("/login", "/log");
		registry.addRedirectViewController("/login/", "/log");
	}
	//
	//	@Bean
	//	public TemplateResolver templateResolver() {
	//		TemplateResolver templateResolver = new ClassLoaderTemplateResolver();
	//		templateResolver.setPrefix("/resources/templates/");
	//		templateResolver.setCacheable(false);
	//		templateResolver.setSuffix(".html");
	//		templateResolver.setTemplateMode("HTML5");
	//		return templateResolver;
	//	}

	@Override
	public void addCorsMappings(CorsRegistry registry) {

		registry.addMapping("/**");
	}
}