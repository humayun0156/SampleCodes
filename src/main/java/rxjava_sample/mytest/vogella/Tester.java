package rxjava_sample.mytest.vogella;

import rx.Observable;

import java.util.Arrays;
import java.util.List;

/**
 * @author Humayun
 */
public class Tester {
    public static void main(String[] args) {
        // 1
        Observable<String> hello = Observable.just("Hello");
        hello.subscribe(System.out::println);

        // 2
        Observable.just("One", "Two").subscribe(System.out::println);

        // 3
        System.out.println("============  3 ==================");
        List<String> words = Arrays.asList("the", "quick", "brown", "fox", "jumped", "over", "the", "lazy", "dogs");
        Observable.just(words)
                .subscribe(word -> System.out.println(word));
        Observable.from(words)
                .subscribe(System.out::println);

        //4
        System.out.println("============  4 ==================");
        Observable.from(words)
                .zipWith(Observable.range(1, Integer.MAX_VALUE),
                        (string, count) -> String.format("%2d. %s", count, string))
                .subscribe(System.out::println);

        //5
        System.out.println("============  5 ==================");
        Observable.from(words)
                .flatMap(word -> Observable.from(word.split("")))
                .zipWith(Observable.range(1, Integer.MAX_VALUE),
                        (string, count) -> String.format("%2d. %s", count, string))
                .subscribe(System.out::println);

        //6
        System.out.println("============  6 ==================");
        Observable.from(words)
                .flatMap(word -> Observable.from(word.split("")))
                .distinct()
                .sorted()
                .zipWith(Observable.range(1, Integer.MAX_VALUE),
                        (string, count) -> String.format("%2d. %s", count, string))
                .subscribe(System.out::println);
    }
}
