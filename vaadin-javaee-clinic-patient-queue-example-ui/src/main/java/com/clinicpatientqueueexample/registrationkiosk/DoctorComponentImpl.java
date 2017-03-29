package com.clinicpatientqueueexample.registrationkiosk;

import com.clinicpatientqueueexample.doctors.Doctor;

public class DoctorComponentImpl extends DoctorComponentDesign {

    public DoctorComponentImpl(Doctor doctor, RegistrationKioskView view,
            RegistrationKioskPresenter presenter) {
        doctorNameLabel.setValue(doctor.getName());
        registerButton.addClickListener(e -> presenter.registerToDoctor(doctor));
    }

}
