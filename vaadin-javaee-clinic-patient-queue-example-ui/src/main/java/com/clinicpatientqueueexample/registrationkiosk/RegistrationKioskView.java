package com.clinicpatientqueueexample.registrationkiosk;

import com.clinicpatientqueueexample.patients.Patient;

public interface RegistrationKioskView {

    String VIEW_NAME = "registration-kiosk";

    Patient getPatient();

}
