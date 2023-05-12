package model;

import java.util.List;

public abstract class Paper {
    protected List<String> authors;
    protected String title;
    protected int year;
    protected String doi;


    public Paper(List<String> authors, String title, int year, String doi){
        this.authors = authors;
        this.title = title;
        this.year = year;
        this.doi = doi;
    }

    public List<String> getAuthors() {
        return authors;
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
}
