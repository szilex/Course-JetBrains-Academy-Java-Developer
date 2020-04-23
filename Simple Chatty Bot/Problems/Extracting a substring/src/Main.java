import java.util.Scanner;

class Main {
    public static void main(String[] args) {
        // put your code here
        Scanner scanner = new Scanner(System.in);
        String value = scanner.next();
        int startingIndex = scanner.nextInt();
        int endingIndex = scanner.nextInt();
        System.out.println(value.substring(startingIndex, endingIndex+1));
    }
}