package pe.sunarp.sigarpplus.controllers.boletas;

import javafx.application.Platform;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.geometry.Insets;
import javafx.print.*;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.shape.Line;
import javafx.scene.text.TextAlignment;
import javafx.stage.Stage;
import org.apache.commons.lang3.StringUtils;
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

        String cuenta = boletasModel.getCuentaBancaria(this.datosTrab.getCodTrab(), StringUtils.join (new String[]{rutaArchivos, Constantes.fileNames.get("bancos")}, File.separator));
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
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setHeaderText("Impresión en proceso");
                alert.setContentText("El documento saldrá en su impresora configurada por defecto");
                alert.show();
            }


        }

    }

    @FXML
    private Parent configuratePrintPage(double width, double height){

        AnchorPane anchorPane = new AnchorPane();
        anchorPane.setStyle("-fx-background-color: white; -fx-font-family: 'Yu Gothic'; -fx-font-size: 9px");
        anchorPane.setPrefWidth(width);
        anchorPane.setPrefHeight(height);

        //Title
        Label lblTitle = new Label("BOLETA DE PAGO");
        lblTitle.setPrefWidth(width);
        lblTitle.setStyle("-fx-font-weight: bold;-fx-font-size: 16px; -fx-alignment: center");

        //Title Zone
        Label lblZone = new Label("ZONA REGISTRAL N° IX\nSEDE LIMA");
        lblZone.setTextAlignment(TextAlignment.CENTER);
        Label lblUbication = new Label("Av. E.Rebagliati 561 Jesús María");
        lblUbication.setLayoutY(37f);
        Label lblRuc = new Label("R.U.C. Nro 20260998898");
        lblRuc.setStyle("-fx-alignment: center");
        lblRuc.setPrefWidth(width);
        lblRuc.setLayoutY(37f);
        //Pane datos
        AnchorPane pane = new AnchorPane();
        pane.setLayoutY(55f);
        pane.setPadding(new Insets(5,0,5,0));
        pane.setStyle("-fx-border-width: 1px 0px; -fx-border-color: black");
        AnchorPane.setLeftAnchor(pane, 0.0);
        AnchorPane.setRightAnchor(pane, 0.0);


        pane.getChildren().addAll(
                this.infoBoxBoleta("Código", this.datosTrab.getCodTrab(), 0.0,0.0),
                this.infoBoxBoleta("Apellidos y nombres", this.txtNombres.getText(), 40.0 , 0.0),
                this.infoBoxBoleta("Nro. DNI", this.txtDocumento.getText(), 340.0, 0.0),
                this.infoBoxBoleta("Fecha ingreso", "12/05/2004", 395.0, 0.0 ),
                this.infoBoxBoleta("Fecha cese", "12/05/2004", 470.0, 0.0 ),
                this.infoBoxBoleta("Plaza", "0000", .0, 25.0),
                this.infoBoxBoleta("Cargo", this.datosTrab.getCargoLab(), 40.0, 25.0),
                this.infoBoxBoleta("Nro Carné IPSS","", width/2, 25.0),
                this.infoBoxBoleta("A.F.P", this.txtNomAfp.getText(), 390.0, 25.0),
                this.infoBoxBoleta("Nro Carné AFP", this.datosTrab.getNumeroAfp(), 460.0, 25.0)
        );

        //Pane sueldo
        AnchorPane paneSueldo = new AnchorPane();
        paneSueldo.setLayoutY(120f);
        AnchorPane.setLeftAnchor(paneSueldo, 0.0);
        AnchorPane.setRightAnchor(paneSueldo, 0.0);

        paneSueldo.getChildren().addAll(
                this.infoBoxBoleta("Sueldo Básico", "", 0.0, 0.0),
                this.infoBoxBoleta("Días trab.", "30 días", 70.0, 0.0),
                this.infoBoxBoleta("Días Subsid.", "", 120.0, 0.0),
                this.infoBoxBoleta("Vacaciones   :   Periodo", "", width/2, 0.0),
                this.infoBoxBoleta("Inicio", "", 395.0,0.0),
            this.infoBoxBoleta("Término", "", 470.0,0.0)
        );

        //Table
        VBox vboxTables = new VBox();

        vboxTables.setLayoutY(150f);
        AnchorPane.setLeftAnchor(vboxTables, 0.0);
        AnchorPane.setRightAnchor(vboxTables, 0.0);

        HBox hbxTables = new HBox();
        vboxTables.getChildren().add(hbxTables);

        double tableWidth = width/3;

        for(int i = 0; i< 3; i++){
            VBox vboxTable = new VBox();
            vboxTable.setSpacing(2f);
            Label lblTableTitle = new Label();
            lblTableTitle.setPrefWidth(tableWidth);
            lblTableTitle.setStyle("-fx-font-weight: bold; -fx-alignment: center;");
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

                    lblTableTitle.setText("Aportes del empleador");
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

        //Table footer
        AnchorPane acpTableFooter = new AnchorPane();
        acpTableFooter.setPadding(new Insets(5.0,0.0,5.0,0.0));
        pane.setStyle("-fx-border-width: 1px 0px; -fx-border-color: black");
        acpTableFooter.setPrefWidth(width);



        acpTableFooter.getChildren().addAll(
            this.boxTotales("TOTAL INGRESOS:", this.txtTotalIng.getText(), 0.0,0.0,tableWidth/2),
                this.boxTotales("TOTAL EGRESOS:", this.txtTotalEgr.getText(), tableWidth,0.0,tableWidth/2),
                this.boxTotales("TOTAL APORTES", this.txtTotalApo.getText(), tableWidth*2, 0.0, tableWidth/2),
                this.boxTotales("Cta.SCOTIABANK", this.txtCuenta.getText(), 0.0, 15.0, tableWidth/2),
                this.boxTotales("NETO A RECIBIR", this.lblTotalBoleta.getText(), tableWidth, 15.0, tableWidth/2)
        );

        vboxTables.getChildren().add(acpTableFooter);

        //FIRMAS

        Line lineaFirma = new Line(width - 140,height-30,width,height-30);
        Label lblFirma = new Label("Recibí conforme");
        lblFirma.setLayoutX(width-95);
        lblFirma.setLayoutY(height - 25);


        anchorPane.getChildren().addAll(lblTitle, lblZone, lblUbication, lblRuc, pane, paneSueldo, vboxTables, lineaFirma, lblFirma);

        return anchorPane;

    }

    private VBox infoBoxBoleta(String title, String content, Double xPos, Double yPos){
        VBox box = new VBox();
        Label lblTituloCod = new Label(title);
        lblTituloCod.setStyle("-fx-font-weight: bold");
        Label lblDataCod = new Label(content);
        AnchorPane.setTopAnchor(box, yPos);
        AnchorPane.setLeftAnchor(box, xPos);

        box.getChildren().addAll(lblTituloCod, lblDataCod);
        return box;
    }

    private HBox boxTotales(String title, String total, Double xPos, Double yPos, Double width){

        HBox box = new HBox();
        Label lblTituloCod = new Label(title);
        lblTituloCod.setPrefWidth(width);
        lblTituloCod.setStyle("-fx-font-weight: bold");
        Label lblTotal = new Label(total);
        lblTotal.setPrefWidth(width);
        AnchorPane.setTopAnchor(box, yPos);
        AnchorPane.setLeftAnchor(box, xPos);

        box.getChildren().addAll(lblTituloCod, lblTotal);
        return box;

    }
}
