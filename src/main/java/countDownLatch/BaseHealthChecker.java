package countDownLatch;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.atomic.AtomicBoolean;

public abstract class BaseHealthChecker implements Runnable{
    private final CountDownLatch _latch;
    private final String _serviceName;
    private final AtomicBoolean _serviceUp = new AtomicBoolean(false);

    public BaseHealthChecker(CountDownLatch _latch, String _serviceName) {
        this._latch = _latch;
        this._serviceName = _serviceName;
        this._serviceUp.set(false);
    }

    @Override
    public void run() {
        try{
            verifyService();
            _serviceUp.set(true);
        }catch (Throwable throwable){
            throwable.printStackTrace(System.err);
            _serviceUp.set(false);
        } finally {
            if(_latch != null){
                _latch.countDown();
            }
        }
    }

    public String getServiceName() {
        return _serviceName;
    }

    public boolean isServiceUp() {
        return _serviceUp.get();
    }

    public abstract void verifyService();
}
