package rxjava_sample.mytest.vogella;

import rx.Observable;
import rx.Observer;

import java.util.Arrays;
import java.util.List;

/**
 * @author Humayun
 */
public class RxJavaExample_1 {
    public static void main(String[] args) {
        List<String> list = Arrays.asList("Android", "Ubuntu", "Mac");

        Observable<List<String>> listObservable = Observable.just(list);
        listObservable.subscribe(new Observer<List<String>>() {
            @Override
            public void onCompleted() {
            }

            @Override
            public void onError(Throwable throwable) {
            }

            @Override
            public void onNext(List<String> strings) {
                System.out.println(strings);
            }
        });
    }
}
