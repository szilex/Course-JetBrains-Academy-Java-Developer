import java.util.Arrays;
import java.util.Scanner;
import java.util.stream.Stream;

class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int amount = Integer.parseInt(scanner.nextLine());
        int[] values = Stream.of(scanner.nextLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        int min = Arrays.stream(values).min().getAsInt();

        System.out.println(min);
    }
}