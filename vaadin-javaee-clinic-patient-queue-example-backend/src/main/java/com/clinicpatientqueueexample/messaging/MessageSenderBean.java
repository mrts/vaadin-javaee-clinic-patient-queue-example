package com.clinicpatientqueueexample.messaging;

import javax.annotation.Resource;
import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.jms.JMSContext;
import javax.jms.JMSDestinationDefinition;
import javax.jms.JMSDestinationDefinitions;
import javax.jms.Queue;

/**
 * Definition of the JMS queues to have them automatically created
 */
// @formatter:off
 @JMSDestinationDefinitions(value = {
     @JMSDestinationDefinition(name = "java:/" + MessageSenderBean.REGISTRATION_JMS_DESTINATION,
         interfaceName = "javax.jms.Queue",
         destinationName = MessageSenderBean.REGISTRATION_QUEUE_NAME),
     @JMSDestinationDefinition(name = "java:/" + MessageSenderBean.CALL_IN_JMS_DESTINATION,
         interfaceName = "javax.jms.Queue",
         destinationName = MessageSenderBean.CALL_IN_QUEUE_NAME)
 })
 // @formatter:on

@Stateless
public class MessageSenderBean {

    public static final String REGISTRATION_QUEUE_NAME = "RegistrationMessageQueue";
    public static final String REGISTRATION_JMS_DESTINATION = "queue/" + REGISTRATION_QUEUE_NAME;

    public static final String CALL_IN_QUEUE_NAME = "CallInMessageQueue";
    public static final String CALL_IN_JMS_DESTINATION = "queue/" + CALL_IN_QUEUE_NAME;

    @Inject
    private JMSContext jmsContext;

    @Resource(lookup = "java:/" + REGISTRATION_JMS_DESTINATION)
    private Queue registrationQueue;

    @Resource(lookup = "java:/" + CALL_IN_JMS_DESTINATION)
    private Queue callInQueue;

    public void sendRegistrationMessage(String message) {
        sendMessage(registrationQueue, message);
    }

    public void sendCallInMessage(String message) {
        sendMessage(callInQueue, message);
    }

    private void sendMessage(Queue queue, String message) {
        jmsContext.createProducer().send(queue, message);
    }

}
