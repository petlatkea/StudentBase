import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintStream;
import java.util.Scanner;

public class CSVFile {
    private String filename;
    private Scanner filereader;
    private PrintStream filewriter;

    private final String SEPERATOR = ";";

    public CSVFile(String filename) {
        this.filename = filename;
    }

    public void openForRead() throws CSVFileReadException {
        try {
            filereader = new Scanner(new File(filename));
            // ignore the first line, which has the headings
            filereader.nextLine();
        } catch (FileNotFoundException ex) {
            throw new CSVFileReadException("Can't read from " + filename, ex);
        }
    }

    public void openForWrite() throws CSVFileWriteException {
        try {
            filewriter = new PrintStream(filename);
        } catch (FileNotFoundException ex) {
            throw new CSVFileWriteException("Can't write to " + filename, ex);
        }
    }

    public void writeLine(String[] strings) {
        for(int i=0;i<strings.length;i++) {
            if(strings[i] != null) {
                filewriter.print(strings[i]);
            }
            if(i < strings.length-1) {
                filewriter.print(SEPERATOR);
            }
        }
        filewriter.println();
    }

    public void writeHeading(String[] strings) {
        writeLine(strings);
    }

    public void close() {
        if(filewriter != null) {
            filewriter.flush();
        }
        if(filereader!= null) {
            filereader.close();
        }
    }

    public boolean hasNext() {
        return filereader.hasNext();
    }

    public String[] next() {
        String line = filereader.nextLine();
        String[] strings = line.split(SEPERATOR);
        return strings;
    }

}
