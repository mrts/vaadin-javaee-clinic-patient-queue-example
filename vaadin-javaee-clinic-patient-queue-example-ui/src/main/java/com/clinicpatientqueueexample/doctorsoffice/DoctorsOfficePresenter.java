package com.clinicpatientqueueexample.doctorsoffice;

import com.clinicpatientqueueexample.common.PrincipalService;
import com.clinicpatientqueueexample.common.ViewNavigator;
import com.clinicpatientqueueexample.common.ViewNotification;
import com.clinicpatientqueueexample.messaging.MessageSenderBean;
import com.clinicpatientqueueexample.patients.Patient;
import com.clinicpatientqueueexample.patients.Registration;
import com.clinicpatientqueueexample.patients.RegistrationService;
import com.clinicpatientqueueexample.patients.RegistrationStatus;

import javax.ejb.EJB;
import javax.enterprise.context.Dependent;
import javax.inject.Inject;
import java.util.List;

@Dependent
public class DoctorsOfficePresenter {

    @EJB
    private MessageSenderBean messageSenderBean;

    @Inject
    private PrincipalService principalService;

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
        final Patient patient = registration.getPatient();
        messageSenderBean.sendCallInMessage(getDoctorID() + ":Calling in patient #" + patient.getId());
        notification.showMessage("Calling in patient " + patient.getName());
    }

    public String getDoctorName() {
        return sessionContext.getDoctor().getName();
    }

    public String getDoctorOffice() {
        return sessionContext.getDoctor().getOffice();
    }

    public String getDoctorID() { return sessionContext.getDoctor().getId(); }

}
