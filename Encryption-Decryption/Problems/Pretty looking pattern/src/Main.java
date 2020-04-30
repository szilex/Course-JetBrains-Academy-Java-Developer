import java.io.CharConversionException;
import java.util.*;
import java.util.stream.Collectors;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        char[][] array = new char[4][4];

        for (int i = 0; i < 4; i++){
            array[i] = scanner.nextLine().toCharArray();
        }

        String answer = "YES";
        boolean continueProccessing = true;

        for (int i = 0; i < 3 && continueProccessing; i++) {
            for (int j = 0; j < 3 && continueProccessing; j++) {
                char character = array[i][j];
                int sameCharacterCounter = 0;
                for (int k = i; k < i + 2; k++) {
                    for (int l = j; l < j + 2; l++) {
                        if(array[k][l] == character) {
                            sameCharacterCounter++;
                        }
                    }
                }
                if (sameCharacterCounter == 4) {
                    answer = "NO";
                }
            }
        }

        System.out.println(answer);
    }
}