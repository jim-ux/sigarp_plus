package pe.sunarp.sigarpplus.controllers;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ProgressBar;
import javafx.stage.Stage;
import javafx.util.Duration;
import pe.sunarp.sigarpplus.SigarpPlusApp;
import pe.sunarp.sigarpplus.utils.Assets;

import java.io.IOException;

public class SplashController {

    @FXML
    private ProgressBar progressBar;

    @FXML
    private void initialize(){

        Timeline timeline = new Timeline(new KeyFrame(Duration.seconds(3), (ActionEvent event)->{

                try {

                    FXMLLoader fxmlLoader = new FXMLLoader(SigarpPlusApp.class.getResource("views/main-window.fxml"));
                    Parent parent = fxmlLoader.load();
                    Scene scene = new Scene(parent, 800,600);
                    Stage stage = new Stage();
                    stage.setScene(scene);
                    stage.getIcons().add(Assets.iconoApp);
                    stage.setTitle("Sigarp Plus <<Sunarp ZR IX>>");
                    stage.show();

                    ((Stage) this.progressBar.getScene().getWindow()).close();

                }catch (IOException ex){

                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setHeaderText("Error de archivo");
                    alert.setContentText(ex.toString());
                    alert.show();

                }

            })
        );

        timeline.play();

    }
}
