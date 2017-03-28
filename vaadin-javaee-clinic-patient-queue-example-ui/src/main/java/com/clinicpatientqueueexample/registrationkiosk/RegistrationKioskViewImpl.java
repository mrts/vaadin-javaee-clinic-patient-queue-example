package com.clinicpatientqueueexample.registrationkiosk;

import com.clinicpatientqueueexample.doctors.Doctor;
import com.clinicpatientqueueexample.patients.Patient;
import com.vaadin.cdi.CDIView;
import com.vaadin.data.Binder;
import com.vaadin.data.validator.StringLengthValidator;
import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener;

import javax.inject.Inject;
import java.util.ArrayList;
import java.util.List;

@CDIView(RegistrationKioskView.VIEW_NAME)
public class RegistrationKioskViewImpl extends RegistrationKioskDesign
        implements RegistrationKioskView, View {

    private Patient patient = new Patient();

    private List<DoctorComponentImpl> doctorComponents = new ArrayList<>();

    @Inject
    private RegistrationKioskPresenter presenter;

    @Override
    public void enter(ViewChangeListener.ViewChangeEvent viewChangeEvent) {
        presenter.setView(this);
        patientName.focus();
        bindPatientNameField();
        fillDoctorGrid(presenter.getAllDoctors());
    }

    @Override
    public Patient getPatient() {
        return patient;
    }

    private void bindPatientNameField() {
        final Binder<Patient> binder = new Binder<>();
        binder.setBean(patient);
        binder.forField(patientName).withValidator(new StringLengthValidator("Too short", 3, 200))
                .bind(Patient::getName, (p, name) -> {
                    p.setName(name);
                    doctorComponents.stream().forEach(component -> component.registerButton.setEnabled(true));
                });
    }

    private void fillDoctorGrid(List<Doctor> allDoctors) {
        allDoctors.stream().map(doctor -> new DoctorComponentImpl(doctor, presenter))
                .forEach(doctorComponent -> {
                    doctorComponent.registerButton.setEnabled(false);
                    doctorComponents.add(doctorComponent);
                    doctorGridLayout.addComponent(doctorComponent);
                });
    }

}
