package model;


import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.ArrayList;
import view.ICustomObserver;
import view.ICustomSubject;
import java.util.List;

public class ReadingList implements ICustomSubject {

    @JsonProperty("readinglist_id")
    private Number id;
    @JsonProperty("creator_researcher_name")
    private String creatorResearcherName;
    @JsonProperty("reading_list_name")
    private String readingListName;
    @JsonProperty("name_of_papers")
    private List<String> nameOfPapers;
    @JsonProperty("number_of_papers")
    private Number numberOfPapers;

    private List<Paper> papers;

    public ReadingList() {
        this.papers = new ArrayList<>();
    }

    public ReadingList(Number id, String creatorResearcherName, String readingListName, Number numberOfPapers, List<String> nameOfPapers) {
        this.id = id;
        this.creatorResearcherName = creatorResearcherName;
        this.readingListName = readingListName;
        this.numberOfPapers = numberOfPapers;
        this.nameOfPapers = new ArrayList<>(nameOfPapers);
    }

    public ReadingList(Number id, String creatorResearcherName, String readingListName, List<Paper> papers) {
        this.id = id;
        this.creatorResearcherName = creatorResearcherName;
        this.readingListName = readingListName;
        this.papers = papers;
    }

    public Number getId() {
        return this.id;
    }

    public String getCreatorResearcherName() {
        return this.creatorResearcherName;
    }

    public String getReadingListName() {
        return this.readingListName;
    }

    public Number getNumberOfPapers() {
        return this.papers.size();
    }

    public List<String> getNameOfPapers() {
        return this.papers.stream().map(Paper::getTitle).toList();
    }

    @Override
    public void attach(ICustomObserver observer) {

    }

    @Override
    public void detach(ICustomObserver observer) {

    }

    @Override
    public void notifyObservers() {

    }
}
