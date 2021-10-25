import java.io.FileNotFoundException;
import java.util.ArrayList;

public class DataBase {
    private final ArrayList<Student> students = new ArrayList<>();

    public void loadFile() throws FileNotFoundException {
        // load students from database-file
        CSVFile file = new CSVFile("students.csv");
        file.openForRead();

        while(file.hasNext()) {
            String[] strings = file.next();
            addStudent(Student.fromStrings(strings));
        }
        file.close();
    }

    public void saveFile() throws FileNotFoundException {
        CSVFile file = new CSVFile("students.csv");
        file.openForWrite();

        file.writeHeading(Student.getStringNames());

        for(Student student : students) {
            file.writeLine(student.toStrings());
        }
    }

    public Iterable<Student> getAllStudents() {
        return students;
    }

    public Student createStudent(String firstName, String lastName, String middleName, String house) {
        Student student = new Student(firstName, lastName, middleName, house);
        return addStudent(student);
    }

    private Student addStudent(Student student) {
        students.add(student);
        return student;
    }

    public void removeStudent(Student student) {
        students.remove(student);
    }

    public Student[] findStudent(String search) {
        ArrayList<Student> foundStudents = new ArrayList<>();
        for(Student student : students) {
            if(student.matches(search)) {
                foundStudents.add(student);
            }
        }

        return foundStudents.toArray(new Student[0]); // Note: size 0 is better than size foundStudents.size(), since the array is never used!
    }

    public int size() {
        return students.size();
    }
}
