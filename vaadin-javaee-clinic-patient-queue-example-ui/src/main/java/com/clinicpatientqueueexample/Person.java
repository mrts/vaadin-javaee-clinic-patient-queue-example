package com.clinicpatientqueueexample;

/**
 * This is a dummy example entity to be used with the CrudService from
 * backend module.
 */
public class Person {
    private String name;

    public Person() {
    }

    public Person(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}