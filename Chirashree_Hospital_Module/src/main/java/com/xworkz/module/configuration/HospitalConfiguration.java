package com.xworkz.module.configuration;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;
import org.springframework.web.servlet.config.annotation.DefaultServletHandlerConfigurer;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ViewResolverRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import javax.sql.DataSource;
import java.util.Properties;

@Configuration
@EnableWebMvc
@Slf4j
@ComponentScan(basePackages = "com.xworkz.module")
@PropertySource("classpath:application.properties")
public class HospitalConfiguration implements WebMvcConfigurer {

    public HospitalConfiguration(){
        log.info("Configuration is connected...........");
    }

    @Override
    public void configureDefaultServletHandling(DefaultServletHandlerConfigurer configurer) {
        configurer.enable();
    }

    @Override
    public void configureViewResolvers(ViewResolverRegistry registry) {
        registry.jsp("/",".jsp");
    }


    @Bean
    public static PropertySourcesPlaceholderConfigurer propertySourcesPlaceholderConfigurer(){
        return new PropertySourcesPlaceholderConfigurer();
    }


    @Bean(name = "multipartResolver")
    public CommonsMultipartResolver commonsMultipartResolver() {
        CommonsMultipartResolver commonsMultipartResolver = new CommonsMultipartResolver();

        // Set to 5 MB (5 * 1024 * 1024 bytes)
        commonsMultipartResolver.setMaxUploadSize(5242880);

        // Set to 5 MB (or a suitable value)
        commonsMultipartResolver.setMaxInMemorySize(5242880);

        return commonsMultipartResolver;
    }
}
