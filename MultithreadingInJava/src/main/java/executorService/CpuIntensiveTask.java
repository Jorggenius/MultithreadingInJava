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
public class CpuIntensiveTask {
     public static void main(String[] args) {
        int cores = Runtime.getRuntime().availableProcessors();
        ExecutorService service = Executors.newFixedThreadPool(cores);
        System.out.println("Created thread pool with " + cores + " cores");
        for (int i = 0; i < 20; i++) {
            service.execute(new CpuTask());
        }
    }
}

class CpuTask implements Runnable {

    @Override
    public void run() {
        System.out.println("Some CPU intensive task being done by :" + Thread.currentThread().getName());
    }
}
