import java.util.*;
import java.util.stream.Collectors;

public class Main {

    public static void main(String[] args) {
        List<GreekLetter> letterList = new ArrayList<>();

        letterList.add(new GreekLetter("Gamma",  3));
        letterList.add(new GreekLetter("Omega", 24));
        letterList.add(new GreekLetter("Alpha",  1));

        System.out.println(letterList.stream().map(x -> x.toString()).collect(Collectors.joining("\n")));
    }

    static class GreekLetter {

        private String letter;
        private Integer position;

        public GreekLetter(String letter, Integer position) {
            this.letter = letter;
            this.position = position;
        }

        @Override
        public String toString() {
            return "{" +
                    "letter='" + letter + '\'' +
                    ", position=" + position +
                    '}';
        }
    }
}