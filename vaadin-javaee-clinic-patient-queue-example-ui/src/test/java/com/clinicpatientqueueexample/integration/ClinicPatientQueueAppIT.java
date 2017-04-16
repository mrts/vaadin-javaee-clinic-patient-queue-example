package com.clinicpatientqueueexample.integration;

import com.clinicpatientqueueexample.doctorsoffice.DoctorsOfficeViewImpl;
import com.clinicpatientqueueexample.infoscreen.InfoscreenViewImpl;
import com.clinicpatientqueueexample.registrationkiosk.RegistrationKioskView;
import com.vaadin.testbench.annotations.RunLocally;
import com.vaadin.testbench.elements.ButtonElement;
import com.vaadin.testbench.elements.GridElement;
import com.vaadin.testbench.elements.TextFieldElement;
import com.vaadin.testbench.parallel.Browser;
import com.vaadin.testbench.parallel.ParallelTest;
import org.junit.Test;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

import static org.assertj.core.api.Assertions.assertThat;

@RunLocally(Browser.CHROME)
public class ClinicPatientQueueAppIT extends ParallelTest {

    private static String BASE_URL = "http://localhost:8080/vaadin-javaee-clinic-patient-queue-example-ui-1.0-SNAPSHOT/";
    private static final String REGISTRATION_KIOSK_URL = BASE_URL + RegistrationKioskView.VIEW_NAME;
    private static final String DOCTORS_OFFICE_URL = BASE_URL + DoctorsOfficeViewImpl.VIEW_NAME;
    private static final String INFOSCREEN_URL = BASE_URL + InfoscreenViewImpl.VIEW_NAME;

    private static final BlockingQueue WAIT_FOR_REGISTRATION_QUEUE = new ArrayBlockingQueue(2);
    private static final BlockingQueue WAIT_FOR_CALL_IN_QUEUE = new ArrayBlockingQueue(2);

    @Test
    public void registerPatientToAppointment() throws InterruptedException {
        getDriver().get(REGISTRATION_KIOSK_URL);

        registerPatientAtRegistrationKiosk("Patsient Peeter");

        notifyRegistrationCompleted();

        // keep the window open so that user sees the result
        Thread.sleep(2000);
    }

    @Test
    public void callInPatient() throws InterruptedException {
        getDriver().get(DOCTORS_OFFICE_URL);

        loginToDoctorsOffice("user", "user");

        waitUntilRegistrationCompleted();

        callPatientInAtDoctorsOffice();

        notifyCallInCompleted();

        // keep the window open so that user sees the result
        Thread.sleep(2000);
    }

    @Test
    public void verifyPatientOnInfoscreen() throws InterruptedException {
        getDriver().get(INFOSCREEN_URL);

        waitUntilCallInCompleted();

        final GridElement.GridRowElement row = $(GridElement.class).first().getRow(0);

        assertThat(row.getCell(0).getText()).isEqualTo("1");
        assertThat(row.getCell(1).getText()).isEqualTo("103");

        // keep the window open so that user sees the result
        Thread.sleep(2000);
    }

    private void registerPatientAtRegistrationKiosk(String patientName) {
        $(TextFieldElement.class).first().setValue(patientName);
        $(ButtonElement.class).get(3).click();
    }

    private void callPatientInAtDoctorsOffice() {
        $(GridElement.class).first().getRow(0).click();
        $(ButtonElement.class).caption("Call in").first().click();
    }

    private void loginToDoctorsOffice(String username, String password) {
        $(TextFieldElement.class).get(0).setValue(username);
        $(TextFieldElement.class).get(1).setValue(password);
        $(ButtonElement.class).first().click();
    }

    private void notifyRegistrationCompleted() throws InterruptedException {
        WAIT_FOR_REGISTRATION_QUEUE.put(this);
    }

    private void waitUntilRegistrationCompleted() throws InterruptedException {
        WAIT_FOR_REGISTRATION_QUEUE.take();
    }

    private void notifyCallInCompleted() throws InterruptedException {
        WAIT_FOR_CALL_IN_QUEUE.put(this);
    }

    private void waitUntilCallInCompleted() throws InterruptedException {
        WAIT_FOR_CALL_IN_QUEUE.take();
    }

}
