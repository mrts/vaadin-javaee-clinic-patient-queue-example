package com.clinicpatientqueueexample.infoscreen;

import com.clinicpatientqueueexample.patients.Registration;
import com.vaadin.cdi.CDIView;
import com.vaadin.data.provider.DataProvider;
import com.vaadin.data.provider.ListDataProvider;
import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener;
import com.vaadin.ui.VerticalLayout;

import javax.inject.Inject;

@CDIView(InfoscreenViewImpl.VIEW_NAME)
public class InfoscreenViewImpl extends InfoscreenDesign implements View {

    public static final String VIEW_NAME = "infoscreen";

    ListDataProvider<Registration> registrationDataProvider;

    @Inject
    private InfoscreenPresenter presenter;

    @Override
    public void enter(ViewChangeListener.ViewChangeEvent viewChangeEvent) {
        registrationDataProvider = DataProvider.ofCollection(presenter.getCalledInRegistrations());

        calledInPatientsGrid.setDataProvider(registrationDataProvider);
        calledInPatientsGrid.addColumn(registration -> registration.getPatient().getId())
                .setCaption("Patient number");
        calledInPatientsGrid.addColumn(registration -> registration.getDoctor().getOffice())
                .setCaption("Doctor's office");
    }

}
