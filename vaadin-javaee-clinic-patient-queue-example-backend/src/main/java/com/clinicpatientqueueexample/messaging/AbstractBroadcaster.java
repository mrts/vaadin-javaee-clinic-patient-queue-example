package com.clinicpatientqueueexample.messaging;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.ConcurrentMap;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.function.Consumer;

public abstract class AbstractBroadcaster {

    private final ConcurrentMap<String, ConcurrentLinkedQueue<Consumer<String>>> listeners = new ConcurrentHashMap<>();
    private final ExecutorService executorService = Executors.newSingleThreadExecutor();

    public void register(String topic, Consumer<String> listener) {
        ConcurrentLinkedQueue<Consumer<String>> topicListeners = listeners.putIfAbsent(topic, new ConcurrentLinkedQueue<>());
        if (topicListeners == null) {
            topicListeners = listeners.get(topic);
        }
        topicListeners.add(listener);
    }

    public void unregister(String topic, Consumer<String> listener) {
        final ConcurrentLinkedQueue<Consumer<String>> topicListeners = listeners.get(topic);
        if (topicListeners != null) {
            topicListeners.remove(listener);
        }
    }

    public void broadcast(String topic, String message) {
        final ConcurrentLinkedQueue<Consumer<String>> topicListeners = listeners.get(topic);
        if (topicListeners != null) {
            for (final Consumer<String> listener : topicListeners) {
                executorService.execute(() -> listener.accept(message));
            }
        }
    }

}
