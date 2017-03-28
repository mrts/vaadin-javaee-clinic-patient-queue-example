package com.clinicpatientqueueexample.patients;

import com.clinicpatientqueueexample.data.CrudService;
import com.clinicpatientqueueexample.doctors.Doctor;

import javax.enterprise.context.ApplicationScoped;
import java.util.UUID;

@ApplicationScoped
public class RegistrationServiceImpl implements RegistrationService {

    private CrudService<Registration> service = new CrudService<>();

    @Override
    public void registerPatientToDoctor(Patient patient, Doctor doctor) {
        service.save(new Registration(UUID.randomUUID(), patient, doctor));
    }

}
