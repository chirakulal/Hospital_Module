package com.xworkz.module.audit;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.ObjectFactory;
import org.springframework.data.domain.AuditorAware;

import javax.servlet.http.HttpSession;
import java.util.Optional;

@Slf4j
public class AuditorAwareImpl implements AuditorAware<String> {

    private final ObjectFactory<HttpSession> sessionFactory;

    public AuditorAwareImpl(ObjectFactory<HttpSession> sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public Optional<String> getCurrentAuditor() {

        try {
            HttpSession currentSession = sessionFactory.getObject();
            String email = (String) currentSession.getAttribute("loginEmail");
            log.info("Current auditor email from session: {}", email);
            return Optional.ofNullable(email);
        }catch (IllegalStateException e){

            log.warn("Auditing operation outside of an HTTP request context. Defaulting to 'SYSTEM'.");
            return Optional.of("SYSTEM");
        }catch (Exception e){
            log.error("Error retrieving auditor from session.", e);
            return Optional.of("SYSTEM_ERROR");
        }

    }
}
