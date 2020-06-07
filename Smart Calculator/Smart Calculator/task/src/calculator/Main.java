package calculator;

import java.math.BigInteger;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Main {

    public static void main(String[] args) {
        Calculator calculator = Calculator.getCalculator();
        calculator.executeProgram();
    }
}

class Calculator {

    private static Calculator calculator;
    private Map<String, BigInteger> variables = new HashMap<>();

    private Calculator() {}

    public static Calculator getCalculator() {
        if (calculator == null) {
            calculator = new Calculator();
        }

        return calculator;
    }

    public void executeProgram() {
        Scanner scanner = new Scanner(System.in);
        boolean repeatLoop = true;
        while (scanner.hasNext() && repeatLoop) {
            String line = scanner.nextLine();
            switch (line) {
                case "/help" :
                    System.out.println("The program calculates the sum of numbers");
                    break;
                case "/exit" :
                    repeatLoop = false;
                    break;
                default:
                    Pattern pattern = Pattern.compile("/.*");
                    Matcher unknownCommandMatcher = pattern.matcher(line);
                    if (unknownCommandMatcher.matches()) {
                        System.out.println("Unknown command");
                    } else if (!line.isEmpty()) {
                        try {
                            line = editAndValidateOperators(line);
                            BigInteger result = calculateResult(line);

                            if (result != null) {
                                System.out.println(result);
                            }
                        } catch (NumberFormatException e) {
                            System.out.println("Invalid expression");
                        } catch (NoSuchElementException e) {
                            System.out.println("Unknown variable");
                        } catch (IllegalArgumentException e) {
                            System.out.println("Invalid expression");
                        } catch (UnsupportedOperationException e) {
                            System.out.println("Invalid assignment");
                        }
                    }
                    break;
            }
        }
        System.out.println("Bye!");
    }

    private BigInteger calculateResult(String input) throws UnsupportedOperationException, IllegalArgumentException {

        BigInteger result = null;
        String formattedLine = editAndValidateOperators(input);
        //System.out.println(formattedLine);
        if (formattedLine.contains("=")) {
            assignVariable(input);
        } else {
            List<String> infixList = convertInputToList(formattedLine);
            /*System.out.println("Infix:");
            infixList.forEach(System.out::println);*/
            List<String> postfixList = toPostfix(infixList);
            /*System.out.println("Postfix:");
            postfixList.forEach(System.out::println);*/
            result = calculateResultFromPostfix(postfixList);
            /*System.out.println("Result: " + result);*/
        }

        return result;
    }

    private String editAndValidateOperators(String input) throws IllegalArgumentException {
        Pattern separatorPattern = Pattern.compile("\\s+");
        Pattern plusPattern = Pattern.compile("[+]+");
        Pattern evenMinusPattern = Pattern.compile("(--)+");
        Pattern oddMinusPattern = Pattern.compile("-(--)+");

        Matcher separatorMatcher = separatorPattern.matcher(input);
        input = separatorMatcher.replaceAll("");

        Matcher plusMatcher = plusPattern.matcher(input);
        input = plusMatcher.replaceAll("+");

        Matcher oddMinusMatcher = oddMinusPattern.matcher(input);
        input = oddMinusMatcher.replaceAll("-");

        Matcher evenMinusMatcher = evenMinusPattern.matcher(input);
        input = evenMinusMatcher.replaceAll("+");

        if (input.matches("[*/]{2,}") || input.chars().filter(c -> c == '(').count() != input.chars().filter(c -> c == ')').count()) {
            throw new IllegalArgumentException("Invalid expression");
        }

        return input;
    }

    private void assignVariable(String input) throws UnsupportedOperationException {
        if (input.matches("[a-zA-Z]+=[a-zA-Z]+")) {
            String[] words = input.split("=");
            if (variables.containsKey(words[1])) {
                if (variables.containsKey(words[0])) {
                    variables.replace(words[0], variables.get(words[1]));
                } else {
                    variables.put(words[0], variables.get(words[1]));
                }
            } else {
                throw new IllegalArgumentException();
            }
        } else if (input.matches("[a-zA-Z]+=\\d+")) {
            String[] words = input.split("=");
            if (variables.containsKey(words[0].trim())) {
                variables.replace(words[0], new BigInteger(words[1]));
            } else {
                variables.put(words[0], new BigInteger(words[1]));
            }
        } else if (input.matches("\\w+=(\\d+|[a-zA-Z]+)")) {
            throw new IllegalArgumentException();
        } else {
            throw new UnsupportedOperationException();
        }
    }

    private List<String> convertInputToList(String input) {
        List<String> result = new ArrayList<>();

        String inputWithoutBrackets = input.replace("(", "").replace(")", "");
        List<String> operands = List.of(inputWithoutBrackets.split("[)]?[-+*/][(]?"));

        for (int i = 0, inputIndex = 0; i < operands.size() - 1; i++) {
            String firstOperand = operands.get(i);
            String secondOperand = operands.get(i + 1);

            int firstOperandStartingIndex = input.indexOf(firstOperand, inputIndex);
            int secondOperandStartingIndex = input.indexOf(secondOperand, inputIndex + firstOperand.length());
            int firstOperandEndingIndex = firstOperandStartingIndex + firstOperand.length();

            result.add(firstOperand);

            for (int j = firstOperandEndingIndex; j < secondOperandStartingIndex; j++) {
                result.add(String.valueOf(input.charAt(j)));
            }

            inputIndex = secondOperandStartingIndex;
        }

        String lastOperand = operands.get(operands.size() - 1);
        result.add(lastOperand);

        for (int j = input.lastIndexOf(lastOperand) + lastOperand.length(); j < input.length(); j++) {
            result.add(String.valueOf(input.charAt(j)));
        }

        return result;
    }

