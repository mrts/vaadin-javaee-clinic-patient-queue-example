package com.clinicpatientqueueexample.doctorsoffice;

import com.clinicpatientqueueexample.common.ViewNotification;
import com.clinicpatientqueueexample.doctors.Doctor;
import com.clinicpatientqueueexample.messaging.MessageSenderBean;
import com.clinicpatientqueueexample.patients.Patient;
import com.clinicpatientqueueexample.patients.Registration;
import com.clinicpatientqueueexample.patients.RegistrationService;
import com.clinicpatientqueueexample.patients.RegistrationStatus;
import org.junit.Rule;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnit;
import org.mockito.junit.MockitoRule;

import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class DoctorsOfficePresenterTest {

    final Doctor DOCTOR = new Doctor("doctest", "Dr. Test", "test-office");
    final Patient PATIENT = new Patient("123", "Ms. Piret Patient");

    @Rule
    public MockitoRule mockitoRule = MockitoJUnit.rule();

    @Mock
    private RegistrationService registrationService;

    @Mock
    private ViewNotification notification;

    @Mock
    private UserSessionContext sessionContext;

    @Mock
    private MessageSenderBean messageSenderBean;

    @InjectMocks
    private DoctorsOfficePresenter presenter;

    @Test
    public void getDoctorsRegistrations_shouldFilterByUserSessionDoctor() {
        // arrange
        when(sessionContext.getDoctor()).thenReturn(DOCTOR);

        // act
        presenter.getDoctorsRegistrations();

        // assert
        verify(registrationService).findByDoctor(DOCTOR);
    }

    @Test
    public void callInPatient_shouldSetStatusToRegisteredAndSendMessageAndShowNotification() {
        // arrange
        when(sessionContext.getDoctor()).thenReturn(DOCTOR);
        final Registration registration = new Registration(UUID.randomUUID(), PATIENT, DOCTOR);

        // act
        presenter.callInPatient(registration);

        // assert
        assertThat(registration.getStatus()).isEqualTo(RegistrationStatus.CALLED_IN);
        verify(messageSenderBean).sendCallInMessage(DOCTOR.getId() + ":Calling in patient #123");
        verify(notification).showMessage("Calling in patient Ms. Piret Patient");
    }

}
