package com.clinicpatientqueueexample.doctors;

import java.util.List;

public interface DoctorService {

    List<Doctor> findAll();

    Doctor findOne(String username);

}
