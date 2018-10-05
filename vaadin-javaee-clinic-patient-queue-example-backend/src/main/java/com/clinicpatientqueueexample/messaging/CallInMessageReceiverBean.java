package com.clinicpatientqueueexample.messaging;

import javax.ejb.ActivationConfigProperty;
import javax.ejb.MessageDriven;
import javax.inject.Inject;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.Topic;

import static com.clinicpatientqueueexample.messaging.MessageSenderBean.CALL_IN_JMS_DESTINATION;

@MessageDriven(activationConfig = {
        @ActivationConfigProperty(propertyName = "destinationLookup", propertyValue = CALL_IN_JMS_DESTINATION),
        @ActivationConfigProperty(propertyName = "destinationType", propertyValue = "javax.jms.Topic"),
        @ActivationConfigProperty(propertyName = "acknowledgeMode", propertyValue = "Auto-acknowledge") })
public class CallInMessageReceiverBean extends AbstractMessageReceiverBean implements MessageListener {

    @Inject
    private CallInBroadcaster callInBroadcaster;

    @Override
    public void onMessage(Message receivedMessage) {
        broadcastReceivedMessage(callInBroadcaster, receivedMessage);
    }

}
