package com.clinicpatientqueueexample.messaging;

import javax.annotation.Resource;
import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.jms.*;

/**
 * Definition of the JMS topics to have them automatically created
 */
// @formatter:off
 @JMSDestinationDefinitions(value = {
     @JMSDestinationDefinition(name = "java:/" + MessageSenderBean.REGISTRATION_JMS_DESTINATION,
         interfaceName = "javax.jms.Topic",
         destinationName = MessageSenderBean.REGISTRATION_TOPIC_NAME),
     @JMSDestinationDefinition(name = "java:/" + MessageSenderBean.CALL_IN_JMS_DESTINATION,
         interfaceName = "javax.jms.Topic",
         destinationName = MessageSenderBean.CALL_IN_TOPIC_NAME)
 })
 // @formatter:on

@Stateless
public class MessageSenderBean {

    public static final String REGISTRATION_TOPIC_NAME = "RegistrationMessageTopic";
    public static final String REGISTRATION_JMS_DESTINATION = "topic/" + REGISTRATION_TOPIC_NAME;

    public static final String CALL_IN_TOPIC_NAME = "CallInMessageTopic";
    public static final String CALL_IN_JMS_DESTINATION = "topic/" + CALL_IN_TOPIC_NAME;

    @Inject
    private JMSContext jmsContext;

    @Resource(lookup = "java:/" + REGISTRATION_JMS_DESTINATION)
    private Topic registrationTopic;

    @Resource(lookup = "java:/" + CALL_IN_JMS_DESTINATION)
    private Topic callInTopic;

    public void sendRegistrationMessage(String message) {
        sendMessage(registrationTopic, message);
    }

    public void sendCallInMessage(String message) {
        sendMessage(callInTopic, message);
    }

    private void sendMessage(Topic topic, String message) {
        jmsContext.createProducer().send(topic, message);
    }

}
