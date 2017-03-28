package com.clinicpatientqueueexample.common;

import com.vaadin.ui.Notification;

import javax.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class ViewNotificationImpl implements ViewNotification {

    @Override
    public void showMessage(String message) {
        Notification.show(message);
    }

}
