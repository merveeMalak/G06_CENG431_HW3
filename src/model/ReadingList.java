package model;


import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.ArrayList;

import view.ICustomObserver;
import view.ICustomSubject;

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

    private List<Paper> papers;

    public ReadingList() {
        this.papers = new ArrayList<>();
    }

    public ReadingList(int id, String creatorResearcherName, String readingListName, int numberOfPapers, List<String> nameOfPapers) {
        this.id = id;
        this.creatorResearcherName = creatorResearcherName;
        this.readingListName = readingListName;
        this.numberOfPapers = numberOfPapers;
        this.nameOfPapers = new ArrayList<>(nameOfPapers);
    }

    public ReadingList(int id, String creatorResearcherName, String readingListName, List<Paper> papers) {
        this.id = id;
        this.creatorResearcherName = creatorResearcherName;
        this.readingListName = readingListName;
        this.papers = papers;
    }

    public ReadingList(int id, String creatorResearcherName, String readingListName) {
        this.id = id;
        this.creatorResearcherName = creatorResearcherName;
        this.readingListName = readingListName;
        this.papers = new ArrayList<>();
        this.numberOfPapers = 0;
        this.nameOfPapers = new ArrayList<>();
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
        return this.papers.size();
    }

    public List<String> getNameOfPapers() {
        return this.papers.stream().map(Paper::getTitle).toList();
    }

    public void addPaper(Paper paper) {
        this.numberOfPapers += 1;
        this.nameOfPapers.add(paper.getTitle());
    }

    @Override
    public String toString() {
        return this.readingListName;
    }
}
