package encryptdecrypt;

import java.io.*;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        Encryption encryption;
        String operation = "enc";
        String algorithm = "shift";
        String message = "";
        String resultMessage = "";
        String inFileName = "";
        String outFileName = "";

        int shift = 0;

        int index = 0;
        while (index < args.length) {
            switch (args[index]) {
                case "-mode" :
                    operation = args[index + 1];
                    break;
                case "-key" :
                    shift = Integer.parseInt(args[index + 1]);
                    break;
                case "-data" :
                    message = args[index + 1];
                    break;
                case "-out" :
                    outFileName = args[index + 1];
                    break;
                case "-in" :
                    inFileName = args[index + 1];
                    break;
                case "-alg" :
                    algorithm = args[index + 1];
                    break;
            }
            index += 2;
        }

        if (message.isEmpty() && !inFileName.isEmpty()) {
            message = getMessageFromFile(inFileName);
        }

        switch (algorithm) {
            case "unicode" :
                encryption = EncryptionFactory.getEncryptionAlgorithm("unicode", shift);
                break;
            case "shift" :
            default:
                encryption = EncryptionFactory.getEncryptionAlgorithm("shift", shift);
                break;
        }

        switch (operation) {
            case "enc" :
                resultMessage = encryption.encrypt(message);
                break;
            case "dec" :
                resultMessage = encryption.decrypt(message);
                break;
        }



        if (outFileName.isEmpty()) {
            System.out.println(resultMessage);
        } else {
            saveMessageToFile(outFileName, resultMessage);
        }

    }

/*    private static String encrypt(String message, int shift) {
        StringBuilder stringBuilder = new StringBuilder();
        for (int i = 0; i < message.length(); i++) {
            char encryptedCharacter = (char) (message.charAt(i) + shift);
            stringBuilder.append(encryptedCharacter);
        }
        return stringBuilder.toString();
    }

    private static String decrypt(String message, int shift) {
        StringBuilder stringBuilder = new StringBuilder();
        for (int i = 0; i < message.length(); i++) {
            char encryptedCharacter = (char) (message.charAt(i) - shift);
            stringBuilder.append(encryptedCharacter);
        }
        return stringBuilder.toString();
    }*/

    private static String getMessageFromFile(String fileName) {
        Path path = Paths.get(fileName);
        Path absolutePath = path.toAbsolutePath().normalize();
        String absoluteFileName = absolutePath.toString();
        StringBuilder stringBuilder = new StringBuilder();

        try (Scanner scanner = new Scanner(new File(absoluteFileName))) {
            while (scanner.hasNext()) {
                stringBuilder.append(scanner.nextLine());
            }
            return stringBuilder.toString();
        } catch (FileNotFoundException e) {
            return "";
        }
    }

    private static void saveMessageToFile(String fileName, String message) {
        Path path = Paths.get(fileName);
        Path absolutePath = path.toAbsolutePath().normalize();
        String absoluteFileName = absolutePath.toString();

        try (FileWriter fileWriter = new FileWriter(new File(absoluteFileName))) {
            fileWriter.write(message);
        } catch (IOException e) {

        }
    }
}

abstract class Encryption {

    public abstract String encrypt(String message);

    abstract public String decrypt(String message);
}

class ShiftEncryption extends Encryption {

    private int shift;

    public ShiftEncryption(int shift) {
        this.shift = shift;
    }

    @Override
    public String encrypt(String message) {
        StringBuilder stringBuilder = new StringBuilder();
        for (int i = 0; i < message.length(); i++) {
            if (Character.isLetter(message.charAt(i))) {
                char encryptedCharacter = (char) (message.charAt(i) + shift);
                if (Character.isUpperCase(message.charAt(i))) {
                    if (encryptedCharacter > 'Z') {
                        encryptedCharacter -= 'Z' - 'A' + 1;
                    }
                } else {
                    if (encryptedCharacter > 'z') {
                        encryptedCharacter -= 'z' - 'a' + 1;
                    }
                }
                stringBuilder.append(encryptedCharacter);
            } else {
                stringBuilder.append(message.charAt(i));
            }
        }
        return stringBuilder.toString();
    }

    @Override
    public String decrypt(String message) {
        StringBuilder stringBuilder = new StringBuilder();
        for (int i = 0; i < message.length(); i++) {
            if (Character.isLetter(message.charAt(i))) {
                char encryptedCharacter = (char) (message.charAt(i) - shift);
                if (Character.isUpperCase(message.charAt(i))) {
                    if (encryptedCharacter < 'A') {
                        encryptedCharacter += 'Z' - 'A' + 1;
                    }
                } else {
                    if (encryptedCharacter < 'a') {
                        encryptedCharacter += 'z' - 'a' + 1;
                    }
                }
                stringBuilder.append(encryptedCharacter);
            } else {
                stringBuilder.append(message.charAt(i));
            }
        }
        return stringBuilder.toString();
    }
}

class UnicodeEncryption extends Encryption {

    private int shift;

    public UnicodeEncryption(int shift) {
        this.shift = shift;
    }

    @Override
    public String encrypt(String message) {
        StringBuilder stringBuilder = new StringBuilder();
        for (int i = 0; i < message.length(); i++) {
            char encryptedCharacter = (char) (message.charAt(i) + shift);
            stringBuilder.append(encryptedCharacter);
        }
        return stringBuilder.toString();
    }

    @Override
    public String decrypt(String message) {
        StringBuilder stringBuilder = new StringBuilder();
        for (int i = 0; i < message.length(); i++) {
            char encryptedCharacter = (char) (message.charAt(i) - shift);
            stringBuilder.append(encryptedCharacter);
        }
        return stringBuilder.toString();
    }
}

class EncryptionFactory {

    public static Encryption getEncryptionAlgorithm(String type, int shift) {
        switch(type) {
            case "shift" :
                return new ShiftEncryption(shift);
            case "unicode":
                return new UnicodeEncryption(shift);
            default:
                return null;
        }
    }
}
