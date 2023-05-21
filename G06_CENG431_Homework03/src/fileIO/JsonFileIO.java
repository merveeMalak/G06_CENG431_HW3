package fileIO;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.core.util.DefaultPrettyPrinter;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import model.ReadingList;

import java.io.File;
import java.io.IOException;
import java.nio.file.Paths;
import java.util.*;

public class JsonFileIO implements IFileIO<List<ReadingList>, List<ReadingList>> {
    @Override
    public List<ReadingList> readFile() {
        ObjectMapper mapper = new ObjectMapper();
        List<ReadingList> readingList = new ArrayList<>();
        File jsonFile = new File("data.json");
        if (jsonFile.exists()) {
            try {
                List<ReadingList> value = Arrays.asList(mapper.readValue(jsonFile, ReadingList[].class));
                readingList = new ArrayList<>(value);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
        return readingList;
    }

    @Override
    public void writeFileIO(List<ReadingList> readingLists) {
        ObjectMapper mapper = new ObjectMapper();
        ObjectWriter writer = mapper.writer(new DefaultPrettyPrinter());
        try {
            writer.writeValue(Paths.get("data.json").toFile(), readingLists);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
