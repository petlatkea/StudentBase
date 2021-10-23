import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) throws FileNotFoundException {
        ArrayList<Student> students = new ArrayList<>();

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

                    System.out.println("exiting, thanks bye!");
                    isRunning = false;
                    System.exit(0);
                    break;
                case 1:
                    System.out.println("""
                            All students
                            ------------""");
                    for (int i = 0; i < students.size(); i++) {
                        if (students.get(i).getMiddleName() != null) {
                            System.out.println((i + 1) + " - " + students.get(i).getFirstName() + " " + students.get(i).getMiddleName() + " " + students.get(i).getLastName() + " of house " + students.get(i).getHouse());
                        } else {
                            System.out.println((i + 1) + " - " + students.get(i).getFirstName() + " " + students.get(i).getLastName() + " of house " + students.get(i).getHouse());
                        }
                    }
                    System.out.println("-------------------");
                    System.out.println("There are " + students.size() + " students in the database");
                    break;
                case 2:
                    System.out.println("""
                            Find student
                            ------------
                            Enter part of the name (any name, first, last or middle) to find the students that match""");
                    System.out.print(": ");
                    String search = scanner.nextLine().trim().toLowerCase();

                    ArrayList<Student> foundStudents = new ArrayList<>();
                    for (int i = 0; i < students.size(); i++) {
                        if (students.get(i).getFirstName().toLowerCase().contains(search) ||
                                students.get(i).getLastName().toLowerCase().contains(search) ||
                                (students.get(i).getMiddleName() != null && students.get(i).getMiddleName().toLowerCase().contains(search))) {
                            foundStudents.add(students.get(i));
                        }
                    }

                    if (foundStudents.size() == 1) {
                        currentStudent = foundStudents.get(0);
                        System.out.println("Found: " + currentStudent.getFirstName() + " " + currentStudent.getLastName());
                    } else if (foundStudents.size() > 1) {
                        System.out.println("Found more matches:");
                        for (int i = 0; i < foundStudents.size(); i++) {
                            System.out.println("#" + (i + 1) + ": " + foundStudents.get(i).getFirstName() + " " + foundStudents.get(i).getLastName());
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
                                System.out.println("You selected: " + currentStudent.getFirstName() + currentStudent.getLastName());
                            }
                        }
                    } else {
                        System.out.println("No students found that match the search criteria");
                        currentStudent = null;
                    }
                    break;

                case 3:
                    // Edit student - only possible if currentStudent isn't null
                    if (currentStudent != null) {
                        // TODO: Edit
                        System.out.println("Edit student (NOT YET IMPLEMENTED)");
                    } else {
                        System.out.println("Please find a student first");
                    }
                    break;
                case 4:
                    // Delete student - only possible if currentStudent isn't null
                    if (currentStudent != null) {
                        System.out.println("Are you sure that you want to delete: '" + currentStudent.getFirstName() + " " + currentStudent.getLastName() + "' (y/N)?\nThis operation cannot be undone!");
                        String answer = scanner.nextLine();
                        if ("y".equalsIgnoreCase(answer)) {
                            students.remove(currentStudent);
                            System.out.println("'" + currentStudent.getFirstName() + " " + currentStudent.getLastName() + "' has been deleted.");
                        } else {
                            System.out.println("ok");
                        }

                    } else {
                        System.out.println("Please find a student first");
                    }
                    break;
                case 9:
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
                    if ("".equals(middleName.trim())) {
                        middleName = null;
                    }
                    System.out.print("house: ");
                    String house = scanner.nextLine();

                    Student student = new Student(firstName, lastName, middleName, house);
                    students.add(student);
                    System.out.println("Student '" + student.getFirstName() + " " + student.getLastName() + "' added - there are now " + students.size() + " students in the database");
                    break;
            }
        }
    }
}
