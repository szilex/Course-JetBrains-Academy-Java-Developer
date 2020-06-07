import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Queue;
import java.util.Scanner;
import java.util.stream.Collectors;

class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int amount = Integer.parseInt(scanner.nextLine());

        Queue<Integer> firstQueue = new ArrayDeque<>();
        Queue<Integer> secondQueue = new ArrayDeque<>();
        int firstLoadIndicator = 0;
        int secondLoadIndicator = 0;

        for (int i = 0; i < amount; i++) {
            int[] values = Arrays.stream(scanner.nextLine().split(" ")).mapToInt(Integer::parseInt).toArray();
            if (firstLoadIndicator <= secondLoadIndicator) {
                firstQueue.offer(values[0]);
                firstLoadIndicator += values[1];
            } else {
                secondQueue.offer(values[0]);
                secondLoadIndicator += values[1];
            }
        }

        firstQueue.stream().forEach(x -> System.out.print(x + " "));
        System.out.println();
        secondQueue.stream().forEach(x -> System.out.print(x + " "));
        System.out.println();
    }
}