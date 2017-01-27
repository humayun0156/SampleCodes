package rxjava_sample.simple;

import rx.Observable;
import rx.Observer;

public class ObserverExample {
    public static void main(String[] args) {
        subscribeOnFew();
    }

    private static void subscribeOnFew() {
        Observable<Integer> numbers = Observable.just(1, 2, 3, 4, 5, 6);
        numbers.take(3).subscribe(
            System.out::println,
            System.out::println,
            () -> {
                System.out.println("Observer completd");
            });
    }

    private static void basicError() {
        Observable<Integer> errorThrowingObservable = Observable.just(1, 2, 3).doOnNext(n -> {
            if (n == 2) throw new RuntimeException("Bad value 2");
        });

        errorThrowingObservable.subscribe(
            System.out::println,
            t -> {
                System.out.println("Whoops! " + t.getMessage());
            });
    }

    private static void basicObserver() {
        Observable<String> stringObservable = Observable.just("abc", "def", "jkl", "mnop");

        stringObservable.subscribe(new Observer<String>() {
            @Override
            public void onCompleted() {
                System.out.println("Observer completed");
            }

            @Override
            public void onError(Throwable e) {
                System.out.println("Got error");
            }

            @Override
            public void onNext(String s) {
                System.out.println("Got value " + s);
            }
        });
    }
}
