package tictactoe;

import java.util.Arrays;
import java.util.Scanner;

public class Main {

    private static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {

        boolean gameOver = false;
        boolean xTurn = true;
        String symbols = "         ";
        displayMenu(symbols);

        while (!gameOver){
            symbols = makeMove(symbols, xTurn ? 'X' : 'O');
            displayMenu(symbols);
            gameOver = isGameOver(symbols);
        }
    }

    private static void displayMenu(String symbols) {
        System.out.println("---------");
        for (int i = 0; i < symbols.length(); i += 3) {
            System.out.printf("| %c %c %c |\n", symbols.charAt(i), symbols.charAt(i + 1), symbols.charAt(i + 2));
        }
        System.out.println("---------");
    }

    private static String makeMove(String symbols, char player) {
        int column = 0;
        int row = 0;
        boolean correctInput = false;
        String result = "";

        while (!correctInput) {
            System.out.print("Enter the coordinates: ");
            String[] line = scanner.nextLine().split(" ");

            if (line.length == 2) {
                char[] input = new char[] {line[0].charAt(0), line[1].charAt(0)};
                if (Character.isDigit(input[0]) && Character.isDigit(input[1])) {
                    column = Integer.parseInt(String.valueOf(input[0]));
                    row = Integer.parseInt(String.valueOf(input[1]));
                    if (row < 1 || row > 3 || column < 1 || column > 3){
                        System.out.println("Coordinates should be from 1 to 3!");
                    } else{
                        if (symbols.charAt((3 - row) * 3 + column - 1) == ' ') {
                            StringBuilder stringBuilder = new StringBuilder(symbols);
                            stringBuilder.setCharAt((3 - row) * 3 + column - 1, 'X');
                            result = stringBuilder.toString();
                            correctInput = true;
                        } else {
                            System.out.println("This cell is occupied! Choose another one!");
                        }
                    }
                } else {
                    System.out.println("You should enter numbers!");
                }
            } else {
                System.out.println("You should enter numbers!");
            }
        }
        return result;
    }

    private static boolean isGameOver(String symbols) {
        int xAmount = 0;
        int oAmount = 0;

        for(int i = 0; i < symbols.length(); i++) {
            if (symbols.charAt(i) == 'X') {
                xAmount++;
            } else if (symbols.charAt(i) == 'O') {
                oAmount++;
            }
        }

        boolean xWon = false;
        boolean oWon = false;

        for (int i = 0; i < symbols.length(); i += 3) {
            String row = symbols.substring(i, i + 3);
            if (row.equals("XXX")) {
                xWon = true;
            } else if (row.equals("OOO")) {
                oWon = true;
            }
        }

        for (int i = 0; i < 3; i++) {
            if (symbols.charAt(i) == symbols.charAt(i + 3) && symbols.charAt(i) == symbols.charAt(i + 6)) {
                if (symbols.charAt(i) == 'X') {
                    xWon = true;
                } else if (symbols.charAt(i) == 'O') {
                    oWon = true;
                }
            }
        }

        if (symbols.charAt(0) == symbols.charAt(4) && symbols.charAt(0) == symbols.charAt(8) || symbols.charAt(2) == symbols.charAt(4) && symbols.charAt(0) == symbols.charAt(6)) {
            if (symbols.charAt(4) == 'X') {
                xWon = true;
            } else if (symbols.charAt(4) == 'O') {
                oWon = true;
            }
        }

        if (xWon && oWon) {
            System.out.println("Impossible");
        } else if (xWon) {
            System.out.println("X wins");
            return true;
        } else if (oWon) {
            System.out.println("O wins");
            return true;
        } else {
            if (xAmount + oAmount == 9) {
                System.out.println("Draw");
                return true;
            }
        }
        return false;
    }

}
