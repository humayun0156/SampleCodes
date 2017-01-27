package rxjava_sample.simple;

import rx.Observable;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

public class IntervalExample {
    public static void main(String[] args) throws InterruptedException {
        awaitForSometime();
    }

    private static void awaitForSometime() throws InterruptedException {
        CountDownLatch latch = new CountDownLatch(5);
        Observable<Long> eachSecond = Observable.interval(1, TimeUnit.SECONDS);
        eachSecond.subscribe(v -> {
            latch.countDown();
            System.out.println("Got value " + v);
        });
        latch.await();
    }

    private static void nothingPrinted() {
        Observable<Long> eachSecond = Observable.interval(1, TimeUnit.SECONDS);
        eachSecond.subscribe(v -> {
            System.out.println("Got value " + v);
        });
    }
}
