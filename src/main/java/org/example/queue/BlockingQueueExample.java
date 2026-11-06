package org.example.queue;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class BlockingQueueExample {
    private static final Logger log = LoggerFactory.getLogger(BlockingQueueExample.class);

    public static void main(String[] args) {

        BlockingQueue<String> bakery = new BlockingQueue<>(4);

        ExecutorService executorService = Executors.newFixedThreadPool(10);

        for (int i = 1; i < 5; i++) {
            final int producerId = i;
            executorService.submit(() -> {
                try {
                    for (int j = 1; j < 10; j++) {
                        String item = "Заказ № " + j + "от пекаря №" + producerId;
                        bakery.enqueue(item);
                        log.info("Заказ №  {} от пекаря № {}, добавлен в очередь", j, producerId);
                        Thread.sleep(150);
                    }
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                }
            });
        }

        for (int i = 1; i < 5; i++) {
            executorService.submit(() -> {
                try {
                    while (!Thread.currentThread().isInterrupted()) {
                        log.info("Курьер ждет заказ");
                        bakery.dequeue();
                        Thread.sleep(200);
                        log.info("Курьер забрал заказ");
                    }
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                }
            });
        }
        executorService.shutdown();

        try {
            if (!executorService.awaitTermination(5, TimeUnit.SECONDS)) {
                executorService.shutdownNow();
            }
        } catch (InterruptedException e) {
            executorService.shutdownNow();
            Thread.currentThread().interrupt();
        }
    }
}
