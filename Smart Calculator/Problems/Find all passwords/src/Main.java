import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

class Main {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        String text = scanner.nextLine();

        Pattern passwordPattern = Pattern.compile("password:?", Pattern.CASE_INSENSITIVE);
        Matcher passwordMatcher = passwordPattern.matcher(text);

        if (passwordMatcher.find()) {
            do {

                String remainderText = text.substring(passwordMatcher.start() + passwordMatcher.group().length());

                Matcher matcher = Pattern.compile("[\\d\\w]+", Pattern.CASE_INSENSITIVE).matcher(remainderText);
                if (matcher.find()) {
                    System.out.println(matcher.group());
                }
            } while (passwordMatcher.find());
        } else {
            System.out.println("No passwords found.");
        }

    }
}