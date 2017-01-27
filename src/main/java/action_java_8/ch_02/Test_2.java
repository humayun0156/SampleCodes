package action_java_8.ch_02;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.Consumer;
import java.util.function.Function;

/**
 * @author humayun
 */
public class Test_2 {
    public static void main(String[] args) {
        forEach(
                Arrays.asList(1,2,3,4,5),
                //(Integer i) -> System.out.println(i)
                System.out::println
        );

        List<Integer> l = map(
                Arrays.asList("lambdas", "in", "action"),
                //(String s) -> s.length()
                String::length
        );
        System.out.println(l);

        List<String> str = Arrays.asList("a", "b", "A", "B");
        str.sort(String::compareToIgnoreCase);
        System.out.println(str);
    }

    static<T> void forEach(List<T> list, Consumer<T> c) {
        for (T i : list) {
            c.accept(i);
        }
    }

    static <T,R> List<R> map(List<T> list, Function<T, R> f) {
        List<R> result = new ArrayList<R>();
        for (T t : list) {
            result.add(f.apply(t));
        }
        return result;
    }
}
