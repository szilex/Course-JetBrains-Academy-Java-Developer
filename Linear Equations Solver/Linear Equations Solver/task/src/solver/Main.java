package solver;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;

public class Main {
    public static void main(String[] args) {
        String inFileName = "";
        String outFileName = "";

        for (int i = 0; i < args.length; i++) {
            switch (args[i]) {
                case "-in" :
                    inFileName = args[i + 1];
                    break;
                case "-out" :
                    outFileName = args[i + 1];
                    break;
            }
        }

        if (inFileName.isEmpty()) System.exit(1);

        ComplexNumber[][] coefficients = getCoefficientsFromFile(inFileName);
        System.out.println();

        for (ComplexNumber[] coefficient : coefficients) {
            for (int j = 0; j < coefficients[0].length; j++) {
                System.out.println(coefficient[j].getReal() + " " + coefficient[j].getImaginary());
            }
            System.out.println(Arrays.toString(coefficient));
        }
        System.out.println();

        ResultAmount resultAmount = ResultAmount.NONE;
        try {
            LinkedHashMap<Integer, int[]> swaps = gaussElimination(coefficients);
            if (swaps.size() > 0) {
                List<Integer> reverseKeys = new ArrayList<>(swaps.keySet());
                Collections.reverse(reverseKeys);
                for (Integer key : reverseKeys) {
                    swapColumns(coefficients, swaps.get(key)[0], swaps.get(key)[1]);
                    swapRows(coefficients, swaps.get(key)[0], swaps.get(key)[1]);
                }
            }
            resultAmount = ResultAmount.ONE;
        } catch (IllegalArgumentException e) {
            switch (e.getMessage()) {
                case "No solutions" :
                    resultAmount = ResultAmount.NONE;
                    break;
                case "Infinitely many solutions" :
                    resultAmount = ResultAmount.INFINITE;
                    break;
            }
        } finally {
            saveCoefficientsToFile(outFileName, coefficients, resultAmount);
            System.out.println();
        }

    }

    public static ComplexNumber[][] getCoefficientsFromFile(String fileName) {
        Path path = Paths.get(fileName);
        Path absolutePath = path.toAbsolutePath().normalize();
        try (Scanner scanner = new Scanner(new File(absolutePath.toString()))) {
            String[] values = scanner.nextLine().split(" ");
            int width = Integer.parseInt(values[0]);
            int height = Integer.parseInt(values[1]);
            ComplexNumber[][] complexNumbers = new ComplexNumber[height][width + 1];

            for (int i = 0; i < height; i++) {
                String line = scanner.nextLine();
                System.out.println(line);
                String[] stringValues = line.split(" ");
                List<ComplexNumber> complexNumberList = new ArrayList<>();
                for (String value : stringValues) {
                    complexNumberList.add(ComplexNumber.fromString(value));
                }
                complexNumberList.toArray(complexNumbers[i]);
            }
            return complexNumbers;
        } catch (IOException e) {
            return null;
        }
    }

    public static void saveCoefficientsToFile(String fileName, ComplexNumber[][] values, ResultAmount resultAmount) {
        Path path = Paths.get(fileName);
        Path absolutePath = path.toAbsolutePath().normalize();
        try (PrintWriter printWriter = new PrintWriter(new File(absolutePath.toString()))) {
            switch (resultAmount) {
                case ONE:
                    for (int i = 0; i < values[0].length-1; i++) {
                        printWriter.println(values[i][values[0].length-1]);
                        System.out.println(values[i][values[0].length-1]);
                    }
                    break;
                case INFINITE:
                    printWriter.println("Infinitely many solutions");
                    break;
                case NONE:
                    printWriter.println("No solutions");
                    break;
            }

        } catch (IOException ignored) {

        }
    }

