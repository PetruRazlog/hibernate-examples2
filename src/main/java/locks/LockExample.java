package locks;

public class LockExample {
    public static void main(String[] args) {
        PrinterQueue printerQueue = new PrinterQueue();
        Thread[] thread = new Thread[5];
        String[] printers ={"lobby","main","xerox","wifi","public"};
        for (int i = 0; i < printers.length; i++){
            thread[i] = new Thread(new PrintingJob(printerQueue),"printer: "+printers[i]);
        }
        for (int i = 0; i < printers.length; i++){
            thread[i].start();
        }
    }
}
