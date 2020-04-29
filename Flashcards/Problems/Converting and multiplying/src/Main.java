import java.util.Scanner;
import java.text.ParseException;

class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String line = scanner.next();
        while(!"0".equals(line)) {
            try {
                int value = Integer.parseInt(line) * 10;
                System.out.println(value);
            } catch(NumberFormatException e) {
                System.out.println("Invalid user input: " + line);
            } finally {
                line = scanner.next();
            }
        }
    }
}