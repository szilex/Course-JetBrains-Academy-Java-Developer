import java.util.Scanner;

class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        long firstValue = scanner.nextLong();
        String operator = scanner.next();
        long secondValue = scanner.nextLong();

        switch(operator) {
            case "+":
                System.out.println(firstValue + secondValue);
                break;
            case "-":
                System.out.println(firstValue - secondValue);
                break;
            case "*":
                System.out.println(firstValue * secondValue);
                break;
            case "/":
                if(secondValue!=0) {
                    System.out.println(firstValue / secondValue);
                } else {
                    System.out.println("Division by 0!");
                }
                break;
            default:
                System.out.println("Unknown operator");
        }
    }
}