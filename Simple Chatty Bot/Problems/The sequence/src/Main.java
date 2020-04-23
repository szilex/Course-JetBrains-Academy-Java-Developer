import java.util.Scanner;

class Main {
    public static void main(String[] args) {
        // put your code here
        Scanner scanner = new Scanner(System.in);
        int len = scanner.nextInt();

        for(int i=1, counter=0; counter<len; i++){
            for(int j=i; j>0; j--){
                System.out.print(i+" ");
                counter++;
                if(counter==len)
                    break;
            }
        }
    }
}