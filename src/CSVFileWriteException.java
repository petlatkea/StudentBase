import java.io.FileNotFoundException;

public class CSVFileWriteException extends RuntimeException {
    public CSVFileWriteException(String message, FileNotFoundException cause) {
        super(message,cause);
    }
}
