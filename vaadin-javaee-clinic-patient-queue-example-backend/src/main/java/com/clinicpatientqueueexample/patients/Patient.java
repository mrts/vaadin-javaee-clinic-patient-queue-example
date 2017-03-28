package com.clinicpatientqueueexample.patients;

import com.clinicpatientqueueexample.data.Entity;

public class Patient extends Entity {

    private String name;

    public Patient(String number, String name) {
        setId(number);
        setName(name);
    }

    public Patient() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

}
