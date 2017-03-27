package com.clinicpatientqueueexample;

import com.clinicpatientqueueexample.data.CrudService;
import com.clinicpatientqueueexample.patients.Patient;
import com.vaadin.annotations.Theme;
import com.vaadin.cdi.CDIUI;
import com.vaadin.data.provider.CallbackDataProvider;
import com.vaadin.data.provider.DataProvider;
import com.vaadin.server.VaadinRequest;
import com.vaadin.ui.Button;
import com.vaadin.ui.Grid;
import com.vaadin.ui.TextField;
import com.vaadin.ui.UI;
import com.vaadin.ui.VerticalLayout;

@CDIUI("registration-kiosk")
@Theme("clinicpatientqueueexampletheme")
public class ClinicPatientQueueExampleUI extends UI {

    private CrudService<Patient> service = new CrudService<>();
    private DataProvider<Patient, String> dataProvider = new CallbackDataProvider<>(
            query -> service.findAll().stream(), query -> service.findAll().size());
    private int number;

    @Override
    protected void init(VaadinRequest vaadinRequest) {
        final VerticalLayout layout = new VerticalLayout();
        final TextField name = new TextField();
        name.setCaption("Type your name here:");

        final Button button = new Button("Click Me");
        button.addClickListener(e -> {
            service.save(new Patient(String.valueOf(number++), name.getValue()));
            dataProvider.refreshAll();
        });

        final Grid<Patient> grid = new Grid<>();
        grid.addColumn(Patient::getName).setCaption("Name");
        grid.setDataProvider(dataProvider);
        grid.setSizeFull();

        layout.addComponents(name, button, grid);
        layout.setSizeFull();
        layout.setExpandRatio(grid, 1.0f);

        setContent(layout);
    }

}
