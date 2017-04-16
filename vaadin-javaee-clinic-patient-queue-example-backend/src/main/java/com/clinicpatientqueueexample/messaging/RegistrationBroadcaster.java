package com.clinicpatientqueueexample.messaging;

import javax.enterprise.context.ApplicationScoped;
import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.function.Consumer;

@ApplicationScoped
public class RegistrationBroadcaster {

    private final List<Consumer<String>> listeners = new LinkedList<>();
    private final ExecutorService executorService = Executors.newSingleThreadExecutor();

    public synchronized void register(Consumer<String> listener) {
        listeners.add(listener);
    }

    public synchronized void unregister(Consumer<String> listener) {
        listeners.remove(listener);
    }

    public synchronized void broadcast(String messageText) {
        for (final Consumer<String> listener : listeners) {
            listener.accept(messageText);
        }
    }

}
