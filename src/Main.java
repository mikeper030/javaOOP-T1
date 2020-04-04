import url.URLFileReader;
import url.URLFileWriter;

import java.io.IOException;

public class Main {
    public static void main(String[]args){

        if(args.length!=2){
            System.out.println("Wrong usage");
            return;
        }

        String inFile = args[0],outFile= args[1];
        URLFileReader urlReader = new URLFileReader();
        try {

            urlReader.setOnFileProcessedListener(data -> {
               URLFileWriter urlFileWriter = new URLFileWriter();
                    urlFileWriter.writeToFile(data,outFile);

            });
            urlReader.readFromFile(inFile);
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }

    }
}