    public static LinkedHashMap<Integer, int[]> gaussElimination(ComplexNumber[][] values) throws IllegalArgumentException{

        LinkedHashMap<Integer, int[]> columnSwaps = new LinkedHashMap<>();
        int height = values.length;
        int width = values[0].length;

        for (int i = 0; i < width - 1; i++) {
            if (i == height) {
                if (!isMatrixValid(values)) {
                    throw new IllegalArgumentException("No solutions");
                } else {
                    throw new IllegalArgumentException("Infinitely many solutions");
                }
            }

            ComplexNumber coefficient = values[i][i];

            if (coefficient.getReal() == 0 && coefficient.getImaginary() == 0) {
                boolean foundValue = false;

                for (int j = i + 1; j < height && !foundValue; j++) {
                    if (values[j][i].getReal() != 0 || values[j][i].getImaginary() != 0) {
                        swapRows(values, i, j);
                        foundValue = true;
                    }
                }

                for (int j = i + 1; j < width - 1 && !foundValue; j++) {
                    if (values[i][j].getReal() != 0 || values[i][j].getImaginary() != 0) {
                        swapColumns(values, i, j);
                        columnSwaps.put(columnSwaps.size(), new int[]{i, j});
                        foundValue = true;
                    }
                }

                for (int j = i + 1; j < height && !foundValue; j++) {
                    for (int k = i + 1; k < width - 1 && !foundValue; k++) {
                        if (values[j][k].getReal() != 0 || values[j][k].getImaginary() != 0) {
                            swapRows(values, i, j);
                            swapColumns(values, i, k);
                            columnSwaps.put(columnSwaps.size(), new int[]{i, k});
                            foundValue = true;
                        }
                    }
                }

                if (!foundValue) {
                    if(values[i][values[i].length-1].getReal() == 0 || values[i][values[i].length-1].getImaginary() == 0) {
                        for (ComplexNumber[] value : values) {
                            boolean emptyRow = true;
                            for (int k = 0; k < width - 1; k++) {
                                if (value[k].getReal() != 0 || value[k].getImaginary() != 0) {
                                    emptyRow = false;
                                    break;
                                }
                            }

                            if (emptyRow && (value[width - 1].getReal() != 0 || value[width - 1].getImaginary() != 0)) {
                                throw new IllegalArgumentException("No solutions");
                            }
                        }
                        throw new IllegalArgumentException("Infinitely many solutions");
                    } else {
                        throw new IllegalArgumentException("No solutions");
                    }
                } else {
                    coefficient = values[i][i];
                }
            }

            for (int j = i; j < width; j++) {
                values[i][j] = ComplexNumberCalculator.divide(values[i][j], coefficient);
            }

            for (int j = i + 1; j < height; j++) {
                ComplexNumber rowCoefficient = ComplexNumberCalculator.divide(values[j][i], values[i][i]);
                for (int k = i; k < width; k++) {
                    values[j][k] = ComplexNumberCalculator.subtract(values[j][k], ComplexNumberCalculator.multiply(rowCoefficient, values[i][k]));
                }
            }
        }
        int emptyRows = 0;
        for (int i = 0; i < height; i++) {
            boolean emptyRow = true;
            for (int j = i; j < width - 1; j++) {
                if (values[i][j].getReal() != 0 || values[i][j].getImaginary() != 0) {
                    emptyRow = false;
                    break;
                }
            }
            if (emptyRow) {
                if (values[i][width-1].getReal() == 0 && values[i][width-1].getImaginary() == 0) {
                    emptyRows++;
                } else {
                    throw new IllegalArgumentException("No solutions");
                }
            }
        }

        if (height - emptyRows < width - 2) {
            throw new IllegalArgumentException("Infinitely many solutions");
        }

        for (int i = width - 2; i > 0; i--) {
            for (int j = i - 1; j >= 0; j--) {
                values[j][width - 1] = ComplexNumberCalculator.subtract(values[j][width - 1], ComplexNumberCalculator.multiply(values[i][width - 1], values[j][i]));
                values[j][i] = new ComplexNumber(0.0, 0.0);
            }
        }

        return columnSwaps;
    }

    public static void swapRows(ComplexNumber[][] values, int index1, int index2) {
        ComplexNumber[] tmp = values[index1];
        values[index1] = values[index2];
        values[index2] = tmp;
    }

    public static void swapColumns(ComplexNumber[][] values, int index1, int index2) {
        int height = values.length;
        for (int i = 0; i < height; i++) {
            ComplexNumber tmp = values[i][index1];
            values[i][index1] = values[i][index2];
            values[i][index2] = tmp;
        }
    }

    public static boolean isMatrixValid(ComplexNumber [][] values) {
        int height = values.length;
        int width = values[0].length;

        for (ComplexNumber[] value : values) {
            boolean emptyRow = true;
            for (int k = 0; k < width - 1; k++) {
                if (value[k].getReal() != 0 && value[k].getImaginary() != 0) {
                    emptyRow = false;
                    break;
                }
            }

            if (emptyRow && value[width - 1].getReal() != 0 && value[width - 1].getImaginary() != 0) {
                return false;
            }
        }

        return true;
    }
}

