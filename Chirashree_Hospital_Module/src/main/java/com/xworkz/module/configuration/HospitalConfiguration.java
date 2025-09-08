package com.xworkz.module.configuration;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.web.servlet.config.annotation.DefaultServletHandlerConfigurer;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ViewResolverRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import javax.sql.DataSource;

@Configuration
@EnableWebMvc
@Slf4j
@ComponentScan(basePackages = "com.xworkz.module")
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
        registry.jsp("/WEB-INF/views/",".jsp");
    }

//    @Bean
//    public LocalContainerEntityManagerFactoryBean localContainerEntityManagerFactoryBean(){
//        LocalContainerEntityManagerFactoryBean factoryBean = new LocalContainerEntityManagerFactoryBean();
//        factoryBean.setDataSource(dataSource());
//        factoryBean.setPackagesToScan("com.xworkz.module.entity");
//        factoryBean.setJpaVendorAdapter(new HibernateJpaVendorAdapter());
//        return factoryBean;
//    }
//
//    @Bean
//    public DataSource dataSource(){
//        DriverManagerDataSource source = new DriverManagerDataSource();
//        source.setDriverClassName("com.mysql.cj.jdbc.Driver");
//        source.setUrl("jdbc:mysql://localhost:3306/module");
//        source.setUsername("root");
//        source.setPassword("Chir@#$123");
//        return source;
//    }
}
