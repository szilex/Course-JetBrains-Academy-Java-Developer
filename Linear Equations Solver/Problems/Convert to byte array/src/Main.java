// The method is OK, no need to change it
public void writeWords(String[] words) throws IOException {
    LetterPrinter printer = new LetterPrinter();

    char[] letters = convert(words);
    for (char letter : letters) {
        printer.write(letter);
    }
}

private char[] convert(String[] words) throws IOException {
    try (CharArrayWriter writer = new CharArrayWriter()) {
        for (String word : words) {
            writer.write(word);
        }
        return writer.toCharArray();
    } catch (Exception e) {
        return null;
    }
}