package com.clinicpatientqueueexample.doctors;

import com.clinicpatientqueueexample.data.Entity;

public class Doctor extends Entity {

    private String name;
    private String office;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getOffice() {
        return office;
    }

    public void setOffice(String office) {
        this.office = office;
    }
}
