package locks;

import java.util.Date;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class PrinterQueue {

    private final Lock lock = new ReentrantLock();

    public void printJob(Object document){
        lock.lock();
        try{
            Long duration = (long)(Math.random()*10000);
            System.out.println(Thread.currentThread().getName()+
                    " process time: "+(duration/10000)+"\nDate:"+new Date());
            Thread.sleep(duration);
        } catch (InterruptedException exception) {
            exception.printStackTrace();
        } finally {
            System.out.println("Finished using: "+Thread.currentThread().getName());
            lock.unlock();
        }
    }
}
