import java.io.FileNotFoundException;

public class StudentBase {
    private Controller controller;
    private UserInterface ui;

    private void start() throws FileNotFoundException {
        controller = new Controller();
        ui = new UserInterface(controller);

        controller.start();
        ui.start();
    }

    public static void main(String[] args) throws FileNotFoundException {
        StudentBase application = new StudentBase();
        application.start();
    }
}
