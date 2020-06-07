import java.util.*;

class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String input = scanner.nextLine();

        Deque<Character> queue = new ArrayDeque<>();
        Map<Character, Character> signs = new HashMap<>();
        signs.put(')', '(');
        signs.put(']', '[');
        signs.put('}', '{');

        boolean correct = true;

        for (char letter : input.toCharArray()) {
            if (letter == '(' || letter == '[' || letter == '{') {
                queue.offerLast(letter);
            } else {
                Character openingBracket = queue.pollLast();
                if (openingBracket == null) {
                    correct = false;
                    break;
                } else {
                    if (signs.containsKey(letter) && openingBracket == signs.get(letter)) {
                        continue;
                    } else {
                        //System.out.println(openingBracket + " " + letter);
                        correct = false;
                        break;
                    }
                }
            }
        }

        if (!correct || !queue.isEmpty()) {
            System.out.println("false");
        } else {
            System.out.println("true");
        }
    }
}