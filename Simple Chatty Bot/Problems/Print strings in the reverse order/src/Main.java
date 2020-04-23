//put imports you need here
import java.util.Collections;
import java.util.Scanner;
import java.util.ArrayList;

class Main {
    public static void main(String[] args) {
        // put your code here
        Scanner scanner = new Scanner(System.in);
        ArrayList<String> list = new ArrayList<>();
        for(int i=0; i<3; i++){
            list.add(scanner.next());
        }
        Collections.reverse(list);
        for(int i=0; i<3; i++){
            System.out.println(list.get(i));
        }
    }
}