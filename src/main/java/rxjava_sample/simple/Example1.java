package rxjava_sample.simple;

import rx.Observable;

import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.concurrent.TimeUnit;

import static java.util.stream.Collectors.toList;

public class Example1 {
    public static void main(String[] args) throws InterruptedException {
        basicOperations();
        //basicFlatMap();

        Thread.sleep(10 * 1000);
    }

    private static void basicOperations() {
        List<Integer> integers = Arrays.asList(2, 1, 3, 4, 5, 6, 7, 8);

        Observable<Integer> numbers = Observable.from(integers);
        numbers.subscribe(num -> {
            System.out.println("Number is " + num);
        });

        Observable<Integer> filteredObservables = numbers.filter(integer -> integer % 2 == 0);
        filteredObservables.subscribe(System.out::println);

        Observable<String> ids = numbers.map(num -> {
            return "Id: " + num;
        });
        ids.subscribe(System.out::println);
    }

    private static void basicFlatMap() {
        List<Integer> integers = Arrays.asList(2, 1, 3, 4, 5, 6, 7, 8);
        Observable<Integer> numbers = Observable.from(integers);

        Observable<Person> personObservable = numbers.flatMap(num -> {
            return getPersonById(num);
        });

        personObservable.subscribe(System.out::println);
    }

    private static Observable<Person> getPersonById(int id) {
        Random random = new Random();
        int delay = random.nextInt(5);
        return Observable.just(new Person(id)).delay(delay, TimeUnit.SECONDS);
    };

    private static void basicEvent() throws InterruptedException {
        Observable<Long> interval = Observable.interval(1, TimeUnit.SECONDS);
        interval.subscribe(l -> {
            System.out.println("Interval " + l);
        });

        Observable<Long> fixed = interval.take(5);
        fixed.subscribe(l -> {
            System.out.println("Fixed " + l);
        });
    }

    private static void collectionAndRx() {
        List<Integer> integers = Arrays.asList(2, 1, 3, 4, 5, 6, 7, 8);
        List<Integer> filtered = integers.stream().filter(i -> i % 2 == 0).collect(toList());
        System.out.println(integers);
        System.out.println(filtered);

        Observable<Integer> numbers = Observable.from(integers);
        numbers.subscribe(num -> {
            System.out.println("Number is " + num);
        });

        Observable<Integer> filteredObservables = numbers.filter(integer -> integer % 2 == 0);
        filteredObservables.subscribe(System.out::println);
    }
}
