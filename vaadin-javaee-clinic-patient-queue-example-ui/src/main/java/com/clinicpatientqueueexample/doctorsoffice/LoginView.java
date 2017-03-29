package com.clinicpatientqueueexample.doctorsoffice;

import com.clinicpatientqueueexample.doctors.Doctor;
import com.clinicpatientqueueexample.doctors.DoctorService;
import com.vaadin.cdi.CDIView;
import com.vaadin.cdi.access.JaasAccessControl;
import com.vaadin.external.org.slf4j.Logger;
import com.vaadin.external.org.slf4j.LoggerFactory;
import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener;
import com.vaadin.ui.LoginForm;
import com.vaadin.ui.Notification;

import javax.inject.Inject;
import javax.servlet.ServletException;

import static com.vaadin.ui.Notification.Type.ERROR_MESSAGE;

@CDIView(LoginView.VIEW_NAME)
public class LoginView extends LoginForm implements View {

    public static final String VIEW_NAME = "login";

    private static final Logger logger = LoggerFactory.getLogger(LoginView.class);

    @Inject
    private DoctorService doctorService;

    @Inject
    private UserSessionContext sessionContext;

    @Override
    public void enter(ViewChangeListener.ViewChangeEvent viewChangeEvent) {
        addLoginListener(loginEvent -> onLogin(loginEvent));
    }

    private void onLogin(LoginEvent loginEvent) {
        final String username = loginEvent.getLoginParameter("username");
        final String password = loginEvent.getLoginParameter("password");

        final Doctor doctor = doctorService.findOne(username);
        if (doctor == null) {
            final String message = "Unknown doctor: " + username;
            Notification.show(message, ERROR_MESSAGE);
            logger.warn(message);
            return;
        }

        try {
            JaasAccessControl.login(username, password);
            Notification.show("Login successful");
            sessionContext.setDoctor(doctor);
            getUI().getNavigator().navigateTo(DoctorsOfficeViewImpl.VIEW_NAME);

        } catch (ServletException e) {
            final String message = "User '" + username + "' login failed";
            Notification.show(message, ERROR_MESSAGE);
            logger.warn(message, e);
        }
    }

}
