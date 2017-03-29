package com.clinicpatientqueueexample.doctorsoffice;

import com.clinicpatientqueueexample.doctors.Doctor;

import javax.enterprise.context.SessionScoped;
import java.io.Serializable;

@SessionScoped
public class UserSessionContext implements Serializable {

    private Doctor doctor;

    public Doctor getDoctor() {
        return doctor;
    }

    public void setDoctor(Doctor doctor) {
        this.doctor = doctor;
    }
}
