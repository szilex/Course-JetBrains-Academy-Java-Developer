import java.util.Scanner;

class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        String regNum = scanner.nextLine(); // a valid or invalid registration number

        String regex = "\\s*[ABEKMHOPCTYX]\\d{3}[ABEKMHOPCTYX]{2}\\s*";
        if (regNum.matches(regex)) {
            System.out.println("true");
        } else {
            System.out.println("false");
        }
    }
}