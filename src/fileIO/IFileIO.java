package fileIO;

import java.util.List;
import java.util.Map;

public interface IFileIO extends IReadFileIO {
    void writeFile(Map<String, String> line);

}
