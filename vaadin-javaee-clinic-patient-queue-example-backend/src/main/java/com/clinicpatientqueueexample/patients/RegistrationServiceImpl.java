package com.clinicpatientqueueexample.patients;

import com.clinicpatientqueueexample.data.CrudService;
import com.clinicpatientqueueexample.doctors.Doctor;

import javax.enterprise.context.ApplicationScoped;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@ApplicationScoped
public class RegistrationServiceImpl implements RegistrationService {

    private CrudService<Registration> service = new CrudService<>();

    @Override
    public void registerPatientToDoctor(Patient patient, Doctor doctor) {
        service.save(new Registration(UUID.randomUUID(), patient, doctor));
    }

    @Override
    public List<Registration> findByDoctor(Doctor doctor) {
        return service.findAll().stream()
                .filter(registration -> registration.getDoctor().getId() == doctor.getId())
                .collect(Collectors.toList());
    }

    @Override
    public Optional<Registration> findByPatient(Patient patient) {
        return service.findAll().stream()
                .filter(registration -> registration.getPatient().getId() == patient.getId()).findFirst();
    }

}
