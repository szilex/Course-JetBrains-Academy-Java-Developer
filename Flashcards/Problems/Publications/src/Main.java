class Publication {

    private String title;

    public Publication(String title) {
        this.title = title;
    }

    public String getDetails() {
        return "title=\"" + title + "\"";
    }

}

class Newspaper extends Publication {

    private String source;

    public Newspaper(String title, String source) {
        super(title);
        this.source = source;
    }

    @java.lang.Override
    public String getDetails() {
        return new StringBuilder(super.getDetails()).append(", source=\"").append(this.source).append("\"").toString();
    }
}

class Article extends Publication {

    private String author;

    public Article(String title, String author) {
        super(title);
        this.author = author;
    }

    @java.lang.Override
    public String getDetails() {
        return new StringBuilder(super.getDetails()).append(", author=\"").append(this.author).append("\"").toString();
    }

}

class Announcement extends Publication {

    private int daysToExpire;

    public Announcement(String title, int daysToExpire) {
        super(title);
        this.daysToExpire = daysToExpire;
    }

    @java.lang.Override
    public String getDetails() {
        return new StringBuilder(super.getDetails()).append(", daysToExpire=").append(this.daysToExpire).toString();
    }

}