    private List<String> toPostfix(List<String> infixExpression) throws IllegalArgumentException {
        List<String> result = new ArrayList<>();
        Stack<String> stack = new Stack<>();

        for (String expression : infixExpression) {
            if (expression.matches("\\d+|[a-zA-Z]+")) {                                                              //1
                result.add(expression);
            } else {
                if (stack.isEmpty() || stack.peek().equals("(")) {
                    stack.push(expression);
                } else if (expression.matches("[*+/-]") && stack.peek().matches("\\d+|[a-zA-Z]+") ) {
                    stack.push(expression);
                } else if (expression.matches("[/*]") && stack.peek().matches("[+-]")) {
                    stack.push(expression);
                } else if (expression.matches("\\d+|[a-zA-Z]+|[+-]") && stack.peek().matches("[*/+-]")) {
                    while (true) {
                        String element = stack.peek();
                        if (element == null || element.matches("[(]")) {
                            stack.push(expression);
                            break;
                        } else if (element.matches("[*/+-]")) {
                            result.add(stack.pop());
                        }
                    }
                } else if (expression.matches("[(]")) {
                    stack.push(expression);
                } else if (expression.matches("[)]")) {
                    while (true) {
                        String element = stack.peek();
                        if (element == null) {
                            throw new IllegalArgumentException();
                        } else if (element.equals("(")) {
                            stack.pop();
                            break;
                        } else {
                            result.add(stack.pop());
                        }
                    }
                }
            }
        }

        while (stack.size() > 0) {
            String element = stack.pop();
            if (element.matches("[()]")) {
                throw new IllegalArgumentException();
            } else {
                result.add(element);
            }
        }

        return result;
    }

    private BigInteger calculateResultFromPostfix(List<String> infixExpression) throws IllegalArgumentException {

        if (infixExpression.size() == 1) {
            BigInteger value = variables.get(infixExpression.get(0));
            if (value == null) {
                throw new IllegalArgumentException();
            }

            return value;
        }

        Stack<String> stack = new Stack<>();

        for (String expression : infixExpression) {
            if (expression.matches("\\d+|[a-zA-Z]+")) {
                stack.push(expression);
            } else if (expression.matches("[-+*/]")) {
                String secondVariable = stack.pop();
                String firstVariable = stack.pop();
                if (firstVariable == null || secondVariable == null) {
                    throw new IllegalArgumentException();
                } else {
                    BigInteger firstValue;
                    BigInteger secondValue;

                    if (firstVariable.matches("[a-zA-Z]+")) {
                        firstValue = variables.get(firstVariable);
                        if (firstValue == null) {
                            throw new IllegalArgumentException("Unknown variable");
                        }
                    } else {
                        firstValue = new BigInteger(firstVariable);
                    }

                    if (secondVariable.matches("[a-zA-Z]+")) {
                        secondValue = variables.get(secondVariable);
                        if (secondValue == null) {
                            throw new IllegalArgumentException("Unknown variable");
                        }
                    } else {
                        secondValue = new BigInteger(secondVariable);
                    }

                    BigInteger result = BigInteger.ZERO;
                    switch (expression) {
                        case "-" :
                            result = firstValue.subtract(secondValue);
                            break;
                        case "+" :
                            result = firstValue.add(secondValue);
                            break;
                        case "*" :
                            result = firstValue.multiply(secondValue);
                            break;
                        case "/" :
                            try {
                                result = firstValue.divide(secondValue);
                            } catch (ArithmeticException e) {
                                throw new IllegalArgumentException();
                            }
                            break;
                    }
                    /*System.out.println("First value: " + firstValue);
                    System.out.println("Second value: " + secondValue);
                    System.out.println("Operation result: " + result);*/
                    stack.push(String.valueOf(result));
                }
            }
        }

        /*System.out.println("Calculated result in function: " + stack.peek());*/
        return new BigInteger(stack.pop());
    }
}

class Stack<T extends Object> {
    private final List<T> stack = new ArrayList<T>();

    public Stack(){

    }

    public T pop() {
        if (stack.size() > 0) {
            return stack.remove(stack.size() - 1);
        } else {
            return null;
        }
    }

    public T peek() {
        if (stack.size() > 0) {
            return stack.get(stack.size() - 1);
        } else {
            return null;
        }
    }

    public void push(T element) {
        stack.add(element);
    }

    public boolean isEmpty() {
        return stack.size() == 0;
    }

    public int size() {
        return stack.size();
    }

    public void printStack() {
        System.out.println("Stack:");
        stack.forEach(System.out::println);
    }
}