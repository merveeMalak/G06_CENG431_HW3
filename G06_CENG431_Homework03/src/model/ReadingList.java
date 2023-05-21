package model;


import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.ArrayList;
import java.util.List;

public class ReadingList {

    @JsonProperty("readinglist_id")
    private int id;
    @JsonProperty("creator_researcher_name")
    private String creatorResearcherName;
    @JsonProperty("reading_list_name")
    private String readingListName;
    @JsonProperty("name_of_papers")
    private List<String> nameOfPapers;
    @JsonProperty("number_of_papers")
    private int numberOfPapers;

    public ReadingList() {}

    public ReadingList(int id, String creatorResearcherName, String readingListName) {
        this.id = id;
        this.creatorResearcherName = creatorResearcherName;
        this.readingListName = readingListName;
        this.numberOfPapers = 0;
        this.nameOfPapers = new ArrayList<String>();
    }

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
        return numberOfPapers;
    }

    public List<String> getNameOfPapers() {
        return this.nameOfPapers;
    }

    public boolean addPaper(Paper paper) {
        if (!this.nameOfPapers.contains(paper.getTitle())) {
            numberOfPapers += 1;
            nameOfPapers.add(paper.getTitle());
            return true;
        } else {
            return false;
        }
    }

    public boolean removePaper(String paperName) {
        if (this.nameOfPapers.contains(paperName)) {
            this.nameOfPapers.remove(paperName);
            this.numberOfPapers -= 1;
            return true;
        } else {
            return false;
        }
    }

    @Override
    public String toString() {
        return this.readingListName;
    }
}
