package com.clinicpatientqueueexample.common;

import com.vaadin.cdi.access.JaasAccessControl;
import com.vaadin.external.org.slf4j.Logger;
import com.vaadin.external.org.slf4j.LoggerFactory;

import javax.enterprise.context.ApplicationScoped;
import javax.servlet.ServletException;

@ApplicationScoped
public class PrincipalServiceImpl implements PrincipalService {

    private static final Logger logger = LoggerFactory.getLogger(PrincipalServiceImpl.class);

    @Override
    public String getUsername() {
        return JaasAccessControl.getCurrentRequest().getUserPrincipal().getName();
    }

    @Override
    public void logout() {
        try {
            JaasAccessControl.logout();
        } catch (ServletException e) {
            logger.error("JAAS logout failed", e);
        }
    }

}
