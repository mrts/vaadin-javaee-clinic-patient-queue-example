package com.clinicpatientqueueexample.infoscreen;

import com.clinicpatientqueueexample.patients.Registration;
import com.clinicpatientqueueexample.patients.RegistrationService;
import com.clinicpatientqueueexample.patients.RegistrationStatus;

import javax.enterprise.context.Dependent;
import javax.inject.Inject;
import java.util.List;

@Dependent
public class InfoscreenPresenter {

    public static final String INFOSCREEN_ID = "infoscreen-singleton";

    @Inject
    private RegistrationService registrationService;

    public List<Registration> getCalledInRegistrations() {
        return registrationService.findByStatus(RegistrationStatus.CALLED_IN);
    }

}
