import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Main {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String stringWithHTMLTags = scanner.nextLine();

        Pattern pattern = Pattern.compile("<.*?>");
        Matcher matcher = pattern.matcher(stringWithHTMLTags);

        System.out.println(matcher.replaceAll(""));
    }
}