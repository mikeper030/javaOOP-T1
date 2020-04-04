package url;

import exceptions.WebPageException;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class URLParser {
    private String line;
    private URLParser(){}

    public URLParser(String line){
       this.line = line;
    }

    public long getFileSize() throws WebPageException,
            MalformedURLException {
        URL url = new URL(line);
        HttpURLConnection conn = null;
        try {
            conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("HEAD");
            return conn.getContentLengthLong();
        } catch (IOException e) {
            throw new WebPageException(e.getMessage());
        } finally {
            if (conn != null) {
                conn.disconnect();
            }
        }
    }
}
