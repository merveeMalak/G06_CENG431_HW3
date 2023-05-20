package fileManagement;

import fileIO.JsonFileIO;
import model.ReadingList;
import model.Researcher;
import view.ICustomObserver;
import view.ICustomSubject;

import java.util.List;
import java.util.Objects;

public class JsonFileManagement implements ICustomObserver {
    private JsonFileIO jsonFileIO;
    private List<ReadingList> readingLists;


    public JsonFileManagement() {
        this.jsonFileIO = new JsonFileIO();
        this.readingLists = this.jsonFileIO.readFile();
    }

    public void addReadingList(ReadingList readingList) {
        this.readingLists.add(readingList);
    }

    public void writeReadingList() {
        if (this.readingLists.size() != 0) {
            this.jsonFileIO.writeFileIO(this.readingLists);
        }
    }

    public void setReadingList(ReadingList readingList) {
        this.readingLists = this.readingLists.stream().map(x -> x.getId() == readingList.getId() ? readingList : x).toList();
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
