package search;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public class Main {
    enum Strategy {
        ANY,
        ALL,
        NONE
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        List<List<String>> words = new ArrayList<>();

        /*System.out.println("Enter the number of people:");
        int amountOfPeople = Integer.parseInt(scanner.nextLine());

        System.out.println("Enter all the people");
        for (int i = 0; i < amountOfPeople; i++) {
            words.add(Arrays.asList(scanner.nextLine().split("\\s+")));
        }*/

        int index = Arrays.asList(args).indexOf("--data");
        if (index < 0) System.exit(1);

        String fileName = args[index + 1];
        String absoluteFilePath = FileOperations.getAbsolutePath(fileName);
        words = FileOperations.getDataFromFile(absoluteFilePath);

        int userChoice = 1;
        while (userChoice != 0) {
            printMainMenu();
            userChoice = Integer.parseInt(scanner.nextLine());
            switch (userChoice) {
                case 1 :
                    System.out.println("Select a matching strategy: ALL, ANY, NONE");
                    Strategy strategy;
                    switch (scanner.nextLine()) {
                        case "ANY" :
                            strategy = Strategy.ANY;
                            break;
                        case "ALL" :
                            strategy = Strategy.ALL;
                            break;
                        case "NONE" :
                            strategy = Strategy.NONE;
                            break;
                        default:
                            strategy = Strategy.ANY;
                            break;
                    }
                    System.out.println("Enter a name or email to search all suitable people:");
                    List<String> searchedWords = Arrays.asList(scanner.nextLine().split(" "));
                    List<Integer> indexes = findIndexes(words, searchedWords, strategy);
                    if (indexes.size() > 0) {
                        System.out.printf("%d persons found:\n", indexes.size());
                        printList(words, indexes);
                    } else {
                        System.out.println("No matching people found.");
                    }
                    break;
                case 2 :
                    printList(words);
                    break;
                case 0 :
                    System.out.println("Bye!");
                    break;
                default:
                    System.out.println("Incorrect option! Try again.");
            }
        }
    }

    public static final void printMainMenu() {
        System.out.println("=== Menu ===");
        System.out.println("1. Find a person");
        System.out.println("2. Print all people");
        System.out.println("0. Exit");
    }

    public static List<Integer> findIndexes(List<List<String>> data, List<String> words, Strategy strategy) {
        List<String> lowerCaseWords = words.stream().map(x -> x.toLowerCase()).collect(Collectors.toList());
        List<Integer> indexes = new ArrayList<>();

        for (int i = 0; i < data.size(); i++) {
            List<String> singleDataList = data.get(i);
            switch (strategy) {
                case ANY :
                    for (int j = 0; j < singleDataList.size(); j++) {
                        for (int k = 0; k < lowerCaseWords.size(); k++)
                        if (singleDataList.get(j).toLowerCase().equals(lowerCaseWords.get(k))) {
                            indexes.add(i);
                            break;
                        }
                    }
                    break;
                case ALL :
                    boolean correct = true;
                    for (int j = 0; j < lowerCaseWords.size() && correct; j++) {
                        boolean found = false;
                        for (int k = 0; k < singleDataList.size() && !found; k++)
                            if (singleDataList.get(j).toLowerCase().equals(lowerCaseWords.get(k))) {
                                found = true;
                            }
                        if (!found) {
                            correct = false;
                        }
                    }
                    if (correct) {
                        indexes.add(i);
                    }
                    break;
                case NONE:
                    boolean found = false;
                    for (int j = 0; j < singleDataList.size(); j++) {
                        for (int k = 0; k < lowerCaseWords.size(); k++)
                            if (singleDataList.get(j).toLowerCase().equals(lowerCaseWords.get(k))) {
                                found = true;
                                break;
                            }
                    }
                    if (!found) {
                        indexes.add(i);
                    }
                    break;

            }

        }
        return indexes;
    }

    public static void printList(List<List<String>> words) {
        for (List<String> list : words) {
            System.out.println(getListString(list.toString()));
        }
    }

    public static void printList(List<List<String>> words, List<Integer> indexes) {
        for (Integer index : indexes) {
            System.out.println(getListString(words.get(index).toString()));
        }
    }

    public static String getListString(String line) {
        return line.substring(1, line.length() - 1).replace(", ", " ");
    }
}

class FileOperations {
    private FileOperations() {}

    public static String getAbsolutePath(String fileName){
        Path path = Paths.get(fileName);
        Path absolutePath = path.toAbsolutePath().normalize();
        return absolutePath.toString();
    }

    public static List<List<String>> getDataFromFile(String absolutePath) {
        List<List<String>> data = new ArrayList<>();
        try (Scanner scanner = new Scanner(new File(absolutePath))) {
            while (scanner.hasNext()) {
                data.add(Arrays.asList(scanner.nextLine().split(" ")));
            }
        } catch (IOException e) {

        }
        return data;
    }

    public static void saveDataToFile(String absolutePath, List<List<String>> data) {
        try (PrintWriter printWriter = new PrintWriter(new File(absolutePath))) {
            for (var singleRow : data) {
                printWriter.println(singleRow.stream().collect(Collectors.joining(" ")));
            }
        } catch (IOException e) {

        }
    }
}