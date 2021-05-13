package locks;

public class PrintingJob implements Runnable{

    private final PrinterQueue printerQueue;

    public PrintingJob(PrinterQueue printerQueue) {
        this.printerQueue = printerQueue;
    }

    @Override
    public void run() {
        System.out.println("Acquire "+Thread.currentThread().getName());
        printerQueue.printJob(new Object());
    }
}
