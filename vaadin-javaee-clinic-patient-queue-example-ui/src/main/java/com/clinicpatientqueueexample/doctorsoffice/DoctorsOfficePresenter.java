package com.clinicpatientqueueexample.doctorsoffice;

import com.clinicpatientqueueexample.common.PrincipalService;
import com.clinicpatientqueueexample.common.ViewNavigator;
import com.clinicpatientqueueexample.common.ViewNotification;
import com.clinicpatientqueueexample.doctors.DoctorService;
import com.clinicpatientqueueexample.patients.Registration;
import com.clinicpatientqueueexample.patients.RegistrationService;
import com.clinicpatientqueueexample.patients.RegistrationStatus;

import javax.enterprise.context.Dependent;
import javax.inject.Inject;
import java.util.List;

@Dependent
public class DoctorsOfficePresenter {

    @Inject
    private PrincipalService principalService;

    @Inject
    private DoctorService doctorService;

    @Inject
    private RegistrationService registrationService;

    @Inject
    private UserSessionContext sessionContext;

    @Inject
    private ViewNavigator navigator;

    @Inject
    private ViewNotification notification;

    public void logout() {
        principalService.logout();
        navigator.navigateTo(LoginView.VIEW_NAME);
    }

    public List<Registration> getDoctorsRegistrations() {
        return registrationService.findByDoctor(sessionContext.getDoctor());
    }

    public void callInPatient(Registration registration) {
        registration.setStatus(RegistrationStatus.CALLED_IN);
        notification.showMessage("Calling in patient " + registration.getPatient().getName());
    }

    public String getDoctorName() {
        return sessionContext.getDoctor().getName();
    }

}
