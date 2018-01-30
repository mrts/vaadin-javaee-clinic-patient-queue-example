package com.clinicpatientqueueexample.infoscreen;

import com.clinicpatientqueueexample.messaging.CallInBroadcaster;
import com.clinicpatientqueueexample.patients.Registration;
import com.vaadin.cdi.CDIView;
import com.vaadin.data.provider.DataProvider;
import com.vaadin.data.provider.ListDataProvider;
import com.vaadin.external.org.slf4j.Logger;
import com.vaadin.external.org.slf4j.LoggerFactory;
import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener;

import javax.inject.Inject;
import java.util.function.Consumer;

@CDIView(InfoscreenViewImpl.VIEW_NAME)
public class InfoscreenViewImpl extends InfoscreenDesign implements View, Consumer<String> {

    public static final String VIEW_NAME = "infoscreen";

    private static final Logger logger = LoggerFactory.getLogger(InfoscreenViewImpl.class);

    ListDataProvider<Registration> registrationDataProvider;

    @Inject
    private CallInBroadcaster broadcaster;

    @Inject
    private InfoscreenPresenter presenter;

    @Override
    public void enter(ViewChangeListener.ViewChangeEvent viewChangeEvent) {
        calledInPatientsGrid.addColumn(registration -> registration.getPatient().getId())
            .setCaption("Patient number");
        calledInPatientsGrid.addColumn(registration -> registration.getDoctor().getOffice())
            .setCaption("Doctor's office");

        resetDataProvider();

        // would filter messages by infoscreen/doctor association in a real system
        broadcaster.register(presenter.INFOSCREEN_ID, this);
    }

    @Override
    public void detach() {
        broadcaster.unregister(presenter.INFOSCREEN_ID, this);
        super.detach();
    }

    @Override
    public void accept(String message) {
        logger.info("Received message: '" + message + "'");
        getUI().access(() -> resetDataProvider());
    }

    private void resetDataProvider() {
        registrationDataProvider = DataProvider.ofCollection(presenter.getCalledInRegistrations());
        calledInPatientsGrid.setDataProvider(registrationDataProvider);
    }

}
