package com.clinicpatientqueueexample.backend;

public class TestBean {

    private static int counter = 0;

    private final int id;

    public TestBean() {
        id = ++counter;
    }

    public int getId() {
        return id;
    }

    @Override
    public String toString() {
        return "{" + getClass().getSimpleName() + ":id=" + getId() + "}";
    }
}
