import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class DataBase {
    private ArrayList<Student> students = new ArrayList<>();

    public void loadFile() throws FileNotFoundException {
        // load students from database-file
        File file = new File("students.csv");
        Scanner filereader = new Scanner(file);
        // ignore the first line, which has the headings
        filereader.nextLine();
        while (filereader.hasNext()) {
            String line = filereader.nextLine();
            String[] strings = line.split(";");

            String firstName = strings[0];
            String lastName = strings[1];
            String middleName = strings[2];
            String house = strings[3];

            if (middleName.trim().equals("")) {
                middleName = null;
            }

            Student student = new Student(firstName, lastName, middleName, house);
            students.add(student);
        }
    }

    public void saveFile() throws FileNotFoundException {
        File file = new File("students.csv");
        PrintStream output = new PrintStream(file);
        // write heading for the csv-file
        output.println("firstName;lastName;middleName;house");
        for (int i = 0; i < students.size(); i++) {
            if (students.get(i).getMiddleName() != null) {
                output.println(students.get(i).getFirstName() + ";" + students.get(i).getLastName() + ";" + students.get(i).getMiddleName() + ";" + students.get(i).getHouse());
            } else {
                // write an empty middleName if no middleName!
                output.println(students.get(i).getFirstName() + ";" + students.get(i).getLastName() + ";;" + students.get(i).getHouse());
            }
        }
        output.flush();
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
