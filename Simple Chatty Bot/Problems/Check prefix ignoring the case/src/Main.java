import java.util.Scanner;

class Main {
    public static void main(String[] args) {
        // put your code here
        Scanner scanner = new Scanner(System.in);
        String text = scanner.next();
        System.out.println(text.toLowerCase().startsWith("j"));
    }
}