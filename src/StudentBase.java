public class StudentBase {

    private void start() {
        Controller controller = new Controller();
        UserInterface ui = new UserInterface(controller);

        controller.start();
        ui.start();
    }

    public static void main(String[] args) {
        StudentBase application = new StudentBase();
        application.start();
    }
}
