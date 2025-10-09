package com.xworkz.module.configuration;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;

@Slf4j
public class HospitalConfigurationInitial extends AbstractAnnotationConfigDispatcherServletInitializer {

    public HospitalConfigurationInitial(){
        log.info("Dispatcher servlet and spring is connected..........");
    }

    @Override
    protected Class<?>[] getRootConfigClasses() {
        return new Class[0];
    }

    @Override
    protected Class<?>[] getServletConfigClasses() {
        return new Class[]{HospitalConfiguration.class, DatabaseConfig.class, AuditConfig.class};
    }

    @Override
    protected String[] getServletMappings() {
        return new String[]{"/"};
    }
}
