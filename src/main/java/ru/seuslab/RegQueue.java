package ru.seuslab;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import test.seuslab.registration.containers.PurchaseStatus;
import test.seuslab.registration.web.FiveSim;

import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.atomic.AtomicBoolean;

public class RegQueue extends Thread {
    private LinkedBlockingQueue<String> queue;
    private AtomicBoolean queueFinish;

    public RegQueue(LinkedBlockingQueue<String> queue, AtomicBoolean queueFinish) {
        this.queue = queue;
        this.queueFinish = queueFinish;
    }

    @Override
    public void run() {
        for (int i = 1; i < 6; i++) {
            try {
                System.out.println("Кладу в очередь " + i);
                queue.put("Из очереди" + i);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        queueFinish.set(true);
    }
}
