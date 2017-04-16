package com.clinicpatientqueueexample.messaging;

import com.clinicpatientqueueexample.patients.Registration;

import javax.ejb.ActivationConfigProperty;
import javax.ejb.MessageDriven;
import javax.inject.Inject;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.TextMessage;

import static com.clinicpatientqueueexample.messaging.MessageSenderBean.REGISTRATION_JMS_DESTINATION;

@MessageDriven(activationConfig = {
        @ActivationConfigProperty(propertyName = "destinationLookup", propertyValue = REGISTRATION_JMS_DESTINATION),
        @ActivationConfigProperty(propertyName = "destinationType", propertyValue = "javax.jms.Queue"),
        @ActivationConfigProperty(propertyName = "acknowledgeMode", propertyValue = "Auto-acknowledge") })
public class RegistrationMessageReceiverBean implements MessageListener {

    @Inject
    private RegistrationBroadcaster registrationBroadcaster;

    @Override
    public void onMessage(Message receivedMessage) {
        try {
            final String messageText = ((TextMessage) receivedMessage).getText();
            registrationBroadcaster.broadcast(messageText);
        } catch (JMSException e) {
            throw new RuntimeException(e);
        }
    }

}
