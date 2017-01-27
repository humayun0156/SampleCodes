package rxjava_sample.simple;

public class Person {
    private int id;

    public Person(int id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "Person [" + this.id + "]";
    }
}
