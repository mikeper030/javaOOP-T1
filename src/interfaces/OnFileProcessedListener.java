package interfaces;

import java.io.IOException;
import java.util.Map;
import java.util.Set;

public interface OnFileProcessedListener {
    void onFileProcessed(Set<Map.Entry<String, Long>> data) throws IOException;
}
