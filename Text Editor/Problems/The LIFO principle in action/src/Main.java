import java.util.List;
import java.util.Scanner;
import java.util.ArrayList;
import java.util.Collections;

class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        scanner.nextLine();

        List<Integer> values = new ArrayList<>();

        while(scanner.hasNext()) {
            values.add(Integer.parseInt(scanner.nextLine()));
        }

        Collections.reverse(values);

        values.forEach(System.out::println);
    }
}