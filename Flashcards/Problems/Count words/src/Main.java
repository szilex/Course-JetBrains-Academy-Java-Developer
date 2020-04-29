import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.Reader;

class Main {
    public static void main(String[] args) throws Exception {
        try (Reader reader = new BufferedReader(new InputStreamReader(System.in))) {
            int words = 0;
            boolean onlySpaces = true, characterAfterSpace = false, previousSpace = false;
            int characterNumber = reader.read();
            while (characterNumber != -1) {
                char character = (char) characterNumber;
                if(character == ' '){
                    if(!onlySpaces && !previousSpace) {
                        words++;
                        characterAfterSpace = false;
                        previousSpace = true;
                    }
                } else {
                    characterAfterSpace = true;
                    onlySpaces = false;
                    previousSpace = false;
                }
                characterNumber = reader.read();
            }

            if(characterAfterSpace) {
                words++;
            }

            System.out.println(words);
        }
    }
}