package com.xworkz.module.configuration;

import com.zaxxer.hikari.HikariDataSource;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;

import javax.sql.DataSource;
import java.util.Properties;

@Configuration
@ComponentScan(basePackages = "com.xworkz.module")
@PropertySource("classpath:application.properties")
public class DatabaseConfig {

    @Bean
   public LocalContainerEntityManagerFactoryBean entityManagerFactoryBean(){
       LocalContainerEntityManagerFactoryBean factoryBean = new LocalContainerEntityManagerFactoryBean();
       factoryBean.setDataSource(dataSource());
       factoryBean.setPackagesToScan("com.xworkz.module.entity");
       factoryBean.setJpaVendorAdapter(new org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter());

        Properties jpaProps = new Properties();
        jpaProps.put("hibernate.hbm2ddl.auto", "update"); // creates/updates tables
        jpaProps.put("hibernate.show_sql", "true");
        jpaProps.put("hibernate.dialect", "org.hibernate.dialect.MySQL8Dialect");
        factoryBean.setJpaProperties(jpaProps);
       return factoryBean;
   }

    @Value("${db.driver}")
    private String driver;

    @Value("${db.url}")
    private String url;

    @Value("${db.username}")
    private String username;

    @Value("${db.password}")
    private String password;

    @Bean
    public DataSource dataSource() {
        HikariDataSource dataSource=new HikariDataSource();
        dataSource.setDriverClassName(driver);
        dataSource.setUsername(username);
        dataSource.setPassword(password);
        dataSource.setJdbcUrl(url);
        return dataSource;
    }


}
