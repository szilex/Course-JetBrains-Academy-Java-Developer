import java.util.*;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        ArrayList<String> stringValues = new ArrayList<>(Arrays.asList(scanner.nextLine().split(" ")));
        ArrayList<Integer> values = new ArrayList<>();
        for(String stringValue : stringValues) {
            values.add(Integer.parseInt(stringValue));
        }

        int number = scanner.nextInt();
        int minDifference = Math.abs(number-values.get(0));
        ArrayList<Integer> minDifferenceValues = new ArrayList<>();
        for(Integer value : values) {
            int difference = Math.abs(number-value.intValue());
            if (difference < minDifference) {
                minDifference = difference;
                minDifferenceValues.clear();
                minDifferenceValues.add(value.intValue());
            } else if (difference == minDifference) {
                minDifferenceValues.add(value.intValue());
            }
        }
        Collections.sort(minDifferenceValues);
        for(Integer value : minDifferenceValues) {
            System.out.print(value.intValue() + " ");
        }
    }
}