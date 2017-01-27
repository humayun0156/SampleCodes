package action_java_8.ch_02;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.BiFunction;
import java.util.function.Function;
import java.util.function.Supplier;

/**
 * @author humayun
 */
public class ConstructorRef {

    public static void main(String[] args) {
        Supplier<Apple> c1 = Apple::new; // () -> new Apple();
        Apple a1 = c1.get();
        System.out.println(a1);

        Function<Integer, Apple> c2 = Apple::new; // (weight) -> new Apple(weight)
        Apple a2 = c2.apply(110);
        System.out.println(a2);

        List<Integer> weights = Arrays.asList(7, 3, 4, 10);
        List<Apple> apples = map(weights, Apple::new);
        System.out.println(apples);

        BiFunction<Integer, String, Apple> c3 = Apple::new;
        Apple a3 = c3.apply(110, "green");
        System.out.println(a3);
    }

    static List<Apple> map(List<Integer> list, Function<Integer, Apple> f) {
        List<Apple> result = new ArrayList<>();
        for (Integer e: list) {
            result.add(f.apply(e));
        }
        return result;
    }


    public static class Apple {
        private int weight = 0;
        private String color = "";

        Apple(){}

        Apple(int weight) {
            this.weight = weight;
        }

        public Apple(int weight, String color){
            this.weight = weight;
            this.color = color;
        }

        public Integer getWeight() {
            return weight;
        }

        public void setWeight(Integer weight) {
            this.weight = weight;
        }

        public String getColor() {
            return color;
        }

        public void setColor(String color) {
            this.color = color;
        }

        public String toString() {
            return "Apple{" +
                    "color='" + color + '\'' +
                    ", weight=" + weight +
                    '}';
        }
    }
}
