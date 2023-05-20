package fileManagement;

import fileIO.JsonFileIO;
import model.ReadingList;
import model.Researcher;
import view.ICustomObserver;
import view.ICustomSubject;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class JsonFileManagement implements ICustomObserver {
    private JsonFileIO jsonFileIO;
    private List<ReadingList> readingLists;
    private int currentId;


    public JsonFileManagement() {
        this.jsonFileIO = new JsonFileIO();
        this.readingLists = this.jsonFileIO.readFile();
        this.currentId = readingLists.size();
    }

    public void addReadingList(String creatorName, String readingListName) {
        ReadingList readingList = new ReadingList(currentId, creatorName, readingListName);
        this.jsonFileIO.readFile();
        this.currentId += 1;
        this.readingLists.add(readingList);
    }

    public void writeReadingList() {
        if (this.readingLists.size() != 0) {
            this.jsonFileIO.writeFileIO(this.readingLists);
        }
    }

    public void setReadingList(ReadingList changedReadingList) {
        List<ReadingList> newReadingList = new ArrayList<>();
        for (ReadingList readingList : this.readingLists) {
            if (changedReadingList.getId() == readingList.getId()){
                newReadingList.add(changedReadingList);
            } else {
                newReadingList.add(readingList);
            }
        }
        this.readingLists = newReadingList;
//        this.readingLists = this.readingLists.stream().map(x -> x.getId() == changedReadingList.getId() ? changedReadingList : x).toList();
    }

    public List<ReadingList> getReadingListsByName(String researcherName) {
        List<ReadingList> readingLists = jsonFileIO.readFile();
        return readingLists.stream().filter(x -> Objects.equals(x.getCreatorResearcherName(), researcherName)).toList();
    }

    @Override
    public void update(ICustomSubject subject) {
        this.writeReadingList();
    }
}
