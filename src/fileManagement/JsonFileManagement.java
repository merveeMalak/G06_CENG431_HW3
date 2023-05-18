package fileManagement;

import fileIO.JsonFileIO;
import model.ReadingList;

import java.util.List;

public class JsonFileManagement {
    public void writeReadingList(List<ReadingList> readingLists) {
        JsonFileIO jsonFileIO = new JsonFileIO();
        jsonFileIO.writeFileIO(readingLists);
    }
}
