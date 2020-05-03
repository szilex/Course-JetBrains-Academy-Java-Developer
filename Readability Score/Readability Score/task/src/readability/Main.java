package readability;

import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;

public class Main {
    public static void main(String[] args) {

        Path path = Paths.get(args[0]);
        Path absolutePath = path.toAbsolutePath().normalize();
        try (Scanner scanner = new Scanner(new File(absolutePath.toString()))) {

            int characterAmount = 0;
            int sentenceAmount = 0;
            int wordAmount = 0;
            int syllables = 0;
            int polysyllables = 0;

            System.out.println("The text is: ");

            while (scanner.hasNext()) {
                String text = scanner.nextLine();
                System.out.println(text);
                characterAmount = text.length();

                for (int i = 0; i < text.length(); i++) {
                    if (text.substring(i, i + 1).matches("[\\s]")) {
                        characterAmount--;
                    }
                }

                String[] sentences = text.split("[.!?] ");
                sentenceAmount += sentences.length;

                for (String sentence : sentences) {
                    String[] words = sentence.split(" ");
                    if (words.length > 0) {
                        wordAmount += words.length;
                        for (String word : words) {
                            int syllablesInWord = countSyllables(word);
                            syllables += syllablesInWord;
                            if (syllablesInWord > 2) {
                                polysyllables++;
                            }
                        }
                    } else {
                        sentenceAmount--;
                    }
                }
            }

            Map<Integer, Integer> scores = new HashMap<>();
            scores.put(1, 6);
            scores.put(2, 7);
            scores.put(3, 9);
            scores.put(4, 10);
            scores.put(5, 11);
            scores.put(6, 12);
            scores.put(7, 13);
            scores.put(8, 14);
            scores.put(9, 15);
            scores.put(10, 16);
            scores.put(11, 17);
            scores.put(12, 18);
            scores.put(13, 24);
            scores.put(14, 24);

            System.out.println();
            System.out.printf("Words: %d\n", wordAmount);
            System.out.printf("Sentences: %d\n", sentenceAmount);
            System.out.printf("Characters: %d\n", characterAmount);
            System.out.printf("Syllables: %d\n", syllables);
            System.out.printf("Polysyllables: %d\n", polysyllables);

            System.out.print("Enter the score you want to calculate (ARI, FK, SMOG, CL, all):");
            String option = new Scanner(System.in).next();
            System.out.println();

            double average = 0;
            switch (option) {
                case "ARI":
                    double score = scoreAutomated(characterAmount, wordAmount, sentenceAmount);
                    average = scores.get((int)Math.round(score));
                    System.out.printf("Automated Readability Index: %f (about %d year olds).\n", score, average);
                    break;
                case "FK":
                    score = scoreFlesch(syllables, wordAmount, sentenceAmount);
                    average = scores.get((int)Math.round(score));
                    System.out.printf("Flesch–Kincaid readability tests: %f (about %d year olds).\n", score, average);
                    break;
                case "SMOG":
                    score = scoreSmog(polysyllables, sentenceAmount);
                    average = scores.get((int)Math.round(score));
                    System.out.printf("Simple Measure of Gobbledygook: %f (about %d year olds).\n", score, average);
                    break;
                case "CL":
                    score = scoreColeman(characterAmount, wordAmount, sentenceAmount);
                    average = scores.get((int)Math.round(score));
                    System.out.printf("Coleman–Liau index: %f (about %d year olds).\n", score, average);
                    break;
                case "all":
                    double ari = scoreAutomated(characterAmount, wordAmount, sentenceAmount);
                    System.out.printf("Automated Readability Index: %f (about %d year olds).\n", ari, scores.get((int)Math.round(ari)));
                    double fk = scoreFlesch(syllables, wordAmount, sentenceAmount);
                    System.out.printf("Flesch–Kincaid readability tests: %f (about %d year olds).\n", fk, scores.get((int)Math.round(fk)));
                    double smog = scoreSmog(polysyllables, sentenceAmount);
                    System.out.printf("Simple Measure of Gobbledygook: %f (about %d year olds).\n", smog, scores.get((int)Math.round(smog)));
                    double cl = scoreColeman(characterAmount, wordAmount, sentenceAmount);
                    System.out.printf("Coleman–Liau index: %f (about %d year olds).\n", cl, scores.get((int)Math.round(cl)));
                    average = (scores.get((int)Math.round(ari)) + scores.get((int)Math.round(fk)) + scores.get((int)Math.round(smog)) + scores.get((int)Math.round(cl))) / 4;
                    System.out.println();

            }

            System.out.printf("This text should be understood in average by %f year olds.\n", average);

        } catch (IOException e) {

        }
    }

    private static int countSyllables(String word) {
        Set<Character> vowels= new HashSet<>(Arrays.asList('a', 'e', 'i', 'o', 'u', 'y'));
        int syllables = 0;
        boolean previousVowel = false;
        for (int i = 0; i < word.length(); i++) {
            if (vowels.contains(word.charAt(i))) {
                if(!(word.charAt(i) == 'e' && i == word.length() - 1)) {
                    if (!previousVowel) {
                        syllables++;
                    }
                    previousVowel = true;
                } else {
                    if (syllables == 0) {
                        syllables = 1;
                    }
                }
            } else {
                previousVowel = false;
            }
        }
        return syllables;
    }

    private static double scoreAutomated(int characters, int words, int sentences) {
        return 4.71 * (double) characters / words + 0.5 * (double) words / sentences - 21.43;
    }

    private static double scoreFlesch(int syllables, int words, int sentences) {
        return 0.39 * (double) words / sentences + 11.8 * (double) syllables / words - 15.59;
    }

    private static double scoreSmog(int polysyllables, int sentences) {
        return 1.043 * Math.sqrt((double) polysyllables * 30.0 / sentences) + 3.1291;
    }

    private static double scoreColeman(int characters, int words, int sentences) {
        return 0.0588 * (double) characters / words * 100 - 0.296 * (double) sentences / words * 100 - 15.8;
    }

}
