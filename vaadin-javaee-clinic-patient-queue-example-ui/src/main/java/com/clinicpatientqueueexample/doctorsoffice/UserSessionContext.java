package com.clinicpatientqueueexample.doctorsoffice;

import com.clinicpatientqueueexample.doctors.Doctor;
import com.vaadin.cdi.VaadinSessionScoped;

import java.io.Serializable;

@VaadinSessionScoped
public class UserSessionContext implements Serializable {

    private Doctor doctor;

    public Doctor getDoctor() {
        return doctor;
    }

    public void setDoctor(Doctor doctor) {
        if (this.doctor != null && doctor != null) {
            throw new RuntimeException("Doctor can be set only if previously uninitialized during a session");
        }
        this.doctor = doctor;
    }
}
