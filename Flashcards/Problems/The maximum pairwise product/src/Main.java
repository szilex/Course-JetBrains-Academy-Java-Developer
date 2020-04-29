import java.util.Scanner;
class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int size = scanner.nextInt();
        int max = 0, oldValue = scanner.nextInt(), value;
        for(int i = 0; i < size - 1; i++ ){
            value = scanner.nextInt();
            int product = oldValue * value;
            if(product > max){
                max = product;
            }
            oldValue = value;
        }
        System.out.println(max);
    }
}