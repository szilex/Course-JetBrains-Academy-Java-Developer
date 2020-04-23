import java.util.Scanner;

class Main {
    public static void main(String[] args) {
        // put your code here
        Scanner scanner = new Scanner(System.in);
        StringBuilder stringBuilder = new StringBuilder();
        int input = scanner.nextInt();
        while(input !=0){
            if(input%2==0)
                stringBuilder.append("even\n");
             else
                stringBuilder.append("odd\n");
            input = scanner.nextInt();
        }
        System.out.println(stringBuilder.toString());
    }
}