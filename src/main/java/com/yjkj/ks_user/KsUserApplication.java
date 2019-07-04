package com.yjkj.ks_user;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.feign.EnableFeignClients;
import org.springframework.core.Ordered;
import org.springframework.web.servlet.config.annotation.PathMatchConfigurer;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;


//@EnableFeignClients
//@EnableDiscoveryClient
@SpringBootApplication
@MapperScan(basePackages = "com.yjkj.ks_user.mapper")
public class KsUserApplication extends WebMvcConfigurerAdapter {

    public static void main(String[] args) {
        SpringApplication.run(KsUserApplication.class, args);
    }

    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        registry.addViewController("/identification").setViewName("identification.html");
        registry.addViewController("/register").setViewName("register1-7.html");
        registry.addViewController("/KSxieyi").setViewName("KSxieyi.html");
        registry.setOrder(Ordered.HIGHEST_PRECEDENCE);
    }

    @Override
    public void configurePathMatch(PathMatchConfigurer configurer) {
        super.configurePathMatch(configurer);
        configurer.setUseSuffixPatternMatch(true)
                .setUseTrailingSlashMatch(true);
    }

}
