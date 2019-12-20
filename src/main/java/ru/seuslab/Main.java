package ru.seuslab;


import test.seuslab.registration.containers.PurchaseStatus;
import test.seuslab.registration.utilites.NameGenerator;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.atomic.AtomicBoolean;

public class Main {
    private static AtomicBoolean queueFinish = new AtomicBoolean();

    public static void main(String[] arg) throws InterruptedException {
        queueFinish.set(false);
        LinkedBlockingQueue<String> queue = new LinkedBlockingQueue<>(1);
        RegQueue regQueue = new RegQueue(queue, queueFinish); //запускаем поток очереди
        regQueue.start();

        List<Thread> threadList = new ArrayList<Thread>();
        RegThread thread = new RegThread(queue, queueFinish);
        threadList.add(thread);
        thread.start();

    }
}
