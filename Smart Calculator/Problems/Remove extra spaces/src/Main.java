import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

class RemoveExtraSpacesProblem {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        String text = scanner.nextLine();

        Pattern pattern = Pattern.compile("\\s+");
        Pattern pattern2 = Pattern.compile("^\\s+|\\s+$");
        Matcher matcher = pattern.matcher(text);
        Matcher matcher2 = pattern2.matcher(matcher.replaceAll(" "));

        System.out.println(matcher2.replaceAll(""));
    }
}