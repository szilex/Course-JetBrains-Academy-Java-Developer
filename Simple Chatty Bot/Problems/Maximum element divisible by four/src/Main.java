import java.util.Scanner;

class Main {
    public static void main(String[] args) {
        // put your code here
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        int max = 0;
        for(int i=0; i<n; i++){
            int tmp=scanner.nextInt();
            if(tmp%4==0 && tmp>max){
                max = tmp;
            }
        }
        System.out.println(max);
    }
}