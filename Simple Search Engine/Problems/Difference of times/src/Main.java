import java.util.Scanner;

class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int hours1 = scanner.nextInt();
        int minutes1 = scanner.nextInt();
        int seconds1 = scanner.nextInt();
        int hours2 = scanner.nextInt();
        int minutes2 = scanner.nextInt();
        int seconds2 = scanner.nextInt();

        if (seconds1 > seconds2) {
            seconds2 += 60;
            minutes2 -= 1;
        }

        if (minutes1 > minutes2 || minutes2 < 0) {
            minutes2 += 60;
            hours2 -= 1;
        }

        System.out.println((hours2 - hours1) * 3600 + (minutes2 - minutes1) * 60 + seconds2 - seconds1);
    }
}