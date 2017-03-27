package com.clinicpatientqueueexample.doctorsoffice;

import com.clinicpatientqueueexample.common.Constants;
import com.vaadin.cdi.CDIView;
import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener;
import com.vaadin.ui.VerticalLayout;

import javax.annotation.security.RolesAllowed;

@CDIView(DoctorsOfficeView.VIEW_NAME)
@RolesAllowed(Constants.USERS_ROLE)
public class DoctorsOfficeView extends VerticalLayout implements View {

    public static final String VIEW_NAME = "doctors-office";

    @Override
    public void enter(ViewChangeListener.ViewChangeEvent viewChangeEvent) {
    }

}
