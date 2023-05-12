package model;

import java.util.List;

public class ReadingList {

    private int id;
    private String creatorResearcherName;
    private String readingListName;
    private int numberOfPapers;
    private List<String> nameOfPapers;

    public ReadingList(){}

    public int getId() {
        return this.id;
    }

    public String getCreatorResearcherName() {
        return this.creatorResearcherName;
    }

    public String getReadingListName() {
        return this.readingListName;
    }

    public int getNumberOfPapers() {
        return this.numberOfPapers;
    }

    public List<String> getNameOfPapers() {
        return this.nameOfPapers;
    }
}
