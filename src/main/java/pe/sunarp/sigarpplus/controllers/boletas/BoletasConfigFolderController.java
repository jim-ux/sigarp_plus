package pe.sunarp.sigarpplus.controllers.boletas;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.stage.DirectoryChooser;
import javafx.stage.Stage;
import pe.sunarp.sigarpplus.SigarpPlusApp;

import java.io.File;
import java.util.prefs.Preferences;

public class BoletasConfigFolderController {

    @FXML
    private TextField txtFolder;

    private Preferences appInfo = Preferences.systemNodeForPackage(SigarpPlusApp.class);

    private Stage stage;

    @FXML
    private void initialize(){


        final String folderBoletas = this.appInfo.get("PREF_FOLDER_BOLETAS", "");

        if(folderBoletas.isBlank()){
            txtFolder.setPromptText("Ninguna carpeta seleccionada");
        }else{
            txtFolder.setText(folderBoletas);
        }

        Platform.runLater(()->{
            this.stage = (Stage) this.txtFolder.getScene().getWindow();
        });

    }

    @FXML
    private void searchFolder(){


        DirectoryChooser chooser = new DirectoryChooser();
        chooser.setTitle("Buscar carpeta");
        File selectedFolder = chooser.showDialog(this.stage);
        if(selectedFolder !=null){
            this.txtFolder.setText(selectedFolder.getAbsolutePath());
        }

    }

    @FXML
    private void modifyFolder(){

        Alert alert = new Alert(Alert.AlertType.INFORMATION);

        try{

            this.appInfo.put("PREF_FOLDER_BOLETAS", this.txtFolder.getText());
            alert.setHeaderText("Correcto");
            alert.setContentText("La carpeta se ha configurado correctamente");

        }catch (Exception ex){
            alert.setAlertType(Alert.AlertType.ERROR);
            alert.setContentText(ex.toString());

        }finally {
            this.stage.close();
            alert.show();
        }




    };
}
