package rxjava_sample.intermediate;

import rx.Observable;
import rxjava_sample.simple.Person;

import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.concurrent.TimeUnit;

public class PersonService {
    public Observable<Person> getPersonById(int id) {
        Random random = new Random();
        int delay = random.nextInt(5);
        return Observable.just(new Person(id)).delay(delay, TimeUnit.SECONDS);
    };

    public Observable<Person> getPersonsByIds(List<Integer> ids) {
        return Observable.from(ids).flatMap(id -> {
            return getPersonById(id);
        });
    }

    public static void main(String[] args) throws InterruptedException {
        PersonService service = new PersonService();
        Observable<Person> personsByIds = service.getPersonsByIds(Arrays.asList(1, 2, 3, 4, 5, 6));
        personsByIds.subscribe(System.out::println);

        Thread.sleep(10 * 1000);
    }
}
