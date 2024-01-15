package pe.sunarp.sigarpplus;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.IOException;

public class SigarpPlusApp extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(SigarpPlusApp.class.getResource("splash.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 300, 200);
        Image logoApp = new Image(SigarpPlusApp.class.getResourceAsStream("icons/cuenta.png"));
        stage.getIcons().add(logoApp);
        stage.setTitle("Bienvenido");
        stage.setScene(scene);
        stage.initStyle(StageStyle.UNDECORATED);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}