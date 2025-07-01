/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package threadSynchronisation;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author JORGE
 */
public class ProducerConsumer {

    public static void main(String[] args) {
        Worker worker = new Worker(5, 0);

        Thread producer = new Thread(() -> {
            try {
                worker.produce();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        });

        Thread consumer = new Thread(() -> {
            try {
                worker.consume();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        });

        producer.start();
        new java.util.Scanner(System.in).nextLine();
        consumer.start();
    }
}

class Worker {

    private int sequence = 0;
    private final Integer top;
    private final Integer bottom;
    private final List<Integer> container;
    private final Object lock = new Object();

    public Worker(Integer top, Integer bottom) {
        this.top = top;
        this.bottom = bottom;
        this.container = new ArrayList<>();
    }

    public void produce() throws InterruptedException {
        synchronized (lock) {
            while (true) {
                if (container.size() == top) {
                    System.out.println("Container full, waiting for items to be removed...");
                    new java.util.Scanner(System.in).nextLine();
                    lock.wait();
                } else {
                    System.out.println(sequence + " Added to the container");
                    container.add(sequence++);
//                    new java.util.Scanner(System.in).nextLine();
                    lock.notify();
                }
                Thread.sleep(500);
            }
        }
    }

    public void consume() throws InterruptedException {
        synchronized (lock) {
            while (true) {
                if (container.size() == bottom) {
                    System.out.println("Container empty, waiting for items to be added...");
                    lock.wait();
                } else {
                    System.out.println(container.remove(0) + " Removed from the container");
                    lock.notify();
                }
                Thread.sleep(500);
            }
        }
    }
}
