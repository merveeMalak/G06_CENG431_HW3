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
//        mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);

        List<ReadingList> readingList = new ArrayList<>();
        File jsonFile = new File("data.json");

        if (jsonFile.exists()) {
            try {
                List<ReadingList> value = Arrays.asList(mapper.readValue(jsonFile, ReadingList[].class));
                System.out.println(value);
                readingList = new ArrayList<>(value);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
        return readingList;
    }

    @Override
    public void writeFileIO(List<ReadingList> readingLists) {
        List<ReadingList> localData = readFile();
        System.out.println(localData);
        localData.addAll(readingLists);
        System.out.println(localData);
        ObjectMapper mapper = new ObjectMapper();
        ObjectWriter writer = mapper.writer(new DefaultPrettyPrinter());

        try {
//                writer.writeValue(Paths.get("data.json").toFile(), map);
            writer.writeValue(Paths.get("data.json").toFile(), localData);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

//        for (ReadingList readingList : localData) {
//            Map<String, Object> map = new HashMap<>();
//            map.put("readinglist_id", readingList.getId());
//            map.put("creator_researcher_name", readingList.getCreatorResearcherName());
//            map.put("reading_list_name", readingList.getReadingListName());
//            map.put("number_of_papers", readingList.getNumberOfPapers());
//            map.put("name_of_papers", readingList.getNameOfPapers());
//
//        }

    }
}
