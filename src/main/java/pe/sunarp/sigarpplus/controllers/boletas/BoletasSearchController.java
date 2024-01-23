package pe.sunarp.sigarpplus.controllers.boletas;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;

import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseButton;
import javafx.stage.Modality;
import javafx.stage.Stage;
import pe.sunarp.sigarpplus.SigarpPlusApp;
import pe.sunarp.sigarpplus.entities.Trabajador;
import pe.sunarp.sigarpplus.models.boletas.BoletasModel;
import pe.sunarp.sigarpplus.utils.Assets;
import pe.sunarp.sigarpplus.utils.Constantes;

import java.io.File;
import java.text.MessageFormat;
import java.util.List;
import java.util.prefs.Preferences;

public class BoletasSearchController {

    private String urlFiles;

    private String folderBoletas = "";
 
    @FXML
    private ComboBox cbxYear;
    @FXML
    private ComboBox cbxMonth;
    @FXML
    private TableView<Trabajador> tbvTrabs;
    @FXML
    private TableColumn<Trabajador, String> tbcCodTrab;
    @FXML
    private TableColumn<Trabajador, String> tbcApPaterno;
    @FXML
    private TableColumn<Trabajador, String> tbcApMaterno;
    @FXML
    private TableColumn<Trabajador, String> tbcNombres;
    @FXML
    private TableColumn<Trabajador, String> tbcDocumento;

    ObservableList<String> yearList = FXCollections.observableArrayList();
    ObservableList<String> monthList = FXCollections.observableArrayList();
    ObservableList<Trabajador> listaTrab = FXCollections.observableArrayList();


    @FXML
    private void initialize(){
        Preferences appInfo = Preferences.systemNodeForPackage(SigarpPlusApp.class);
        this.folderBoletas = appInfo.get("PREF_FOLDER_BOLETAS", "");
        this.fillYears();
        this.configureTable();

    }

    private void fillYears(){

        File baseDir = new File(this.folderBoletas);
        File[] listDir = baseDir.listFiles(File::isDirectory);

        if(listDir != null){

            for(File directory:listDir){

                yearList.add(directory.getName());

            }

            cbxYear.setItems(yearList);



        }else{

            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText("Error al leer carpeta");
            alert.setContentText("Configure la carpeta en la pestaña \"Boletas\"` -> \"Configurar carpeta\" ");
            alert.show();

        }


    }

    @FXML
    private void cbxYearOnClick(){

        String selectedMonth = (String) cbxYear.getValue();
        this.monthList.clear();
        this.cbxMonth.getItems().clear();
        this.fillMonths(selectedMonth);

    }

    private void fillMonths(String monthDir){


        String baseUrl = this.folderBoletas + File.separator + monthDir;
        File baseDir = new File(baseUrl);
        File[] listDir = baseDir.listFiles(File::isDirectory);

        if(listDir != null){

            for(File directory:listDir){

                monthList.add(directory.getName());

            }

            cbxMonth.getItems().addAll(monthList);
            cbxMonth.setDisable(false);


        }else{
            cbxMonth.setDisable(true);
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText("Oops");
            alert.setContentText("Error al leer carpeta");
            alert.show();

        }
    }

    @FXML
    private void btnSearchOnClick(){

        if(cbxMonth.getValue() == null || cbxYear.getValue() == null){
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setHeaderText("Campos incompletos!!!");
            alert.setContentText("Seleccione ambas opciones para buscar datos.");
            alert.show();
            return;
        }

        this.urlFiles = this.folderBoletas + File.separator + cbxYear.getValue() + File.separator + cbxMonth.getValue();
        String rutaTrabFiles = this.urlFiles + File.separator + Constantes.fileNames.get("trab");
        this.tbvTrabs.getItems().clear();

        try {

            List<Trabajador> lista = new BoletasModel().getListaBoletas(rutaTrabFiles);
            listaTrab.addAll(lista);


        }catch (IndexOutOfBoundsException | NullPointerException ex){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText("Error al leer el archivo");
            alert.setContentText(ex.toString());
            alert.show();

            ex.printStackTrace();
        }

    }

    private void configureTable(){

        //Columns
        tbcCodTrab.setCellValueFactory(new PropertyValueFactory<>("codTrab"));
        tbcApPaterno.setCellValueFactory(new PropertyValueFactory<>("apPaterno"));
        tbcApMaterno.setCellValueFactory(new PropertyValueFactory<>("apMaterno"));
        tbcNombres.setCellValueFactory(new PropertyValueFactory<>("nombres"));
        tbcDocumento.setCellValueFactory(new PropertyValueFactory<>("documento"));

        //Double-Click on row
        this.tbvTrabs.setRowFactory(tv ->{
            TableRow<Trabajador> row = new TableRow<>();


            row.setOnMouseClicked(event -> {

                if (event.getClickCount() == 2 && (! row.isEmpty()) && event.getButton() == MouseButton.PRIMARY) {

                    Trabajador rowData = row.getItem();
                    this.openDetailBoleta(rowData);

                }
            });

            return row;
        });

        this.tbvTrabs.setItems(this.listaTrab);

    }

    private void openDetailBoleta(Trabajador infoTrab){

        try {
            FXMLLoader fxmlLoader = new FXMLLoader(SigarpPlusApp.class.getResource("views/boletas/boletas-detalle.fxml"));
            Parent raiz = fxmlLoader.load();
            Scene scene = new Scene(raiz, 1000, 700);
            Stage stage = new Stage();
            stage.setResizable(false);
            stage.setScene(scene);
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.setTitle("Detalle de boleta");
            stage.getIcons().add(Assets.iconoApp);
            BoletasDetailController controller = fxmlLoader.getController();
            controller.setData(infoTrab, this.urlFiles);
            stage.show();



        }catch (Exception ex){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setContentText(ex.toString());
            alert.show();
        }

    }





}
