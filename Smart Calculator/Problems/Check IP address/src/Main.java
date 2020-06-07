import java.util.*;

public class Main {
    public static void main(String[] args) {
        String line = new Scanner(System.in).nextLine();
        String[] values = line.split("\\.");
        /*boolean correct = true;
        if (values.length == 4) {
            for (int i = 0; i < 4; i++) {
                if(!values[i].matches("((\\d)|([1-9]\\d)|(1\\d\\d)|(2[0-4]\\d)|(25[0-5]))")) {
                    correct = false;
                }
            }
        } else {
            correct = false;
        }

        if (correct) {
            System.out.println("YES");
        } else {
            System.out.println("NO");
        }*/

        String regex = "\\s*(((\\d)|([1-9]\\d)|(1\\d\\d)|(2[0-4]\\d)|(25[0-5]))\\.){3}((\\d)|([1-9]\\d)|(1\\d\\d)|(2[0-4]\\d)|(25[0-5]))\\s*";
        if (line.matches(regex)) {
            System.out.println("YES");
        } else {
            System.out.println("NO");
        }
    }
}