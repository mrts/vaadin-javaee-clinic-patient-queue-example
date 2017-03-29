package com.clinicpatientqueueexample.common;

import com.vaadin.ui.UI;

import javax.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class ViewNavigatorImpl implements ViewNavigator {

    @Override
    public void navigateTo(String viewName) {
        UI.getCurrent().getNavigator().navigateTo(viewName);
    }

}
