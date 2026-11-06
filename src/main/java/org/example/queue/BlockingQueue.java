package org.example.queue;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.LinkedList;
import java.util.Queue;

public class BlockingQueue<T> {

    public final Queue<T> queue = new LinkedList<>();
    private final int capacity;

    private static final Logger log = LoggerFactory.getLogger(BlockingQueue.class);

    public BlockingQueue(int capacity) {
        this.capacity = capacity;
    }

    public synchronized void enqueue(T item) {
        try {
            while (queue.size() == capacity) {
                log.info("Очередь заполнена (capacity={}). Поток [{}] ожидает свободного места...",
                        capacity, Thread.currentThread().getName());
                wait();
            }
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            return;
        }
        queue.add(item);
        log.debug("Элемент успешно добавлен. Размер очереди: {}/{}", queue.size(), capacity);
        notifyAll();
    }

    public synchronized T dequeue() {
        try {
            while (queue.isEmpty()) {
                log.info("Очередь пуста. Поток [{}] ожидает появления элементов...",
                        Thread.currentThread().getName());
                wait();
            }
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            return null;
        }
        return removeElement();
    }

    private T removeElement() {
        T item = queue.poll();
        log.info("Элемент извлечен. Размер очереди: {}/{}", queue.size(), capacity);
        notifyAll();
        return item;
    }

    public synchronized int size() {
        return queue.size();
    }
}
