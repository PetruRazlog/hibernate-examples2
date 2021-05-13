package collections;

import java.util.concurrent.ConcurrentLinkedDeque;

public class ConcurrentExample {
    public static void main(String[] args) {
        ConcurrentLinkedDeque<String> list = new ConcurrentLinkedDeque<>();
        Thread[] threads = new Thread[100];

        for (int i=0; i< threads.length; i++){
            AddTask task = new AddTask(list);
            threads[i] = new Thread(task);
            threads[i].start();
        }
        System.out.println("Launched threads: "+threads.length);

        for (int i=0; i< threads.length; i++){
            try {
                threads[i].join();
            } catch (InterruptedException exception) {
                exception.printStackTrace();
            }
        }
        System.out.println("List size is: "+list.size());

        for (int i=0; i< threads.length; i++){
            RemoveTask task = new RemoveTask(list);
            threads[i] = new Thread(task);
            threads[i].start();
        }
        System.out.println("Stopping threads: "+threads.length);

        for (int i=0; i< threads.length; i++){
            try {
                threads[i].join();
            } catch (InterruptedException exception) {
                exception.printStackTrace();
            }
        }
        System.out.println("List size is: "+list.size());
    }
}
