package model;

import java.util.List;
import java.util.Random;

public abstract class Paper {
    private final int MAX_NUM_OF_DOWNLOADS = 1500;
    protected List<String> authors;
    protected String title;
    protected int year;
    protected String doi;
    protected int numOfDownloads;


    public Paper(List<String> authors, String title, int year, String doi) {
        this.authors = authors;
        this.title = title;
        this.year = year;
        this.doi = doi;
        Random random = new Random();
        this.numOfDownloads = random.nextInt(MAX_NUM_OF_DOWNLOADS + 1);

    }

    public List<String> getAuthors() {
        return authors;
    }

    public String getAuthorsString() {
        return String.join(";", this.authors);
    }

    public String getTitle() {
        return title;
    }

    public int getYear() {
        return year;
    }

    public String getDoi() {
        return doi;
    }

    public String getNumOfDownloads() {
        return String.format("%s", numOfDownloads);
    }
}
