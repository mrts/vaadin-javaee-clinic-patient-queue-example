package com.clinicpatientqueueexample;

import com.clinicpatientqueueexample.data.CrudService;
import com.clinicpatientqueueexample.patients.Patient;
import com.vaadin.annotations.Theme;
import com.vaadin.cdi.CDIUI;
import com.vaadin.cdi.CDIViewProvider;
import com.vaadin.data.provider.CallbackDataProvider;
import com.vaadin.data.provider.DataProvider;
import com.vaadin.navigator.Navigator;
import com.vaadin.server.VaadinRequest;
import com.vaadin.ui.Button;
import com.vaadin.ui.Grid;
import com.vaadin.ui.TextField;
import com.vaadin.ui.UI;
import com.vaadin.ui.VerticalLayout;

import javax.inject.Inject;

@CDIUI("registration-kiosk")
@Theme("clinicpatientqueueexampletheme")
public class ClinicPatientQueueExampleUI extends UI {

    @Inject
    private CDIViewProvider cdiViewProvider;

    @Override
    protected void init(VaadinRequest vaadinRequest) {
        final VerticalLayout contentArea = new VerticalLayout();
        contentArea.setMargin(false);
        setContent(contentArea);

        final Navigator navigator = new Navigator(this, contentArea);
        navigator.addProvider(cdiViewProvider);
        navigator.navigateTo(DefaultView.NAME);
    }

}
