package action_java_8.ch_02;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

/**
 * @author humayun
 */
public class Test {
    public static void main(String[] args) throws Exception {
        String ss = "abcd";

        System.out.println();
        Runnable r1 = () -> System.out.println("Hello world 1");

        Runnable r2 = new Runnable() {
            @Override
            public void run() {
                System.out.println("Hello world 2");
            }
        } ;

        process(r1);
        process(r2);
        process(() -> System.out.println("Hello world 3"));


        System.out.println(processFile());
        String result = processFile((BufferedReader br) -> br.readLine() + br.readLine());
        System.out.println(result);
    }

    public static void process(Runnable r) {
        r.run();
    }

    static String processFile() throws Exception {
        try (BufferedReader br = new BufferedReader(new FileReader("/home/humayun/.bashrc"))) {
            return br.readLine();
        }
    }

    static String processFile(BufferedReaderProcessor p) throws Exception {
       try (BufferedReader br = new BufferedReader(new FileReader("/home/humayun/.bashrc"))) {
           return p.process(br);
       }
    }
}

@FunctionalInterface
interface BufferedReaderProcessor {
    String process(BufferedReader br) throws IOException;
}
