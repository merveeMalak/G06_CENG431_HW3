package model;

import java.util.List;

public class Article extends Paper {
    private int volume;
    private String number;
    private String journal;

    public Article(List<String> authors, String title, int year, String doi, int volume, String number, String journal, int numOfDownloads) {
        super(authors, title, year, doi, numOfDownloads);
        this.volume = volume;
        this.number = number;
        this.journal = journal;
    }

    public String getVolume() {
        return String.format("%s", this.volume);
    }

    public String getNumber() {
        return this.number;
    }

    public String getJournal() {
        return this.journal;
    }

    @Override
    public String getInfo() {
        return getAuthorsString() + "\n" + getYear() + "," + getJournal() + "," + getVolume() + "," + getNumber() + "," + getDoi();
    }
}
