package com.clinicpatientqueueexample.infoscreen;

import com.clinicpatientqueueexample.common.ClinicPatientQueueExampleUIBase;
import com.vaadin.cdi.CDIUI;
import com.vaadin.server.VaadinRequest;

@CDIUI("infoscreen")
public class InfoscreenUI extends ClinicPatientQueueExampleUIBase {

    @Override
    protected void init(VaadinRequest vaadinRequest) {
        setupNavigator();
        navigator.navigateTo(InfoscreenView.VIEW_NAME);
    }

}
