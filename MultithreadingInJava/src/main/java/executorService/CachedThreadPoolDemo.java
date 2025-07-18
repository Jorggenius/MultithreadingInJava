/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package executorService;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 *
 * @author JORGE
 */
public class CachedThreadPoolDemo {

    public static void main(String[] args) {
        try (ExecutorService service = Executors.newCachedThreadPool()) {
            for (int i = 0; i < 10000; i++) {
                service.execute(new TaskOne(i));
            }
        }
    }
}

class TaskOne implements Runnable {

    private final int taskId;

    public TaskOne(int taskId) {
        this.taskId = taskId;
    }

    @Override
    public void run() {
        System.out.println("Task : " + taskId + " being executed by " + Thread.currentThread().getName());
        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}
