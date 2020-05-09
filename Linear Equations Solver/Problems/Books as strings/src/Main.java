class Book {

    private String title;
    private int yearOfPublishing;
    private String[] authors;

    public Book(String title, int yearOfPublishing, String[] authors) {
        this.title = title;
        this.yearOfPublishing = yearOfPublishing;
        this.authors = authors;
    }

    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("[");
        for (String author : authors) {
            stringBuilder.append(author);
            stringBuilder.append(",");
        }
        stringBuilder.replace(stringBuilder.length() - 1, stringBuilder.length(), "]");

        return String.format("title=%s,yearOfPublishing=%d,authors=%s", title, yearOfPublishing, stringBuilder.toString());
    }
}