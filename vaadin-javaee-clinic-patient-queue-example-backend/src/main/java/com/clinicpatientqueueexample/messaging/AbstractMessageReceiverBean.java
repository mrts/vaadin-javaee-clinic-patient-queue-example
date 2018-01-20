package com.clinicpatientqueueexample.messaging;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.TextMessage;

public abstract class AbstractMessageReceiverBean {

    protected void broadcastReceivedMessage(AbstractBroadcaster broadcaster, Message receivedMessage) {
        try {
            final String messageText = ((TextMessage) receivedMessage).getText();
            final String[] parts = messageText.split(":", 2);
            if (parts.length != 2) {
                throw new RuntimeException("Message '" + messageText + "' must have 2 parts separated by :");
            }
            broadcaster.broadcast(parts[0], parts[1]);
        } catch (JMSException e) {
            throw new RuntimeException(e);
        }
    }

}
