import java.util.Scanner;

public class Main {

    public static int convert(Long val) {
        int result = 0;
        if(val != null) {
            long unboxedVal = val.longValue();
            if (unboxedVal > Integer.MAX_VALUE) {
                result = Integer.MAX_VALUE;
            } else if(unboxedVal < Integer.MIN_VALUE) {
                result = Integer.MIN_VALUE;
            } else {
                result = (int) unboxedVal;
            }
        }
        return result;
    }

    /* Do not change code below */
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String val = scanner.nextLine();
        Long longVal = "null".equals(val) ? null : Long.parseLong(val);
        System.out.println(convert(longVal));
    }
}