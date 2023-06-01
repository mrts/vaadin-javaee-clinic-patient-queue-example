package com.clinicpatientqueueexample.messaging;

import javax.annotation.Resource;
import javax.enterprise.concurrent.ManagedExecutorService;
import javax.enterprise.context.ApplicationScoped;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.ConcurrentMap;
import java.util.function.Consumer;

// This is an abstract base class, but as managed beans cannot be abstract, there is no abstract qualifier.
@ApplicationScoped
public class AbstractBroadcaster {

    private final ConcurrentMap<String, ConcurrentLinkedQueue<Consumer<String>>> listeners = new ConcurrentHashMap<>();
    @Resource
    private ManagedExecutorService executorService;

    public void register(String topic, Consumer<String> listener) {
        ConcurrentLinkedQueue<Consumer<String>> topicListeners = listeners.putIfAbsent(topic, new ConcurrentLinkedQueue<>());
        if (topicListeners == null) {
            topicListeners = listeners.get(topic);
        }
        topicListeners.add(listener);
    }

    public void unregister(String topic, Consumer<String> listener) {
        final ConcurrentLinkedQueue<Consumer<String>> topicListeners = listeners.get(topic);
        if (topicListeners == null) {
            return;
        }
        topicListeners.remove(listener);
        if (topicListeners.isEmpty()) {
            listeners.remove(topic);
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
