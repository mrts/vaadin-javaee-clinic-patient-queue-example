package com.clinicpatientqueueexample.registrationkiosk;

import com.clinicpatientqueueexample.common.ViewNotification;
import com.clinicpatientqueueexample.doctors.Doctor;
import com.clinicpatientqueueexample.messaging.MessageSenderBean;
import com.clinicpatientqueueexample.patients.Patient;
import com.clinicpatientqueueexample.patients.PatientService;
import com.clinicpatientqueueexample.patients.RegistrationService;
import org.junit.Rule;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnit;
import org.mockito.junit.MockitoRule;

import static org.mockito.AdditionalAnswers.returnsFirstArg;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class RegistrationKioskPresenterTest {

    @Rule
    public MockitoRule mockitoRule = MockitoJUnit.rule();

    @Mock
    private RegistrationKioskView view;

    @Mock
    private PatientService patientService;

    @Mock
    private RegistrationService registrationService;

    @Mock
    private ViewNotification notification;

    @Mock
    private MessageSenderBean messageSenderBean;

    @InjectMocks
    private RegistrationKioskPresenter presenter;

    @Test
    public void registerToDoctor_shouldRegisterPatientToGivenDoctorAndShowNotification() {
        // arrange
        final Patient patient = new Patient("123", "Mr. Pablo Patient");
        final Doctor doctor = new Doctor("234", "Dr. Dora Doctor", "office-1");
        when(view.getPatient()).thenReturn(patient);
        when(patientService.save(any(Patient.class))).then(returnsFirstArg());

        // act
        presenter.registerToDoctor(doctor);

        // assert
        verify(registrationService).registerPatientToDoctor(patient, doctor);
        verify(notification).showMessage("Mr. Pablo Patient, you are successfully registered to "
                + "Dr. Dora Doctor appointment.\nYour call-in number is 123");
    }

}
