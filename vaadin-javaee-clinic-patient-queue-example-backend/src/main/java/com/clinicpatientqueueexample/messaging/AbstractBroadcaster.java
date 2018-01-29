package com.clinicpatientqueueexample.messaging;

import java.util.Collection;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.function.BiConsumer;

public abstract class AbstractBroadcaster {

    private final Collection<BiConsumer<String, String>> listeners = new ConcurrentLinkedQueue<>();
    private final ExecutorService executorService = Executors.newSingleThreadExecutor();

    public void register(BiConsumer<String, String> listener) {
        listeners.add(listener);
    }

    public void unregister(BiConsumer<String, String> listener) {
        listeners.remove(listener);
    }

    public void broadcast(String receiverID, String messageText) {
        for (final BiConsumer<String, String> listener : listeners) {
            executorService.execute(() -> listener.accept(receiverID, messageText));
        }
    }

}
