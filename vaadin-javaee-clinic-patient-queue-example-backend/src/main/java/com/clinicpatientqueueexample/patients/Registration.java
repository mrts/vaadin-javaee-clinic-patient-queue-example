package com.clinicpatientqueueexample.patients;

import com.clinicpatientqueueexample.data.Entity;
import com.clinicpatientqueueexample.doctors.Doctor;

import java.util.UUID;

public class Registration extends Entity {

    private final Doctor doctor;
    private final Patient patient;

    private RegistrationStatus status = RegistrationStatus.WAITING;

    public Registration(UUID uuid, Patient patient, Doctor doctor) {
        this.id = uuid.toString();
        this.patient = patient;
        this.doctor = doctor;
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

    public Patient getPatient() {
        return patient;
    }
}
