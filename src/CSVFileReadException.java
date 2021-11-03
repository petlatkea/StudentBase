import java.io.FileNotFoundException;

public class CSVFileReadException extends RuntimeException {
    public CSVFileReadException(String message, FileNotFoundException cause) {
        super(message,cause);
    }
}
