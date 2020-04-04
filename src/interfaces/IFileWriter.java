package interfaces;

import java.io.IOException;
import java.util.Map;
import java.util.Set;

public interface IFileWriter {
    void writeToFile(Set<Map.Entry<String, Long>> data,String fileName)throws IOException;
}
