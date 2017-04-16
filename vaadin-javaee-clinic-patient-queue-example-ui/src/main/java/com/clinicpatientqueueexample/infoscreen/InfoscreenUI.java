package com.clinicpatientqueueexample.infoscreen;

import com.clinicpatientqueueexample.common.ClinicPatientQueueExampleUIBase;
import com.vaadin.annotations.Push;
import com.vaadin.cdi.CDIUI;
import com.vaadin.server.VaadinRequest;
import com.vaadin.shared.ui.ui.Transport;

@Push(transport = Transport.LONG_POLLING)
@CDIUI("infoscreen")
public class InfoscreenUI extends ClinicPatientQueueExampleUIBase {

    @Override
    protected void init(VaadinRequest vaadinRequest) {
        setupNavigator();
        navigator.navigateTo(InfoscreenViewImpl.VIEW_NAME);
    }

}
