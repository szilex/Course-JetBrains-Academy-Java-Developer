import java.util.Scanner;

class Main {
    public static void main(String[] args) {
        // put your code here
        Scanner scanner = new Scanner(System.in);
        int largest=0;
        while(scanner.hasNext()){
            int value = scanner.nextInt();
            if(value==0){
                break;
            } else
                if(value>largest){
                    largest = value;
                }

        }
        System.out.println(largest);
    }
}