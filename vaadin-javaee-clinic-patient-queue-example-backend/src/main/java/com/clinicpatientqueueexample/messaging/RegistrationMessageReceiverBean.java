package com.clinicpatientqueueexample.messaging;

import javax.ejb.ActivationConfigProperty;
import javax.ejb.MessageDriven;
import javax.inject.Inject;
import javax.jms.Message;
import javax.jms.MessageListener;

import static com.clinicpatientqueueexample.messaging.MessageSenderBean.REGISTRATION_JMS_DESTINATION;

@MessageDriven(activationConfig = {
        @ActivationConfigProperty(propertyName = "destinationLookup", propertyValue = REGISTRATION_JMS_DESTINATION),
        @ActivationConfigProperty(propertyName = "destinationType", propertyValue = "javax.jms.Topic"),
        @ActivationConfigProperty(propertyName = "acknowledgeMode", propertyValue = "Auto-acknowledge") })
public class RegistrationMessageReceiverBean extends AbstractMessageReceiverBean implements MessageListener {

    @Inject
    private RegistrationBroadcaster registrationBroadcaster;

    @Override
    public void onMessage(Message receivedMessage) {
        broadcastReceivedMessage(registrationBroadcaster, receivedMessage);
    }

}
