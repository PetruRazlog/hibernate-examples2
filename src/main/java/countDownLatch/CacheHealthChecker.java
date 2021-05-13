package countDownLatch;

import java.util.concurrent.CountDownLatch;

public class CacheHealthChecker extends BaseHealthChecker {

    public CacheHealthChecker(CountDownLatch _latch) {
        super(_latch,"Cache service");
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
