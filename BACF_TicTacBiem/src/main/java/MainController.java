import javafx.fxml.FXML;

public class MainController {

    @FXML beginController begincontroller;
    @FXML regelController regelcontroller;
    @FXML instellingenController instellingencontroller;
    @FXML speelController speelcontroller;
    @FXML eindController eindcontroller;

    @FXML public void initialize() {

        System.out.println("App gestart");

        begincontroller.init(this);
        regelcontroller.init(this);
        instellingencontroller.init(this);
        speelcontroller.init(this);
        eindcontroller.init(this);
    }
}