import java.util.Scanner;

class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int height = scanner.nextInt();
        int width = scanner.nextInt();
        int[][] array = new int[height][width];

        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                array[i][j] = scanner.nextInt();
            }
        }

        int max = array[0][0];
        int widthIndex = 0;
        int heightIndex = 0;

        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                if (array[i][j] > max) {
                    max = array[i][j];
                    heightIndex = i;
                    widthIndex = j;
                }
            }
        }

        System.out.printf("%d %d", heightIndex, widthIndex);
    }
}