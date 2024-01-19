package pe.sunarp.sigarpplus.controllers.boletas;

import javafx.application.Platform;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.print.*;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.shape.Line;
import javafx.scene.text.TextAlignment;
import javafx.stage.Stage;
import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.engine.design.JasperDesign;
import net.sf.jasperreports.engine.xml.JRXmlLoader;
import net.sf.jasperreports.view.JasperViewer;
import pe.sunarp.sigarpplus.SigarpPlusApp;
import pe.sunarp.sigarpplus.entities.Concepto;
import pe.sunarp.sigarpplus.entities.Trabajador;
import pe.sunarp.sigarpplus.models.boletas.BoletasModel;
import pe.sunarp.sigarpplus.utils.Constantes;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.text.DecimalFormat;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class BoletasDetailController {

    private Trabajador datosTrab;

    private String fechaBoleta;

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

    public void setData(Trabajador trabajador, String fechaBoleta, String baseRuta){

        this.datosTrab = trabajador;
        this.fechaBoleta = fechaBoleta;
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

    @FXML
    private void generateReport() {

        Stage stage = (Stage) this.txtCargoLab.getScene().getWindow();
        PrinterJob job = PrinterJob.createPrinterJob();

        //&& job.showPrintDialog(stage)
        if(job != null ){
            Printer printer = job.getPrinter();
            PageLayout pageLayout = printer.createPageLayout(Paper.A4, PageOrientation.PORTRAIT, Printer.MarginType.HARDWARE_MINIMUM);

            double pageWidth = pageLayout.getPrintableWidth();
            double pageHeight = pageLayout.getPrintableHeight();

            Parent root = this.configuratePrintPage(pageWidth, pageHeight/2);

//            Scene scenePreview = new Scene(root, pageWidth, pageHeight/2);
//            Stage stagePreview = new Stage();
//            stagePreview.setResizable(false);
//            stagePreview.setScene(scenePreview);
//            stagePreview.show();

            boolean jobState =  job.printPage(pageLayout, root);
            if(jobState){
                job.endJob();
            }

            System.out.println("Imprimiendo");
        }

    }

    @FXML
    private Parent configuratePrintPage(double width, double height){

        AnchorPane anchorPane = new AnchorPane();
        anchorPane.setStyle("-fx-background-color: white");
        anchorPane.setPrefWidth(width);
        anchorPane.setPrefHeight(height);

        //Title
        Label lblTitle = new Label("BOLETA DE PAGO");
        lblTitle.setPrefWidth(width);
        lblTitle.setStyle("-fx-font-weight: bold;-fx-font-size: 16px; -fx-alignment: center");


        //TABLES
        HBox hbxTables = new HBox();
        hbxTables.setLayoutY(50f);
        AnchorPane.setLeftAnchor(hbxTables, 0.0);
        AnchorPane.setRightAnchor(hbxTables, 0.0);

        double tableWidth = width/3;

        for(int i = 0; i< 3; i++){
            VBox vboxTable = new VBox();
            vboxTable.setSpacing(2f);
            Label lblTableTitle = new Label();
            lblTableTitle.setPrefWidth(tableWidth);
            lblTableTitle.setStyle("-fx-font-weight: bold; -fx-alignment: center");
            vboxTable.getChildren().add(lblTableTitle);
            switch (i){

                case 0: {
                    lblTableTitle.setText("Ingresos");
                    listaIngresos.forEach((Concepto cop) ->{
                        HBox row = new HBox();
                        Label lblNomCpt = new Label(cop.getNomCpt());
                        lblNomCpt.setPrefWidth(tableWidth - 50);
                        Label lblMontoCpt = new Label(cop.getMontoCpt().toString());
                        lblMontoCpt.setPrefWidth(50);
                        row.getChildren().addAll(lblNomCpt, lblMontoCpt);
                        vboxTable.getChildren().add(row);
                    });

                    hbxTables.getChildren().add(vboxTable);

                    break;
                }
                case 1: {

                    lblTableTitle.setText("Egresos");
                    listaEgresos.forEach((Concepto cop) ->{
                        HBox row = new HBox();
                        Label lblNomCpt = new Label(cop.getNomCpt());
                        lblNomCpt.setPrefWidth(tableWidth - 50);
                        Label lblMontoCpt = new Label(cop.getMontoCpt().toString());
                        lblMontoCpt.setPrefWidth(50);
                        row.getChildren().addAll(lblNomCpt, lblMontoCpt);
                        vboxTable.getChildren().add(row);
                    });

                    hbxTables.getChildren().add(vboxTable);
                    break;
                }
                case 2:{

                    lblTableTitle.setText("Aportes");
                    listaAportes.forEach((Concepto cop) ->{
                        HBox row = new HBox();
                        Label lblNomCpt = new Label(cop.getNomCpt());
                        lblNomCpt.setPrefWidth(tableWidth - 50);
                        Label lblMontoCpt = new Label(cop.getMontoCpt().toString());
                        lblMontoCpt.setPrefWidth(50);
                        row.getChildren().addAll(lblNomCpt, lblMontoCpt);
                        vboxTable.getChildren().add(row);
                    });

                    hbxTables.getChildren().add(vboxTable);
                    break;
                }

            }

        }

        //FIRMAS

        Line lineaFirma = new Line(width - 100,height-30,width,height-30);
        Label lblFirma = new Label("Recibí conforme");
        lblFirma.setLayoutX(width-95);
        lblFirma.setLayoutY(height - 25);


        anchorPane.getChildren().addAll(lblTitle, hbxTables, lineaFirma, lblFirma);

        return anchorPane;

    }
}
