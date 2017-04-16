package com.clinicpatientqueueexample.doctorsoffice;

import com.clinicpatientqueueexample.common.ClinicPatientQueueExampleUIBase;
import com.vaadin.annotations.Push;
import com.vaadin.cdi.CDIUI;
import com.vaadin.server.Page;
import com.vaadin.server.VaadinRequest;
import com.vaadin.shared.ui.ui.Transport;

@Push(transport = Transport.LONG_POLLING)
@CDIUI("doctors-office")
public class DoctorsOfficeUI extends ClinicPatientQueueExampleUIBase {

    @Override
    protected void init(VaadinRequest vaadinRequest) {
        setupNavigator();

        String defaultView = Page.getCurrent().getUriFragment();
        if (defaultView == null || defaultView.trim().isEmpty()) {
            defaultView = DoctorsOfficeViewImpl.VIEW_NAME;
        }

        if (isUserAuthenticated(vaadinRequest)) {
            navigator.navigateTo(defaultView);
        } else {
            navigator.navigateTo(LoginView.VIEW_NAME);
        }
    }

    private boolean isUserAuthenticated(VaadinRequest vaadinRequest) {
        return vaadinRequest.getUserPrincipal() != null;
    }

}
