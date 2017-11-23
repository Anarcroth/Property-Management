package com.property.manager.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
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

	@Override
	public void addResourceHandlers(ResourceHandlerRegistry registry) {

		if (!registry.hasMappingForPattern("/static/**")) {
			registry.addResourceHandler("/static/**").addResourceLocations("classpath:/static/");
		}
	}

	@Override
	public void addCorsMappings(CorsRegistry registry) {

		registry.addMapping("/**");
	}
}