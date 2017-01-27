package rxjava_sample.simple;

import rx.Observable;
import rx.Subscriber;

public class CreateObservables {
    public static void main(String[] args) {
        withCreate();
    }

    private static void withCreate() {
        Observable<String> strings = Observable.create(new Observable.OnSubscribe<String>() {
            @Override
            public void call(Subscriber<? super String> subscriber) {
                try {
                    if (!subscriber.isUnsubscribed()) {
                        for(int i=0; i<5; i++) {
                            subscriber.onNext("Value is " + i);
                        }
                        subscriber.onCompleted();
                    }
                } catch (Exception e) {
                    subscriber.onError(e);
                }
            }
        });

        strings.subscribe(System.out::println);
    }

    private static void withJust() {
        Observable<String> strings = Observable.just("abcd");
        strings.subscribe(System.out::println);
    }
}
