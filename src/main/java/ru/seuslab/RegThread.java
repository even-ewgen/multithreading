package ru.seuslab;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import test.seuslab.registration.containers.PurchaseStatus;
import test.seuslab.registration.utilites.NameGenerator;
import test.seuslab.registration.web.FiveSim;
import test.seuslab.registration.workInTg.Registration;

import java.util.Objects;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicBoolean;

public class RegThread  extends Thread {
    private LinkedBlockingQueue<String> queue;
    private AtomicBoolean queueFinish;

    public RegThread(LinkedBlockingQueue<String> queue, AtomicBoolean queueFinish) {
        this.queue = queue;
        this.queueFinish = queueFinish;
    }

    @Override
    public void run() {
        boolean work = true;
        while (work) {
            if (queueFinish.get() && queue.isEmpty()) {
                work = false;
            } else {
                String fromQueue = null;
                try {
                    fromQueue = queue.take();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println("В потоке - " + fromQueue);
            }
        }
    }
}
