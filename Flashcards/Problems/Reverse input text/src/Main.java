import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.Reader;
import java.util.List;
import java.util.ArrayList;

class Main {
    public static void main(String[] args) throws Exception {
        try (Reader reader = new BufferedReader(new InputStreamReader(System.in))) {
            List<Character> sequence = new ArrayList<>();
            int length = 0;
            int characterNumber = reader.read();
            while (characterNumber != -1) {
                sequence.add((char)characterNumber);
                length++;
                characterNumber = reader.read();
            }

            while(length > 0){
                System.out.print(sequence.get(--length));
            }
        }
    }
}