package com.clinicpatientqueueexample.messaging;

import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.function.Consumer;

public abstract class AbstractBroadcaster {

    private final ConcurrentLinkedQueue<Consumer<String>> listeners = new ConcurrentLinkedQueue<>();
    private final ExecutorService executorService = Executors.newSingleThreadExecutor();

    public void register(Consumer<String> listener) {
        listeners.add(listener);
    }

    public void unregister(Consumer<String> listener) {
        listeners.remove(listener);
    }

    public void broadcast(String messageText) {
        for (final Consumer<String> listener : listeners) {
            executorService.execute(() -> listener.accept(messageText));
        }
    }

}
