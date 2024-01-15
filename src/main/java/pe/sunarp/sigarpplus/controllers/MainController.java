package pe.sunarp.sigarpplus.controllers;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;
import pe.sunarp.sigarpplus.SigarpPlusApp;

import java.io.IOException;

public class MainController {

    @FXML
    private AnchorPane acpWorkSpace;

    @FXML
    private MenuItem mniBoletas;

    @FXML
    private Stage mainStage;

    @FXML
    private void initialize(){

        Platform.runLater(()->{

            this.mainStage = (Stage) this.acpWorkSpace.getScene().getWindow();
            this.mainStage.setOnCloseRequest((WindowEvent event)->{

                Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                alert.setHeaderText("Confirmación");
                alert.setContentText("¿Desea salir de la aplicación?");
                alert.showAndWait().ifPresent(((ButtonType buttonType) -> {
                    if(buttonType.getButtonData() == ButtonBar.ButtonData.CANCEL_CLOSE){
                        event.consume();
                    }
                }));

            });



        });


    }


    @FXML
    private void loadModule(ActionEvent event){
        String eventId = ((MenuItem) event.getSource()).getId();
        String filePath = "";

        switch (eventId){

            case "mniBoletas": {
                filePath = "views/boletas/boletas-search.fxml";
                break;
            }

            case "mniCerrar":{

                Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                alert.setHeaderText("Confirmación");
                alert.setContentText("¿Desea salir de la aplicación?");
                alert.showAndWait().ifPresent(((ButtonType buttonType) -> {
                    if(buttonType.getButtonData() != ButtonBar.ButtonData.CANCEL_CLOSE){
                        System.exit(0);
                    }
                }));
                return;
            }


        }

        this.openWindowModule(filePath);


    }


    private void openWindowModule(String filePath){

        this.acpWorkSpace.getChildren().clear();

        try {

            FXMLLoader fxmlLoader = new FXMLLoader(SigarpPlusApp.class.getResource(filePath));
            AnchorPane root = fxmlLoader.load();
            AnchorPane.setLeftAnchor(root, 0.0);
            AnchorPane.setTopAnchor(root, 0.0);
            AnchorPane.setRightAnchor(root, 0.0);
            AnchorPane.setBottomAnchor(root, 0.0);
            this.acpWorkSpace.getChildren().add(root);

        }catch(IOException ex){

            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText("Error de archivo");
            alert.setContentText(ex.toString());
            alert.show();
        }



    }

}
