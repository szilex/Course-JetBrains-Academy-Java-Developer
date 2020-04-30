import java.util.Scanner;

class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String line = scanner.nextLine();
        boolean ordered = true;

        for (int i = 0; i < line.length() - 1 && ordered; i++) {
            if (line.charAt(i) != line.charAt(i+1) - 1) {
                System.out.println("false");
                ordered = false;
            }
        }

        if (ordered) {
            System.out.println("true");
        }

    }
}