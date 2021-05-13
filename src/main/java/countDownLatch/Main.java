package countDownLatch;

public class Main {
    public static void main(String[] args) {
        boolean result = false;
        try{
            result = ApplicationStartupUtil.checkExternalServices();
        } catch (InterruptedException exception) {
            exception.printStackTrace();
        }
        System.out.println("Result was: "+result);

    }
}
