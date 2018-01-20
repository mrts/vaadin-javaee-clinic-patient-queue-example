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
import java.util.function.BiConsumer;
import java.util.function.Consumer;

@CDIView(InfoscreenViewImpl.VIEW_NAME)
public class InfoscreenViewImpl extends InfoscreenDesign implements View, BiConsumer<String, String> {

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

        broadcaster.register(this);
    }

    @Override
    public void detach() {
        broadcaster.unregister(this);
        super.detach();
    }

    @Override
    public void accept(String doctorID, String message) {
        // would filter messages by infoscreen/doctor association in a real system
        logger.info("Received message: '" + message + "' from doctor #" + doctorID);
        getUI().access(() -> resetDataProvider());
    }

    private void resetDataProvider() {
        registrationDataProvider = DataProvider.ofCollection(presenter.getCalledInRegistrations());
        calledInPatientsGrid.setDataProvider(registrationDataProvider);
    }

}
