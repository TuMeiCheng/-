package com.wandeyun.wuyi.website;

import org.intellij.lang.annotations.RegExp;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.MultipartConfigFactory;
import org.springframework.boot.web.support.SpringBootServletInitializer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.MultipartConfigElement;

@EnableJpaAuditing
@SpringBootApplication
@RequestMapping("/")
@MapperScan("com.wandeyun.wuyi.website.mapper")
public class WebsiteApplication extends SpringBootServletInitializer {
	@Override
	protected SpringApplicationBuilder configure(SpringApplicationBuilder builder) {
		return builder.sources(WebsiteApplication.class);
	}

	public static void main(String[] args) {
		SpringApplication.run(WebsiteApplication.class, args);
	}

	/**
	 * 文件上传配置
	 * @return
	 */
	@Bean
	public MultipartConfigElement multipartConfigElement() {
		MultipartConfigFactory factory = new MultipartConfigFactory();
		//单个文件最大( 20mb )
		factory.setMaxFileSize("20480KB"); //KB,MB
		/// 设置总上传数据总大小 (100mb)
		factory.setMaxRequestSize("102400KB");
		return factory.createMultipartConfig();
	}
}
