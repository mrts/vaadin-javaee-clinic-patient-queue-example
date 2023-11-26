package com.clinicpatientqueueexample.patients;

import com.clinicpatientqueueexample.data.CrudService;

import javax.enterprise.context.ApplicationScoped;

import java.util.concurrent.atomic.AtomicInteger;

@ApplicationScoped
public class PatientServiceImpl implements PatientService {

    private static final AtomicInteger patientNumberGenerator = new AtomicInteger(1);

    private final CrudService<Patient> service = new CrudService<>();

    @Override
    public Patient save(Patient patient) {
        patient.setId(String.valueOf(patientNumberGenerator.getAndIncrement()));
        service.save(patient);
        return patient;
    }

}
