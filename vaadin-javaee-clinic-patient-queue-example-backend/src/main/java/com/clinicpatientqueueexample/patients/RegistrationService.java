package com.clinicpatientqueueexample.patients;

import com.clinicpatientqueueexample.doctors.Doctor;

import java.util.List;
import java.util.Optional;

public interface RegistrationService {

    void registerPatientToDoctor(Patient patient, Doctor doctor);

    List<Registration> findByDoctor(Doctor doctor);

    Optional<Registration> findByPatient(Patient patient);

}
