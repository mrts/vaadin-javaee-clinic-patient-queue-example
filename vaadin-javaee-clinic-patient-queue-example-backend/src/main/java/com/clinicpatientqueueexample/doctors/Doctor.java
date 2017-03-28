package com.clinicpatientqueueexample.doctors;

import com.clinicpatientqueueexample.data.Entity;

public class Doctor extends Entity {

    private final String name;
    private final String office;

    public Doctor(String id, String name, String office) {
        this.id = id;
        this.name = name;
        this.office = office;
    }

    public String getName() {
        return name;
    }

    public String getOffice() {
        return office;
    }

}
