import java.util.Scanner;

class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String line = scanner.nextLine();
        String word = scanner.nextLine();
        int counter = 0;

        for (int i = 0; i < line.length() - word.length() + 1; i++) {
            if (line.charAt(i) == word.charAt(0)) {
                if (line.startsWith(word, i)) {
                    counter++;
                }
            }
        }
        System.out.println(counter);
    }
}