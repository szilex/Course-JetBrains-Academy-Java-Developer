import java.util.Scanner;

class Main {
    public static void main(String[] args) {
        // put your code here
        Scanner scanner = new Scanner(System.in);
        int input = scanner.nextInt();
        boolean orderAsc = true, orderDesc = true;
        while(input!=0){
            int nextValue = scanner.nextInt();
            if(nextValue==0)
                break;

            if(nextValue < input)
                orderAsc = false;
            else if(nextValue > input)
                orderDesc = false;

            input = nextValue;
        }
        if(orderAsc||orderDesc)
            System.out.println("true");
        else
            System.out.println("false");
    }
}