package model;

import java.util.List;

public class ConferencePaper extends Paper {
    private String bookTitle;


    public ConferencePaper(List<String> authors, String title, int year, String doi, String bookTitle, int numOfDownloads) {
        super(authors, title, year, doi, numOfDownloads);
        this.bookTitle = bookTitle;
    }

    public String getBookTitle() {
        return this.bookTitle;
    }
}
