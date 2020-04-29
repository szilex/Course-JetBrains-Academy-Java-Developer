package flashcards;

import java.util.Scanner;
import java.util.List;
import java.util.ArrayList;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.util.LinkedHashMap;
import java.util.Collections;
import java.util.Iterator;
import java.io.*;

public class Main {

    private static Map<Card, Integer> cardsWithMistakes = new LinkedHashMap<>();
    private static Scanner scanner = new Scanner(System.in);
    private static List<String> logger = new ArrayList<>();
    private static String importFileName;
    private static String exportFileName;

    public static void main(String[] args) {

        if(args.length > 0) {
            if ()
        }

        final String mainMenuMessage = "Input the action (add, remove, import, export, ask, exit, log, hardest card, reset stats) :";
        String action = "add";

        while(!action.equals("exit")){
            printAndLogMessage(mainMenuMessage);
            action = scanner.nextLine();
            logMessage(action);
            switch(action) {
                case "add" :
                    getNewCardDataAndAdd();
                    break;
                case "remove" :
                    getOldCardDataAndRemove();
                    break;
                case "import" :
                    getFileNameAndImportCards();
                    break;
                case "export" :
                    getFileNameAndExportCards();
                    break;
                case "ask" :
                    int timesToAsk = getQuestionAmount();
                    askQuestions(timesToAsk);
                    break;
                case "exit" :
                    printAndLogMessage("Bye, bye!");
                    break;
                case "log" :
                    saveLog();
                    break;
                case "hardest card" :
                    printHardestCards();
                    break;
                case "reset stats":
                    resetStats();
                    break;
            }
        }
    }


    private static void printAndLogMessage(final String message) {
        System.out.println(message);
        logger.add(message);
    }

    private static void logMessage(final String message) {
        logger.add(message);
    }

    private static void getNewCardDataAndAdd() {
        try {
            Card card = getNewCardData();
            addCard(card);
        } catch (IllegalArgumentException e) {
            printAndLogMessage(e.getMessage());
        }
    }

    private static Card getNewCardData() throws IllegalArgumentException {
        printAndLogMessage("The card:");
        String term = scanner.nextLine();
        logMessage(term);

        for(Card card : cardsWithMistakes.keySet()) {
            if (card.getTerm().equals(term)){
                throw new IllegalArgumentException("The card \"" + term + "\" already exists.");
            }
        }

        System.out.println("The definition of the card:");
        logger.add("The definition of the card:");
        String definition = scanner.nextLine();
        logger.add(definition);
        /*if (cards.containsValue(definition))
            throw new IllegalArgumentException("The definition \"" + definition + "\" already exists. Try again:");*/

        for(Card card : cardsWithMistakes.keySet()) {
            if (card.getDefinition().equals(definition)){
                throw new IllegalArgumentException("The definition \"" + definition + "\" already exists. Try again:");
            }
        }

        return new Card(term, definition);
    }

    private static void addCard(Card card)  {
        cardsWithMistakes.put(new Card(card.getTerm(), card.getDefinition()), 0);
        printAndLogMessage("The pair (\"" + card.getTerm() + "\":\"" + card.getDefinition() + "\") has been added");
    }

    private static void getOldCardDataAndRemove() {
        try {
            Card card = getOldCardData();
            removeCard(card);
        } catch (IllegalArgumentException e) {
            printAndLogMessage(e.getMessage());
        }
    }

    private static Card getOldCardData() throws IllegalArgumentException {
        printAndLogMessage("The card:");
        String term = scanner.nextLine();
        logMessage(term);

        for(Card card : cardsWithMistakes.keySet()) {
            if(card.getTerm().equals(term)) {
                return card;
            }
        }
        throw new IllegalArgumentException("Can't remove \"" + term + "\": there is no such card.");
    }

    private static void removeCard(Card card) {
        Iterator<Entry<Card, Integer>> iter = cardsWithMistakes.entrySet().iterator();
        while (iter.hasNext()) {
            Entry<Card, Integer> entryToRemove = iter.next();
            if (entryToRemove.getKey().getTerm().equals(card.getTerm())) {
                iter.remove();
            }
        }
        printAndLogMessage("The card has been removed.");
    }

    private static void getFileNameAndImportCards() {
        String fileNameImport = getFileName();
        int counterImport = importCards(fileNameImport);
        if(counterImport == 0){
            printAndLogMessage("File \"" + fileNameImport + "\" not found");
        } else{
            printAndLogMessage(counterImport + " cards have been loaded.");
        }
    }

