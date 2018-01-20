package com.clinicpatientqueueexample.common;

import com.vaadin.annotations.Theme;
import com.vaadin.cdi.CDINavigator;
import com.vaadin.navigator.PushStateNavigation;
import com.vaadin.ui.UI;
import com.vaadin.ui.VerticalLayout;

import javax.inject.Inject;

@Theme("clinicpatientqueueexampletheme")
@PushStateNavigation
public abstract class ClinicPatientQueueExampleUIBase extends UI {

    @Inject
    protected CDINavigator navigator;

    protected void setupNavigator() {
        final VerticalLayout contentArea = new VerticalLayout();
        contentArea.setMargin(false);
        setContent(contentArea);

        navigator.init(this, contentArea);
        navigator.setErrorView(InaccessibleErrorView.class);
    }

}
