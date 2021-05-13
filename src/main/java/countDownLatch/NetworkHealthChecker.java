package countDownLatch;

import java.util.concurrent.CountDownLatch;

public class NetworkHealthChecker extends BaseHealthChecker{

    public NetworkHealthChecker(CountDownLatch _latch) {
        super(_latch, "Network Service");
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
