package url;

import exceptions.WebPageException;
import interfaces.IFileReader;
import interfaces.OnFileProcessedListener;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.net.MalformedURLException;
import java.util.*;

public class URLFileReader implements IFileReader {

    private HashMap<String,Long> linesData = new HashMap<>();
    private OnFileProcessedListener onFileProcessedListener;

    @Override
    public void readFromFile(String fileName) throws IOException {
        System.out.println("reading file...");
        File file=new File(fileName);    //creates a new file instance
        FileReader fr=new FileReader(file);   //reads the file
        BufferedReader br=new BufferedReader(fr);  //creates a buffering character input stream
        StringBuffer sb=new StringBuffer();    //constructs a string buffer with no characters
        String line;
        while((line=br.readLine())!=null)
        {
            sb.append(line);      //appends line to string buffer
            sb.append("\n");     //line feed
        }
         fr.close();    //closes the stream and release the resources
         parseFile(sb.toString());
    }


    private void parseFile(String content) throws IOException {
        System.out.println("Parsing file...");
        linesData.clear();
        String [] lines = content.split("\n");

        if(lines.length>0){
            for (String line:lines){
                URLParser urlParser = new URLParser(line);
                long size = 0;
                try {
                    size = urlParser.getFileSize();
                    if(size!=-1)// web page does not exist
                    linesData.put(line,size);
                    else
                       System.out.println("Size is 0");
                } catch (WebPageException e) {
                   System.out.println(e.getMessage());
                    linesData.put(line,size);
                 //internal page error

                } catch (MalformedURLException e) {
                   System.out.println("Bad input");
                   //the url is not formed correctly skip
                }
            }
           Set<Map.Entry<String,Long>> data = sortData();
           if (onFileProcessedListener!=null){
               onFileProcessedListener.onFileProcessed(data);
           }else {
               throw new RuntimeException("url.URLFileReader must implement FileProcessedListener");
           }
        }
    }

  private Set<Map.Entry<String, Long>> sortData(){
      Set<Map.Entry<String, Long>> set =  linesData.entrySet();
      List<Map.Entry<String, Long>> list = new ArrayList<>(
              set);
      list.sort((o1, o2) -> o2.getValue().compareTo(o1.getValue()));

      for (Map.Entry<String, Long> entry : list) {
          System.out.println(entry.getValue());

      }
      return set;
  }
    public void setOnFileProcessedListener(OnFileProcessedListener listener){
        this.onFileProcessedListener = listener;
    }
}
