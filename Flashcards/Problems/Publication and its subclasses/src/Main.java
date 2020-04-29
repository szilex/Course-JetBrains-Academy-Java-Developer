class Publication {

    private String title;

    public Publication(String title) {
        this.title = title;
    }

    public final String getInfo() {
        return getType() + getDetails() + ": " + title;
    }

    public String getType() {
        return "Publication";
    }

    public String getDetails() {
        return "";
    }

}

class Newspaper extends Publication {

    private String source;

    public Newspaper(String title, String source) {
        super(title);
        this.source = source;
    }

    @java.lang.Override
    public String getType() {
        return "Newspaper";
    }

    @java.lang.Override
    public String getDetails() {
        return " (source - " + source + ")";
    }
}

class Article extends Publication {

    private String author;

    public Article(String title, String author) {
        super(title);
        this.author = author;
    }

    @java.lang.Override
    public String getType() {
        return "Article";
    }

    @java.lang.Override
    public String getDetails() {
        return " (author - " + author + ")";
    }
}

class Announcement extends Publication {

    private int daysToExpire;

    public Announcement(String title, int daysToExpire) {
        super(title);
        this.daysToExpire = daysToExpire;
    }

    @java.lang.Override
    public String getType() {
        return "Announcement";
    }

    @java.lang.Override
    public String getDetails() {
        return " (days to expire - " + daysToExpire + ")";
    }

    // write your code here

}