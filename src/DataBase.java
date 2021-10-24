import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintStream;
import java.util.ArrayList;

public class DataBase {
    private ArrayList<Student> students = new ArrayList<>();

    public void loadFile() throws FileNotFoundException {
        // load students from database-file
        CSVFile file = new CSVFile("students.csv");
        file.openForRead();

        while(file.hasNext()) {
            String[] strings = file.next();

            String firstName = strings[0];
            String lastName = strings[1];
            String middleName = strings[2];
            String house = strings[3];

            createStudent(firstName,lastName,middleName,house);
        }
        file.close();
    }

    public void saveFile() throws FileNotFoundException {
        CSVFile file = new CSVFile("students.csv");
        file.openForWrite();

        file.writeHeading(new String[]{"firstName", "lastName", "middleName", "house"});

        for(Student student : students) {
            file.writeLine(new String[]{student.getFirstName(), student.getLastName(), student.getMiddleName(), student.getHouse()});
        }
    }

    public Iterable<Student> getAllStudents() {
        return students;
    }

    public Student createStudent(String firstName, String lastName, String house) {
        return createStudent(firstName, lastName, null, house);
    }

    public Student createStudent(String firstName, String lastName, String middleName, String house) {
        if("".equals(middleName.trim())) {
            middleName = null;
        }
        Student student = new Student(firstName, lastName, middleName, house);
        students.add(student);
        return student;
    }

    public void removeStudent(Student student) {
        students.remove(student);
    }

    public Student[] findStudent(String search) {
        ArrayList<Student> foundStudents = new ArrayList<>();
        for (int i = 0; i < students.size(); i++) {
            if (students.get(i).getFirstName().toLowerCase().contains(search) ||
                    students.get(i).getLastName().toLowerCase().contains(search) ||
                    (students.get(i).getMiddleName() != null && students.get(i).getMiddleName().toLowerCase().contains(search))) {
                foundStudents.add(students.get(i));
            }
        }
        return foundStudents.toArray(new Student[0]); // Note: size 0 is better than size foundStudents.size(), since the array is never used!
    }

    public int size() {
        return students.size();
    }
}
