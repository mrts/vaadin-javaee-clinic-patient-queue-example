package com.clinicpatientqueueexample.patients;

import com.clinicpatientqueueexample.data.Entity;
import com.clinicpatientqueueexample.doctors.Doctor;

public class Registration extends Entity {

    private Doctor doctor;
    private Patient patient;
    private RegistrationStatus status = RegistrationStatus.WAITING;

    public Registration(Doctor doctor, Patient patient) {
        this.doctor = doctor;
        this.patient = patient;
    }

    public RegistrationStatus getStatus() {
        return status;
    }

    public void setStatus(RegistrationStatus status) {
        this.status = status;
    }

    public Doctor getDoctor() {
        return doctor;
    }

    public void setDoctor(Doctor doctor) {
        this.doctor = doctor;
    }

    public Patient getPatient() {
        return patient;
    }

    public void setPatient(Patient patient) {
        this.patient = patient;
    }
}
