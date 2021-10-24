import java.io.FileNotFoundException;

public class StudentBase {

    private void start() throws FileNotFoundException {
        Controller controller = new Controller();
        UserInterface ui = new UserInterface(controller);

        controller.start();
        ui.start();
    }

    public static void main(String[] args) throws FileNotFoundException {
        StudentBase application = new StudentBase();
        application.start();
    }
}
