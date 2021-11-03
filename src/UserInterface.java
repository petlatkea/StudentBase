import java.util.Scanner;

public class UserInterface {
    private final Controller controller;

    public UserInterface(Controller controller) {
        this.controller = controller;
    }

    public void start() {
        System.out.println("Welcome to StudentBase 9001");
        System.out.println("---------------------------");

        try {
            controller.start();
        } catch(CSVFileReadException exception) {
            System.out.println("Couldn't load file - start with empty database");
        }

        mainMenu();
    }

    private void mainMenu() {
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

                    boolean notSaved = true;
                    while(notSaved) {
                        try {
                            System.out.println("Saving the list of students");
                            controller.end();
                            notSaved = false;
                            System.out.println("exiting, thanks bye!");
                            isRunning = false;
                        }
                        catch (CSVFileWriteException exception) {
                            notSaved = true;
                            System.out.println("Could not save the list to usual filename");
                            System.out.print("Please supply a diffent filename: ");
                            String filename = scanner.nextLine();
                            controller.setFileName(filename);
                        }
                    }

                    break;
                case 1:
                    listAllStudents();
                    break;
                case 2:
                    findStudent(scanner);
                    break;
                case 3:
                    editStudent();
                    break;
                case 4:
                    deleteStudent(scanner);
                    break;
                case 9:
                    createStudent(scanner);
                    break;
            }
        }

    }

    private void listAllStudents() {
        System.out.println("""
                All students
                ------------""");
        int counter = 1;
        for (Student student : controller.getAllStudents()) {
            System.out.println(counter + " - " + student + " of house " + student.getHouse());
            counter++;
        }
        System.out.println("-------------------");
        System.out.println("There are " + controller.getNumberOfStudents() + " students in the database");
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

        Student student = controller.createStudent(firstName, lastName, middleName, house);
        System.out.println("Student '" + student + "' added - there are now " + controller.getNumberOfStudents() + " students in the database");
    }

    private void findStudent(Scanner scanner) {
        System.out.println("""
                Find student
                ------------
                Enter part of the name (any name, first, last or middle) to find the students that match""");
        System.out.print(": ");
        String search = scanner.nextLine().trim().toLowerCase();

        Student[] foundStudents = controller.findStudent(search);

        if (foundStudents.length == 1) {
            controller.selectStudent(foundStudents[0]);
            System.out.println("Found: " + foundStudents[0]);
        } else if (foundStudents.length > 1) {
            System.out.println("Found more matches:");
            for (int i = 0; i < foundStudents.length; i++) {
                System.out.println("#" + (i + 1) + ": " + foundStudents[i]);
            }
            System.out.println("Please select by entering the number next to the #: ");
            int select = 0;
            while (select < 1 || select > foundStudents.length) {
                select = scanner.nextInt();
                scanner.nextLine();
                if (select < 1 || select > foundStudents.length) {
                    System.out.println("Please enter a number between 1 and " + foundStudents.length);
                } else {
                    controller.selectStudent(foundStudents[select - 1]);
                    System.out.println("You selected: " + foundStudents[select - 1]);
                }
            }
        } else {
            System.out.println("No students found that match the search criteria");
            controller.selectStudent(null);
        }
    }

    private void deleteStudent(Scanner scanner) {
        // Delete student - only possible if there is a selected student
        if (controller.hasSelectedStudent()) {
            System.out.println("Are you sure that you want to delete: '" + controller.getSelectedStudent() + "' (y/N)?\nThis operation cannot be undone!");
            String answer = scanner.nextLine();
            if ("y".equalsIgnoreCase(answer)) {
                System.out.println("'" + controller.deleteSelectedStudent() + "' has been deleted.");
            } else {
                System.out.println("ok");
            }

        } else {
            System.out.println("Please find a student first");
        }
    }

    private void editStudent() {
        // Edit student - only possible if there is a selected student
        if (controller.hasSelectedStudent()) {
            // TODO: Edit
            System.out.println("Edit student (NOT YET IMPLEMENTED)");
        } else {
            System.out.println("Please find a student first");
        }
    }
}
