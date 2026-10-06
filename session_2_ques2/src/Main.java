import java.util.*;
import java.util.stream.Collectors;

public class Main {
    public static void main(String[] args) {
   List<Integer> numbers = Arrays.asList(4,12,7,20,9,15);
   List<Integer> printNumbers= numbers.stream().filter(number -> (number > 10)).sorted().collect(Collectors.toList());

        System.out.println(printNumbers);
    }
}