    private static int importCards(String fileName) {
        int counter = 0;
        try(Scanner fileScanner = new Scanner(new File(fileName))) {

            while(fileScanner.hasNext()) {
                String term = fileScanner.nextLine();
                String definition = fileScanner.nextLine();
                int errors = Integer.parseInt(fileScanner.nextLine());
                cardsWithMistakes.put(new Card(term, definition), errors);
                counter++;
            }
        } catch (FileNotFoundException e) {

        }
        return counter;
    }

    private static void getFileNameAndExportCards() {
        String fileName = getFileName();
        int counterExport = exportCards(fileName);
        printAndLogMessage(counterExport + " cards have been saved.");
    }

    private static String getFileName() {
        printAndLogMessage("File name:");
        String fileName = scanner.nextLine();
        logMessage(fileName);
        return fileName;
    }

    private static int exportCards(String fileName) {
        int counter = 0;
        try(PrintWriter printWriter = new PrintWriter(new File(fileName))) {
            /*for(String term : cards.keySet()) {
                printWriter.println(term);
                printWriter.println(cards.get(term));
                counter++;
            }*/

            for(Entry<Card, Integer> entry : cardsWithMistakes.entrySet()) {
                printWriter.println(entry.getKey().getTerm());
                printWriter.println(entry.getKey().getDefinition());
                printWriter.println(entry.getValue());
                counter++;
            }
        } catch (FileNotFoundException e) {

        }
        return counter;
    }

    private static int getQuestionAmount() {
        printAndLogMessage("How many times to ask?");
        int amount = Integer.parseInt(scanner.nextLine());
        logMessage(Integer.toString(amount));
        return amount;
    }

    private static void askQuestions(int amount) {
        while(amount>0) {
            for(Entry<Card, Integer> entry : cardsWithMistakes.entrySet()) {
                printAndLogMessage("Print the definition of \"" + entry.getKey().getTerm() + "\":");
                String answer = scanner.nextLine();
                logMessage(answer);
                if (answer.equals(entry.getKey().getDefinition())) {
                    printAndLogMessage("Correct answer.");
                } else {
                    boolean definitionFound = false;
                    for (Entry<Card, Integer> wrongEntry : cardsWithMistakes.entrySet()) {
                        if (!wrongEntry.getKey().getTerm().equals(entry.getKey().getTerm()) && wrongEntry.getKey().getDefinition().equals(entry.getKey().getDefinition())) {
                            printAndLogMessage("Wrong answer. (The correct one is \"" + entry.getKey().getDefinition() + "\", you've just written the definition of \"" + wrongEntry.getKey().getTerm() + "\" card.)");
                            definitionFound = true;
                        }
                    }

                    if (!definitionFound) {
                        printAndLogMessage("Wrong answer. The correct one is \"" + entry.getKey().getDefinition() + "\"");
                    }

                    int currentMistakes = entry.getValue();
                    entry.setValue(currentMistakes+1);
                }
                if (--amount == 0)
                    break;
            }
        }
    }

    private static void saveLog() {
        String fileName = getFileName();
        try(PrintWriter printWriter = new PrintWriter(new File(fileName))) {
            for(String line : logger) {
                printWriter.println(line);
            }
            printAndLogMessage("The log has been saved;");
        } catch (FileNotFoundException e) {

        }
    }

    private static void printHardestCards() {
        if (cardsWithMistakes.size() != 0) {
            int maximum = Collections.max(cardsWithMistakes.values());
            List<String> hardestTerms = new ArrayList<>();
            if (maximum != 0) {
                for(Entry<Card, Integer> entry : cardsWithMistakes.entrySet()) {
                    if (entry.getValue() == maximum) {
                        hardestTerms.add("\"" + entry.getKey().getTerm() + "\"");
                    }
                }
                if(hardestTerms.size() == 1){
                    printAndLogMessage("The hardest card is " + hardestTerms.get(0) + ". You have " + maximum + " errors answering them.");
                } else {
                    String names = String.join(", ", hardestTerms);
                    printAndLogMessage("The hardest cards are " + names + ". You have " + maximum + " errors answering them.");
                }

            } else {
                printAndLogMessage("There are no cards with errors.");
            }
        } else {
            printAndLogMessage("There are no cards with errors.");
        }



    }

    private static void resetStats() {
        for(Entry<Card, Integer> entry : cardsWithMistakes.entrySet()) {
            entry.setValue(0);
        }
        printAndLogMessage("Card statistics has been reset.");
    }
}

class Card{
    private String term;
    private String definition;

    public Card(String term, String definition){
        this.term = term;
        this.definition = definition;
    }

    public String getTerm(){
        return term;
    }

    public String getDefinition(){
        return definition;
    }
}