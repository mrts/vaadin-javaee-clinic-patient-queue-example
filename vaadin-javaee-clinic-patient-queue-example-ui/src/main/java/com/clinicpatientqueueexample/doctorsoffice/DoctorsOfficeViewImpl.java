package com.clinicpatientqueueexample.doctorsoffice;

import com.clinicpatientqueueexample.common.Constants;
import com.clinicpatientqueueexample.messaging.RegistrationBroadcaster;
import com.clinicpatientqueueexample.patients.Registration;
import com.vaadin.cdi.CDIView;
import com.vaadin.data.provider.CallbackDataProvider;
import com.vaadin.data.provider.DataProvider;
import com.vaadin.data.provider.ListDataProvider;
import com.vaadin.external.org.slf4j.Logger;
import com.vaadin.external.org.slf4j.LoggerFactory;
import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener;

import javax.annotation.security.RolesAllowed;
import javax.inject.Inject;
import java.util.Optional;
import java.util.function.Consumer;

@CDIView(DoctorsOfficeViewImpl.VIEW_NAME)
@RolesAllowed(Constants.USERS_ROLE)
public class DoctorsOfficeViewImpl extends DoctorsOfficeDesign implements View, Consumer<String> {

    public static final String VIEW_NAME = "doctors-office";

    private static final Logger logger = LoggerFactory.getLogger(DoctorsOfficeViewImpl.class);

    @Inject
    private RegistrationBroadcaster broadcaster;

    @Inject
    private DoctorsOfficePresenter presenter;

    private Optional<Registration> selectedRegistration;

    ListDataProvider<Registration> registrationDataProvider;

    @Override
    public void enter(ViewChangeListener.ViewChangeEvent viewChangeEvent) {
        doctorNameLabel.setValue(presenter.getDoctorName());
        doctorsOfficeNameLabel.setValue("Doctor's office #" + presenter.getDoctorOffice());

        logoutButton.addClickListener(e -> presenter.logout());

        callInButton.addClickListener(e -> selectedRegistration.ifPresent(registration -> {
            presenter.callInPatient(registration);
            registrationDataProvider.refreshItem(registration);
        }));
        callInButton.setEnabled(false);

        resetDataProvider();

        registeredPatientsGrid.addColumn(registration -> registration.getPatient().getName())
                .setCaption("Patient");
        registeredPatientsGrid.addColumn(registration -> registration.getPatient().getId())
                .setCaption("Number");
        registeredPatientsGrid.addColumn(registration -> registration.getStatus()).setCaption("Status");
        registeredPatientsGrid.addSelectionListener(patientSelectionEvent -> {
            selectedRegistration = patientSelectionEvent.getFirstSelectedItem();
            callInButton.setEnabled(true);
        });

        broadcaster.register(this);
    }

    @Override
    public void detach() {
        broadcaster.unregister(this);
        super.detach();
    }

    @Override
    public void accept(String message) {
        getUI().access(() -> resetDataProvider());
    }

    private void resetDataProvider() {
        registrationDataProvider = DataProvider.ofCollection(presenter.getDoctorsRegistrations());
        registeredPatientsGrid.setDataProvider(registrationDataProvider);
    }

}
