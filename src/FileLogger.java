import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class FileLogger implements Logger {
    private static final String FILE_LOGGER_NAME =  "StudentFileOutput.txt";

    static {

        /** TODO D
         * Dcreate a new File object for FILE_LOGGER_NAME
         * Dif the file already exists, delete it first
         * Duse try/catch block
         */

        File fiOb = new File(FILE_LOGGER_NAME);

        try{
            if (fiOb.exists()){
                fiOb.delete();
            }
            fiOb.createNewFile();
        } catch(IOException e){
            e.getStackTrace();
        }
    }


    @Override
    public void log (String message) {
        /** TODO D
         * Dcreate a new FileWriter in append mode
         * Dwrite the message to file
         * Dcheck the ExpectedOutput files
         * Duse try-with-resources/catch block
         */
        try (FileWriter fiWri = new FileWriter(FILE_LOGGER_NAME, true)) {

            fiWri.append(message);
        }
        catch (IOException e) {

            e.printStackTrace();
        }
    }
}
