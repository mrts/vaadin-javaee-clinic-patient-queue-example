package com.clinicpatientqueueexample.registrationkiosk;

import com.clinicpatientqueueexample.data.CrudService;
import com.clinicpatientqueueexample.patients.Patient;
import com.vaadin.cdi.CDIView;
import com.vaadin.data.provider.CallbackDataProvider;
import com.vaadin.data.provider.DataProvider;
import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener;
import com.vaadin.ui.Button;
import com.vaadin.ui.Grid;
import com.vaadin.ui.TextField;
import com.vaadin.ui.VerticalLayout;

@CDIView(RegistrationKioskView.VIEW_NAME)
public class RegistrationKioskView extends VerticalLayout implements View {

    public static final String VIEW_NAME = "registration-kiosk";

    private CrudService<Patient> service = new CrudService<>();
    private DataProvider<Patient, String> dataProvider = new CallbackDataProvider<>(
            query -> service.findAll().stream(), query -> service.findAll().size());
    private int number;

    @Override
    public void enter(ViewChangeListener.ViewChangeEvent viewChangeEvent) {

        final TextField name = new TextField();
        name.setCaption("Type your name here:");

        final Button button = new Button("Click Me");
        button.addClickListener(e -> {
            service.save(new Patient(String.valueOf(number++), name.getValue()));
            dataProvider.refreshAll();
        });

        final Grid<Patient> grid = new Grid<>(Patient.class);
        grid.setDataProvider(dataProvider);
        grid.setSizeFull();

        addComponents(name, button, grid);
        setSizeFull();
        setExpandRatio(grid, 1.0f);
    }

}
