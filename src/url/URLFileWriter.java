package url;

import interfaces.IFileWriter;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Map;
import java.util.Set;

public class URLFileWriter implements IFileWriter {
    @Override
    public void writeToFile(Set<Map.Entry<String, Long>> data,String fileName) throws IOException {
        File f = new File(fileName);
        if (f.exists()){
            f.delete();
            f.createNewFile();
        }

            BufferedWriter bw = new BufferedWriter(new FileWriter(f, true));
           for (Map.Entry<String,Long> entry: data)
               bw.append(entry.getKey()).append(" ").append(String.valueOf(entry.getValue())).append("\n");

            bw.close();

    }
}
