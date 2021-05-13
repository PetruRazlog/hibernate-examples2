package countDownLatch;

import java.util.concurrent.CountDownLatch;

public class DatabaseHealthChecker extends BaseHealthChecker {

    public DatabaseHealthChecker(CountDownLatch _latch) {
        super(_latch,"Database service");
    }

    @Override
    public void verifyService() {
        System.out.println("Checking "+this.getServiceName());
        try{
            Thread.sleep(7000);
        } catch (InterruptedException exception) {
            exception.printStackTrace();
        }
        System.out.println(this.getServiceName()+" is Up");
    }
}
