package com.clinicpatientqueueexample.patients;

import com.clinicpatientqueueexample.doctors.Doctor;

public interface RegistrationService {

    void registerPatientToDoctor(Patient patient, Doctor doctor);

}
