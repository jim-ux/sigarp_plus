package pe.sunarp.sigarpplus.controllers.boletas;

import javafx.application.Platform;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;

import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import pe.sunarp.sigarpplus.SigarpPlusApp;
import pe.sunarp.sigarpplus.entities.Concepto;
import pe.sunarp.sigarpplus.entities.Trabajador;
import pe.sunarp.sigarpplus.models.boletas.BoletasModel;
import pe.sunarp.sigarpplus.utils.Constantes;

import java.io.File;
import java.text.DecimalFormat;
import java.util.List;

public class BoletasDetailController {

    private Trabajador datosTrab;
    @FXML
    private TextField txtCodTrab;
    @FXML
    private TextField txtNombres;
    @FXML
    private TextField txtDocumento;
    @FXML
    private TextField txtFechaIngreso;
    @FXML
    private TextField txtFechaCese;
    @FXML
    private TextField txtPlaza;
    @FXML
    private TextField txtCargoLab;
    @FXML
    private TextField txtNomAfp;
    @FXML
    private TextField txtNumeroAfp;

    @FXML
    private TableView<Concepto> tbvIngresos;

    @FXML
    private TableView<Concepto> tbvEgresos;

    @FXML
    private TableView<Concepto> tbvAportes;

    @FXML
    private TableColumn<Concepto, String> tbcIngCod;
    @FXML
    private TableColumn<Concepto, String> tbcIngNom;
    @FXML
    private TableColumn<Concepto, String> tbcIngMonto;

    @FXML
    private TableColumn<Concepto, String> tbcEgrCod;
    @FXML
    private TableColumn<Concepto, String> tbcEgrNom;
    @FXML
    private TableColumn<Concepto, String> tbcEgrMonto;

    @FXML
    private TableColumn<Concepto, String> tbcApoCod;
    @FXML
    private TableColumn<Concepto, String> tbcApoNom;
    @FXML
    private TableColumn<Concepto, String> tbcApoMonto;
    @FXML
    private TextField txtCuenta;

    @FXML
    private ObservableList<Concepto> listaIngresos = FXCollections.observableArrayList();

    @FXML
    private ObservableList<Concepto> listaEgresos = FXCollections.observableArrayList();

    @FXML
    private ObservableList<Concepto> listaAportes = FXCollections.observableArrayList();

    @FXML
    private TextField txtTotalIng;
    @FXML
    private TextField txtTotalEgr;
    @FXML
    private TextField txtTotalApo;
    @FXML
    private Label lblTotalBoleta;

    @FXML
    private ImageView imvLogo;


    @FXML
    private void initialize(){

        this.configureTables();

        Image logoSunarp = new Image(SigarpPlusApp.class.getResourceAsStream("pdf-assets/logo-sunarp.png"));
        this.imvLogo.setImage(logoSunarp);
    }

    public void setData(Trabajador trabajador, String baseRuta){

        this.datosTrab = trabajador;

        txtCodTrab.setText(this.datosTrab.getCodTrab());
        txtNombres.setText(this.datosTrab.getApPaterno() + " " + this.datosTrab.getApMaterno() + " " +this.datosTrab.getNombres());
        txtDocumento.setText(this.datosTrab.getDocumento());
        txtCargoLab.setText(this.datosTrab.getCargoLab());
        txtNomAfp.setText(Constantes.listaAFP.get(this.datosTrab.getNomAfp()));
        txtNumeroAfp.setText(this.datosTrab.getNumeroAfp());

        Platform.runLater(()->{
            this.fillTables(baseRuta);
        });
    }

    private void configureTables(){

        this.tbcIngCod.setCellValueFactory(new PropertyValueFactory<>("codCpt"));
        this.tbcIngNom.setCellValueFactory(new PropertyValueFactory<>("nomCpt"));
        this.tbcIngMonto.setCellValueFactory(cellData -> new SimpleStringProperty(this.formatMoney(cellData.getValue())));

        this.tbcEgrCod.setCellValueFactory(new PropertyValueFactory<>("codCpt"));
        this.tbcEgrNom.setCellValueFactory(new PropertyValueFactory<>("nomCpt"));
        this.tbcEgrMonto.setCellValueFactory(new PropertyValueFactory<>("montoCpt"));

        this.tbcApoCod.setCellValueFactory(new PropertyValueFactory<>("codCpt"));
        this.tbcApoNom.setCellValueFactory(new PropertyValueFactory<>("nomCpt"));
        this.tbcApoMonto.setCellValueFactory(new PropertyValueFactory<>("montoCpt"));

        this.tbvIngresos.setItems(listaIngresos);
        this.tbvEgresos.setItems(listaEgresos);
        this.tbvAportes.setItems(listaAportes);



    }

    private void fillTables(String rutaArchivos){

        BoletasModel boletasModel = new BoletasModel();
        List<Concepto> listaMontos = boletasModel.getDetalleBoleta(this.datosTrab.getCodTrab(), rutaArchivos + File.separator + Constantes.fileNames.get("montos"));

        String cuenta = boletasModel.getCuentaBancaria(this.datosTrab.getCodTrab(), rutaArchivos + File.separator + Constantes.fileNames.get("bancos"));
        txtCuenta.setText(cuenta);

        listaMontos.forEach( (Concepto concepto) -> {

            int numberCodConcepto = Integer.parseInt(concepto.getCodCpt());

            switch (numberCodConcepto){

                case 1:

                case 2:
                    break;

                case 501: {
                    txtTotalIng.setText("S/. "+concepto.getMontoCpt());
                    break;
                }
                case 502: {
                    txtTotalEgr.setText("S/. "+concepto.getMontoCpt());
                    break;
                }
                case 503: {
                    lblTotalBoleta.setText("S/. "+concepto.getMontoCpt());
                    break;
                }
                case 699: {
                    txtTotalApo.setText("S/. "+concepto.getMontoCpt());
                    break;
                }
                default: {

                    if(numberCodConcepto > 600){

                        listaAportes.add(concepto);

                    }else if(numberCodConcepto > 300){

                        listaEgresos.add(concepto);

                    }else{

                        listaIngresos.add(concepto);

                    }

                }

            }

        });



    }

    private String formatMoney(Concepto concepto){

        DecimalFormat currency = new DecimalFormat("S/ #,###.##");
        return currency.format(concepto.getMontoCpt());

    }
}
