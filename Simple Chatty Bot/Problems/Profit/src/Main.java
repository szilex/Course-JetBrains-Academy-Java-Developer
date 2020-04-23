import java.util.*;

public class Main {
    public static void main(String[] args) {
        // write your code here
        Scanner scanner = new Scanner(System.in);
        int m = scanner.nextInt();
        int p = scanner.nextInt();
        int k = scanner.nextInt();

        int years = 0;
        double rate = 1.0 + (double)p/100;
        double current_value = m;

        while(current_value<k){
            current_value *= rate;
            years++;
        }
        System.out.println(years);
    }
}