package com.clinicpatientqueueexample.infoscreen;

import com.clinicpatientqueueexample.doctors.Doctor;
import com.clinicpatientqueueexample.patients.Patient;
import com.clinicpatientqueueexample.patients.RegistrationService;
import com.clinicpatientqueueexample.patients.RegistrationServiceImpl;
import com.clinicpatientqueueexample.patients.RegistrationStatus;
import org.junit.Rule;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Spy;
import org.mockito.junit.MockitoJUnit;
import org.mockito.junit.MockitoRule;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import static org.assertj.core.api.Assertions.assertThat;

public class InfoscreenPresenterTest {

    final Doctor DOCTOR = new Doctor("doctest", "Dr. Test", "test-office");
    final Patient PATIENT1 = new Patient("123", "Ms. Piret Patient");
    final Patient PATIENT2 = new Patient("124", "Mr. Peeter Patient");

    @Rule
    public MockitoRule mockitoRule = MockitoJUnit.rule();

    @Spy
    private RegistrationService registrationService = new RegistrationServiceImpl();

    @InjectMocks
    private InfoscreenPresenter presenter;

    @Test
    public void getCalledInRegistrations_shouldReturnOnlyCalledInRegistrations() {
        // arrange
        registrationService.registerPatientToDoctor(PATIENT1, DOCTOR);
        registrationService.registerPatientToDoctor(PATIENT2, DOCTOR);
        registrationService.findByPatient(PATIENT2)
                .ifPresent(registration -> registration.setStatus(RegistrationStatus.CALLED_IN));

        // act
        final List<String> registeredPatientNames = presenter.getCalledInRegistrations()
            .stream().map(registration -> registration.getPatient().getName()).collect(Collectors.toList());

        // assert
        assertThat(registeredPatientNames).isEqualTo(Arrays.asList("Mr. Peeter Patient"));
    }

}
