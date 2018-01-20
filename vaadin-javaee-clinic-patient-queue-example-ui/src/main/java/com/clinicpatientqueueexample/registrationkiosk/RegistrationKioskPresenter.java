package com.clinicpatientqueueexample.registrationkiosk;

import com.clinicpatientqueueexample.common.ViewNotification;
import com.clinicpatientqueueexample.doctors.Doctor;
import com.clinicpatientqueueexample.doctors.DoctorService;
import com.clinicpatientqueueexample.messaging.MessageSenderBean;
import com.clinicpatientqueueexample.patients.Patient;
import com.clinicpatientqueueexample.patients.PatientService;
import com.clinicpatientqueueexample.patients.RegistrationService;

import javax.ejb.EJB;
import javax.enterprise.context.Dependent;
import javax.inject.Inject;
import java.util.List;

@Dependent
public class RegistrationKioskPresenter {

    private RegistrationKioskView view;

    @EJB
    private MessageSenderBean messageSenderBean;

    @Inject
    private PatientService patientService;

    @Inject
    private DoctorService doctorService;

    @Inject
    private RegistrationService registrationService;

    @Inject
    private ViewNotification notification;

    public void setView(RegistrationKioskView view) {
        this.view = view;
    }

    public List<Doctor> getAllDoctors() {
        return doctorService.findAll();
    }

    public void registerToDoctor(Doctor doctor) {
        final Patient patient = patientService.save(view.getPatient());
        registrationService.registerPatientToDoctor(patient, doctor);
        messageSenderBean.sendRegistrationMessage(doctor.getId() + ":Patient " + patient.getName() + " registered");
        notification.showMessage(patient.getName() + ", you are successfully registered to "
                + doctor.getName() + " appointment.\nYour call-in number is " + patient.getId());
        view.resetPatient();
    }

}
