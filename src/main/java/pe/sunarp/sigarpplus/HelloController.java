package pe.sunarp.sigarpplus;

import javafx.fxml.FXML;
import javafx.scene.control.Label;


import java.io.IOException;



public class HelloController {
    @FXML
    private Label welcomeText;

    @FXML
    protected void onHelloButtonClick() {
        welcomeText.setText("Welcome to JavaFX Application!");
    }

    @FXML
    private void initialize() throws IOException {

    }


}
