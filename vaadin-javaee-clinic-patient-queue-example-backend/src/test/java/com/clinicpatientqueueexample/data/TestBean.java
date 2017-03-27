package com.clinicpatientqueueexample.data;

public class TestBean extends Entity {

    private static int counter = 0;

    public TestBean() {
        id = String.valueOf(++counter);
    }

    @Override
    public String toString() {
        return "{" + getClass().getSimpleName() + ":id=" + getId() + "}";
    }
}
