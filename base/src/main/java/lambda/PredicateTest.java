package lambda;

import java.util.Arrays;
import java.util.List;
import java.util.function.Predicate;

/**
 * @Description
 * @Author lzb
 * @Date 2021/5/27 10:59
 * @Version 1.0
 */
public class PredicateTest {
    public static void main(String[] args) {
        List languages = Arrays.asList("Java", "Scala", "C++", "Haskell", "Lisp");

        System.out.println("Languages which starts with J :");
        Predicate<String> startsWith = (n) -> n.startsWith("J");
        filter(languages, startsWith);

        System.out.println("Languages which ends with a ");
        Predicate<String> endsWith = (n) -> n.endsWith("a");
        filter(languages, endsWith);

        System.out.println("Print all languages :");
        filter(languages, (str) -> true);

        System.out.println("Print no language : ");
        filter(languages, (str) -> false);

        System.out.println("Print language whose length greater than 4:");
        //filter(languages, (str) -> str.length() > 4);
    }

    public static void filter(List names, Predicate condition) {
        names.stream().filter((name) -> (condition.test(name))).forEach((name) -> {
            System.out.println(name + " ");
        });
    }
}
