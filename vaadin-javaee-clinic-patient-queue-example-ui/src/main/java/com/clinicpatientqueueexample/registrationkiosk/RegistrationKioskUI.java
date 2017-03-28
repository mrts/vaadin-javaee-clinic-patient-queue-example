package com.clinicpatientqueueexample.registrationkiosk;

import com.clinicpatientqueueexample.common.ClinicPatientQueueExampleUIBase;
import com.vaadin.cdi.CDIUI;
import com.vaadin.server.VaadinRequest;

@CDIUI("registration-kiosk")
public class RegistrationKioskUI extends ClinicPatientQueueExampleUIBase {

    @Override
    protected void init(VaadinRequest vaadinRequest) {
        setupNavigator();
        navigator.navigateTo(RegistrationKioskViewImpl.VIEW_NAME);
    }

}
