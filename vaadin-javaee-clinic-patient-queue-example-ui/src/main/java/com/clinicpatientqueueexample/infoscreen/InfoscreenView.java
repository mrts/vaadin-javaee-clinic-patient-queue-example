package com.clinicpatientqueueexample.infoscreen;

import com.vaadin.cdi.CDIView;
import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener;
import com.vaadin.ui.VerticalLayout;

@CDIView(InfoscreenView.VIEW_NAME)
public class InfoscreenView extends VerticalLayout implements View {

    public static final String VIEW_NAME = "infoscreen";

    @Override
    public void enter(ViewChangeListener.ViewChangeEvent viewChangeEvent) {

    }

}
