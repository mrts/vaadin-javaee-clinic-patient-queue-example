package com.clinicpatientqueueexample.messaging;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.TextMessage;

public abstract class AbstractMessageReceiverBean {

    protected void broadcastReceivedMessage(AbstractBroadcaster broadcaster, Message receivedMessage) {
        try {
            final String messageText = ((TextMessage) receivedMessage).getText();
            broadcaster.broadcast(messageText);
        } catch (JMSException e) {
            throw new RuntimeException(e);
        }
    }

}
