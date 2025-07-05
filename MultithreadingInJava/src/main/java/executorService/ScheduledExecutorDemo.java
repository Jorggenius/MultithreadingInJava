/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package executorService;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

/**
 *
 * @author JORGE
 */
public class ScheduledExecutorDemo {

    public static void main(String[] args) {
        ScheduledExecutorService executorService = Executors.newScheduledThreadPool(1);
        executorService.scheduleAtFixedRate(new ProbeTask(), 1000, 2000, TimeUnit.MILLISECONDS);

        try {
            if (!executorService.awaitTermination(5000, TimeUnit.MILLISECONDS)) {
                executorService.shutdownNow();
            }
        } catch (InterruptedException e) {
            executorService.shutdownNow();
        }
    }
}

class ProbeTask implements Runnable {

    @Override
    public void run() {
        System.out.println("Probing end point for updates...");
    }
}
