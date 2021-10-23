import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class StudentBase {
    private DataBase db;

    private void listAllStudents() {
        ArrayList<Student> students = db.getAllStudents();

        System.out.println("""
                All students
                ------------""");
        for (int i = 0; i < students.size(); i++) {
            System.out.println((i + 1) + " - " + students.get(i) + " of house " + students.get(i).getHouse());

        }
        System.out.println("-------------------");
        System.out.println("There are " + students.size() + " students in the database");
    }

    private void editStudent(Student currentStudent) {
        // Edit student - only possible if currentStudent isn't null
        if (currentStudent != null) {
            // TODO: Edit
            System.out.println("Edit student (NOT YET IMPLEMENTED)");
        } else {
            System.out.println("Please find a student first");
        }
    }

    private Student findStudent(Student currentStudent, Scanner scanner) {
        System.out.println("""
                Find student
                ------------
                Enter part of the name (any name, first, last or middle) to find the students that match""");
        System.out.print(": ");
        String search = scanner.nextLine().trim().toLowerCase();

        ArrayList<Student> foundStudents = db.findStudent(search);

        if (foundStudents.size() == 1) {
            currentStudent = foundStudents.get(0);
            System.out.println("Found: " + currentStudent);
        } else if (foundStudents.size() > 1) {
            System.out.println("Found more matches:");
            for (int i = 0; i < foundStudents.size(); i++) {
                System.out.println("#" + (i + 1) + ": " + foundStudents.get(i));
            }
            System.out.println("Please select by entering the number next to the #: ");
            int select = 0;
            while (select < 1 || select > foundStudents.size()) {
                select = scanner.nextInt();
                scanner.nextLine();
                if (select < 1 || select > foundStudents.size()) {
                    System.out.println("Please enter a number between 1 and " + foundStudents.size());
                } else {
                    currentStudent = foundStudents.get(select - 1);
                    System.out.println("You selected: " + currentStudent);
                }
            }
        } else {
            System.out.println("No students found that match the search criteria");
            currentStudent = null;
        }
        return currentStudent;
    }

    private void deleteStudent(Student currentStudent, Scanner scanner) {
        // Delete student - only possible if currentStudent isn't null
        if (currentStudent != null) {
            System.out.println("Are you sure that you want to delete: '" + currentStudent + "' (y/N)?\nThis operation cannot be undone!");
            String answer = scanner.nextLine();
            if ("y".equalsIgnoreCase(answer)) {
                db.removeStudent(currentStudent);
                System.out.println("'" + currentStudent + "' has been deleted.");
            } else {
                System.out.println("ok");
            }

        } else {
            System.out.println("Please find a student first");
        }
    }

    private void createStudent(Scanner scanner) {
        System.out.println("""
                Create student
                --------------
                Please enter first name, last name, middle name (if any), and house""");
        System.out.print("first name: ");
        String firstName = scanner.nextLine();
        System.out.print("last name: ");
        String lastName = scanner.nextLine();
        System.out.print("middle name (press enter if no middle name): ");
        String middleName = scanner.nextLine();
        System.out.print("house: ");
        String house = scanner.nextLine();

        Student student = db.createStudent(firstName, lastName, middleName, house);

        System.out.println("Student '" + student + "' added - there are now " + db.size() + " students in the database");
    }

    private void start() throws FileNotFoundException {
        db = new DataBase();

        db.loadFile();

        System.out.println("Welcome to StudentBase 9001");
        System.out.println("---------------------------");

        Student currentStudent = null;

        Scanner scanner = new Scanner(System.in);
        boolean isRunning = true;
        while (isRunning) {
            System.out.println("""
                                        
                    Make your selection:
                    1) List all students
                    2) Find student (must find before edit or delete)
                        3) Edit student
                        4) Delete student
                    9) Create new student
                                    
                    0) Exit application""");
            int selection = scanner.nextInt();
            scanner.nextLine(); // Fix for the "scanner bug"
            switch (selection) {
                case 0:
                    System.out.println("Saving the list of students");

                    db.saveFile();

                    System.out.println("exiting, thanks bye!");
                    isRunning = false;
                    System.exit(0);
                    break;
                case 1:
                    listAllStudents();
                    break;
                case 2:
                    currentStudent = findStudent(currentStudent, scanner);
                    break;
                case 3:
                    editStudent(currentStudent);
                    break;
                case 4:
                    deleteStudent(currentStudent, scanner);
                    break;
                case 9:
                    createStudent(scanner);
                    break;
            }
        }
    }

    public static void main(String[] args) throws FileNotFoundException {
        StudentBase application = new StudentBase();
        application.start();
    }


}