class ComplexNumber {
    private final double real;
    private final double imaginary;

    public ComplexNumber(double real, double imaginary) {
        this.real = real;
        this.imaginary = imaginary;
    }

    public double getReal() {
        return real;
    }

    public double getImaginary() {
        return imaginary;
    }

    public static ComplexNumber fromString(String complexNumber) {
        double real = 0.0;
        double imaginary = 0.0;

        String[] values = complexNumber.split("\\d[-+]");
        if (values.length == 2) {
            int signIndex = complexNumber.indexOf(values[1]);

            real = Double.parseDouble(complexNumber.substring(0, signIndex - 1));
            imaginary = getImaginary(values[1]);


            if (complexNumber.charAt(signIndex-1) == '-') {
                imaginary = -imaginary;
            }
        } else {
            if (complexNumber.contains("i")) {
                imaginary = getImaginary(complexNumber);
                /*if (complexNumber.charAt(0) == '-') {
                    imaginary = -imaginary;
                }*/
            } else {
                real = Double.parseDouble(complexNumber);
            }
        }

        return new ComplexNumber(real, imaginary);
    }

    private static Double getImaginary(String imaginaryString) {
        double imaginary = 0.0;
        if(imaginaryString == null || imaginaryString.isEmpty()) {
            return imaginary;
        }

        imaginaryString = imaginaryString.substring(0, imaginaryString.length() - 1);
        if (imaginaryString.length() > 0 && imaginaryString.matches("-?\\d+(.\\d+)?")) {
            imaginary = Double.parseDouble(imaginaryString);
        } else if (imaginaryString.length() == 0){
            imaginary = 1.0;
        } else {
            imaginary = -1.0;
        }
        return imaginary;
    }

    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();

        if (real != 0) {
            stringBuilder.append(real);
        }

        if (stringBuilder.length() > 0 && imaginary > 0) {
            stringBuilder.append("+");
        }

        if (imaginary != 0) {
            if (imaginary < 0) {
                stringBuilder.append("-");
            }
            if (Math.abs(imaginary) != 1) {
                stringBuilder.append(Math.abs(imaginary));
            }
            stringBuilder.append("i");
        }

        if (stringBuilder.length() == 0) {
            stringBuilder.append(0.0);
        }

        return stringBuilder.toString();
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        long temp;

        temp = Double.doubleToLongBits(real);
        result = prime * result + (int) (temp ^ (temp >>> 32));
        temp = Double.doubleToLongBits(imaginary);
        result = prime * result + (int) (temp ^ (temp >>> 32));

        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof ComplexNumber) {
            ComplexNumber complexNumber = (ComplexNumber) obj;
            return this.real == complexNumber.getReal() && this.imaginary == complexNumber.getImaginary();
        } else {
            return false;
        }
    }
}

class ComplexNumberCalculator {
    private ComplexNumberCalculator() { }

    public static ComplexNumber add(ComplexNumber number1, ComplexNumber number2) {
        return new ComplexNumber(number1.getReal() + number2.getReal(), number1.getImaginary() + number2.getImaginary());
    }

    public static ComplexNumber subtract(ComplexNumber number1, ComplexNumber number2) {
        return new ComplexNumber(number1.getReal() - number2.getReal(), number1.getImaginary() - number2.getImaginary());
    }

    public static ComplexNumber multiply(ComplexNumber number1, ComplexNumber number2) {
        double real = number1.getReal() * number2.getReal() - number1.getImaginary() * number2.getImaginary();
        double imaginary = number1.getReal() * number2.getImaginary() + number1.getImaginary() * number2.getReal();
        return new ComplexNumber(real, imaginary);
    }

    public static ComplexNumber divide(ComplexNumber number1, ComplexNumber number2) {
        ComplexNumber conjugate = ComplexNumberCalculator.conjugate(number2);
        ComplexNumber nominator = ComplexNumberCalculator.multiply(number1, conjugate);
        ComplexNumber denominator = ComplexNumberCalculator.multiply(number2, conjugate);
        return new ComplexNumber(nominator.getReal() / denominator.getReal(), nominator.getImaginary() / denominator.getReal());
    }

    public static ComplexNumber conjugate(ComplexNumber number) {
        return new ComplexNumber(number.getReal(), -number.getImaginary());
    }
}

enum ResultAmount {
    ONE,
    INFINITE,
    NONE
}

