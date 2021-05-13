package stopwatch;

import java.util.Scanner;
import java.util.Timer;
import java.util.TimerTask;

public class StopwatchSymbol {
    static int interval;
    static Timer timer;
    static String symbol = "=";

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.print("Input seconds => : ");
        String secs = sc.nextLine();
        int delay = 1000;
        int period = 1000;
        timer = new Timer();
        interval = Integer.parseInt(secs);
        System.out.print(symbol);
        timer.scheduleAtFixedRate(new TimerTask() {

            public void run() {
                setInterval();

            }
        }, delay, period);
    }

    private static final void setInterval() {
        if (interval == 1) {
            timer.cancel();
        } else {
            System.out.print(symbol);
            --interval;
        }
    }
}
