import java.util.*;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int size = Integer.parseInt(scanner.nextLine());
        int squaredSize = (int) Math.pow(size, 2);
        int[][] table = new int[squaredSize][squaredSize];

        for (int i = 0; i < squaredSize; i++) {
            table[i] = Arrays.stream(scanner.nextLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        }

        boolean[] rowControlTable = new boolean[squaredSize + 1];
        boolean[] columnControlTable = new boolean[squaredSize + 1];
        resetTable(rowControlTable, squaredSize + 1);
        resetTable(columnControlTable, squaredSize + 1);

        String answer = "YES";
        boolean foundError = false;

        //check all rows and columns

        for (int i = 0; i < squaredSize && !foundError; i++) {
            for (int j = 0; j < squaredSize && !foundError; j++) {
                if(table[i][j] >= 1 && table[i][j] <= squaredSize && table[j][i] >= 1 && table[j][i] <= squaredSize) {
                    if (rowControlTable[table[i][j]] || columnControlTable[table[j][i]]) {
                        answer = "NO";
                        foundError = true;
                    }
                    else {
                        rowControlTable[table[i][j]] = true;
                        columnControlTable[table[j][i]] = true;
                    }
                } else {
                    answer = "NO";
                    foundError = true;
                }

            }
            resetTable(rowControlTable, squaredSize + 1);
            resetTable(columnControlTable, squaredSize + 1);
        }

        // check all squares

        boolean[] controlTable = new boolean[squaredSize + 1];
        resetTable(controlTable, squaredSize + 1);

        for (int i = 0; i < squaredSize && !foundError; i += size) {
            for (int j = 0; j < squaredSize && !foundError; j += size) {
                for (int k = i; k < i + size && !foundError; k++) {
                    for (int l = j; l < j + size && !foundError; l++) {
                        if (controlTable[table[k][l]]) {
                            answer = "NO";
                            foundError = true;
                        }
                        else {
                            controlTable[table[k][l]] = true;
                        }
                    }
                }
                resetTable(controlTable, squaredSize + 1);
            }
        }

        System.out.println(answer);

    }

    private static void resetTable(boolean[] array, int size) {
        for (int i = 0; i < size; i++){
            array[i] = false;
        }
    }
}