package com.clinicpatientqueueexample.common;

import com.vaadin.annotations.Theme;
import com.vaadin.cdi.CDIViewProvider;
import com.vaadin.navigator.Navigator;
import com.vaadin.server.VaadinRequest;
import com.vaadin.ui.UI;
import com.vaadin.ui.VerticalLayout;

import javax.inject.Inject;

@Theme("clinicpatientqueueexampletheme")
public abstract class ClinicPatientQueueExampleUIBase extends UI {

    @Inject
    private CDIViewProvider cdiViewProvider;
    protected Navigator navigator;

    protected void setupNavigator() {
        final VerticalLayout contentArea = new VerticalLayout();
        contentArea.setMargin(false);
        setContent(contentArea);

        navigator = new Navigator(this, contentArea);
        navigator.addProvider(cdiViewProvider);
        navigator.setErrorView(InaccessibleErrorView.class);
    }

}
