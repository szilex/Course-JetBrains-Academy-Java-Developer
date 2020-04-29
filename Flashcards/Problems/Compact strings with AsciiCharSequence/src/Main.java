import java.util.Arrays;

class AsciiCharSequence implements java.lang.CharSequence {

    byte[] characters;

    public AsciiCharSequence(byte[] characters) {
        this.characters = characters;
    }

    @Override
    public char charAt(int index) {
        return (char)characters[index];
    }

    @Override
    public int length() {
        return characters.length;
    }

    @Override
    public AsciiCharSequence subSequence(int start, int end) {
        return new AsciiCharSequence(Arrays.copyOfRange(characters, start, end));
    }

    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        for(byte character : characters){
            stringBuilder.append((char)character);
        }
        return stringBuilder.toString();
    }
}