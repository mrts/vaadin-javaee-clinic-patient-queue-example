package com.clinicpatientqueueexample.doctors;

import com.clinicpatientqueueexample.data.CrudService;

import javax.annotation.PostConstruct;
import javax.enterprise.context.ApplicationScoped;
import java.util.Arrays;
import java.util.List;

@ApplicationScoped
public class DoctorServiceImpl implements DoctorService {

    private CrudService<Doctor> service = new CrudService<>();

    @Override
    public List<Doctor> findAll() {
        return service.findAll();
    }

    @Override
    public Doctor findOne(String username) {
        return service.findById(username);
    }

    @PostConstruct
    private void fillServiceWithData() {
        Arrays.asList(new Doctor("drtaivo", "Dr. Taivo Lõoke", "102"),
                new Doctor("user", "Dr. Anu Võsu", "103"),
                new Doctor("drjaakob", "Dr. Jaakob Klavan", "104"),
                new Doctor("drmae", "Dr. Mae Parmas", "105")).stream()
                .forEach(doctor -> service.save(doctor));
    }

}
