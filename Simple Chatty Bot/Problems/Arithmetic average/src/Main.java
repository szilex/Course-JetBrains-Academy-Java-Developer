import java.util.Scanner;

class Main {
    public static void main(String[] args) {
        // put your code here
        Scanner scanner = new Scanner(System.in);
        int a = scanner.nextInt();
        int b = scanner.nextInt();

        if(a%3!=0){
            if((a+1)%3 == 0){
                a += 1;
            } else {
                a += 2;
            }
        }

        int average = 0;
        int counter = 0;
        for(int i=a; i<=b; i+=3){
            average+=i;
            counter++;
        }
        System.out.println((double)average/counter);
    }
}