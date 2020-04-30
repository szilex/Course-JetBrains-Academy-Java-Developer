import java.util.*;

public class Main {

    public static void main(String[] args) {
        Random random = new Random(5);
        Scanner scanner = new Scanner(System.in);
        int A = scanner.nextInt();
        int B = scanner.nextInt();
        int C = scanner.nextInt();
        int N = scanner.nextInt();
        int counterA = 0;
        int counterB = 0;
        int counterC = 0;
        int index = 0;
        char[] password = new char[N];

        while (index < N) {
            int value = random.nextInt(4);
            switch (value) {
                case 0 :
                    if (counterA < A || (counterB >= B && counterC >= C)) {
                        password[index] = (char) ('A' + random.nextInt(25));
                        if (index >= 1) {
                            while (password[index] == password[index - 1]) {
                                password[index] = (char) ('A' + random.nextInt(25));
                            }
                        }
                        counterA++;
                    } else {
                        index--;
                    }
                    break;
                case 1 :
                    if (counterB < B || (counterA >= A && counterC >= C)) {
                        password[index] = (char) ('a' + random.nextInt(25));
                        if (index >= 1) {
                            while (password[index] == password[index - 1]) {
                                password[index] = (char) ('a' + random.nextInt(25));
                            }
                        }
                        counterB++;
                    } else {
                        index--;
                    }
                    break;
                case 2 :
                    if (counterC < C || (counterA >= A && counterB >= B)) {
                        password[index] = (char) ('0' + random.nextInt(10));
                        if (index >= 1) {
                            while (password[index] == password[index - 1]) {
                                password[index] = (char) ('0' + random.nextInt(10));
                            }
                        }
                        counterC++;
                    } else {
                        index--;
                    }
                    break;
                case 3 :
                    if (counterC >= C && counterA >= A && counterB >= B) {
                        password[index] = (char) ('!' + random.nextInt(14));
                    } else {
                        index--;
                    }
                    break;
            }
            index++;
        }

        for (int i = 0; i < N; i++) {
            System.out.print(password[i]);
        }
    }


}