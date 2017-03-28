package com.clinicpatientqueueexample.patients;

import com.clinicpatientqueueexample.data.CrudService;

import javax.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class PatientServiceImpl implements PatientService {

    private static int patientNumberGenerator = 1;

    private CrudService<Patient> service = new CrudService<>();

    @Override
    public Patient save(Patient patient) {
        patient.setId(String.valueOf(patientNumberGenerator++));
        service.save(patient);
        return patient;
    }

}
