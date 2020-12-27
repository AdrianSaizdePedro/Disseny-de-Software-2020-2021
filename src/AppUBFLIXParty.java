
import ub.edu.controller.IController;


public class AppUBFLIXParty {

    private IController controller;

    public AppUBFLIXParty(){
    }

    public void setStrategy(IController controller) {
        this.controller = controller;
    }

    public void run(){
        try {
            controller.init();
        } catch(NullPointerException exp){
            System.out.println("No Controller/Strategy specified for the app.");
        } catch(Exception exp){
            System.out.println("FAILED to run the app with strategy: " + controller.getClass());
        }
    }
}
