import ub.edu.controller.ControladorGUI;

public class MainClient {
    public static void main(String[] args) {
        AppUBFLIXParty app = new AppUBFLIXParty();
        app.setStrategy(ControladorGUI.getInstance());
        app.run();
    }
}
