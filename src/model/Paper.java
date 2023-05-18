package model;

import java.util.List;

public abstract class Paper {
    protected List<String> authors;
    protected String title;
    protected int year;
    protected String doi;
    protected int numOfDownloads;


    public Paper(List<String> authors, String title, int year, String doi,int numOfDownloads) {
        this.authors = authors;
        this.title = title;
        this.year = year;
        this.doi = doi;
        this.numOfDownloads = numOfDownloads;
    }

    public List<String> getAuthors() {
        return authors;
    }

    public String getAuthorsString() {
        return String.join(",", this.authors);
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

    public void increaseOneNumOfDownloads() {
        this.numOfDownloads += 1;
    }
}
