import java.util.Scanner;
import java.util.HashSet;
import java.util.Arrays;
import java.util.Set;
import java.util.Collection;

class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int dictionaryLength = Integer.parseInt(scanner.nextLine());
        Collection<String> dictionary = new HashSet<>();
        for(int i = 0; i < dictionaryLength; i++) {
            dictionary.add(scanner.nextLine().toLowerCase());
        }

        int lineAmount = Integer.parseInt(scanner.nextLine());
        Collection<String> erroneousWords = new HashSet<>();
        for(int i = 0; i < lineAmount; i++) {
            Collection<String> wordsInLine = new HashSet<>(Arrays.asList(scanner.nextLine().toLowerCase().split(" ")));
            wordsInLine.removeAll(dictionary);
            erroneousWords.addAll(wordsInLine);
        }
        erroneousWords.forEach(System.out::println);
    }
}