import java.io.FileNotFoundException;

public class Controller {
    private final DataBase db;
    private Student selectedStudent = null;

    public Controller() {
        db = new DataBase();
    }

    public void start() throws FileNotFoundException {
        db.loadFile();
    }

    public void end() throws FileNotFoundException {
        db.saveFile();
    }

    public Iterable<Student> getAllStudents() {
        return db.getAllStudents();
    }

    public int getNumberOfStudents() {
        return db.size();
    }

    public Student createStudent(String firstName, String lastName, String middleName, String house) {
        return db.createStudent(firstName, lastName, middleName, house);
    }

    public Student[] findStudent(String search) {
        return db.findStudent(search);
    }

    public void selectStudent(Student student) {
        selectedStudent = student;
    }

    public Student getSelectedStudent() {
        return selectedStudent;
    }

    public boolean hasSelectedStudent() {
        return selectedStudent != null;
    }

    public Student deleteSelectedStudent() {
        Student student = selectedStudent;

        db.removeStudent(selectedStudent);
        selectedStudent = null;

        return student;
    }
}
