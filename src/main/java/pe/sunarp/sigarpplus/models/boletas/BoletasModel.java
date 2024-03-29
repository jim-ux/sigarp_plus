package pe.sunarp.sigarpplus.models.boletas;

import com.linuxense.javadbf.DBFFieldNotFoundException;
import com.linuxense.javadbf.DBFReader;
import com.linuxense.javadbf.DBFRow;
import javafx.scene.control.Alert;
import org.apache.commons.lang3.StringUtils;
import org.controlsfx.dialog.ExceptionDialog;
import pe.sunarp.sigarpplus.entities.Concepto;
import pe.sunarp.sigarpplus.entities.Trabajador;
import pe.sunarp.sigarpplus.utils.Constantes;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class BoletasModel {

    public DBFReader reader;


    public List<Trabajador> getListaBoletas(String filePath){

        List<Trabajador> lista = new ArrayList<>();

        try{

            FileInputStream file = new FileInputStream(filePath);
            this.reader = new DBFReader(file);
            DBFRow row;
            while((row = this.reader.nextRow())!= null) {

                Trabajador trabajador = new Trabajador();
                trabajador.setCodTrab(row.getString("CODTRA"));
                trabajador.setApPaterno(cleanName(row.getString("AP")));
                trabajador.setApMaterno( cleanName(row.getString("AM")));
                trabajador.setNombres( cleanName(row.getString("NOMB")));
                trabajador.setDocumento(row.getString("LIB_ELEC"));
                trabajador.setNomAfp(row.getString("CODAFP"));
                trabajador.setNumeroAfp(cleanName(row.getString("CUIAFP")));
                trabajador.setFechaIngreso(row.getDate("FECHA_ING"));
                trabajador.setFechaCese(row.getDate("FECHA_CESE"));
                trabajador.setSueldoBasico(row.getDouble("BASICO"));
                trabajador.setPlaza("");
                trabajador.setCargoLab(row.getString("CARGO"));
                lista.add(trabajador);

            }

            reader.close();

        }catch(FileNotFoundException ex){

            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText("Archivo no encontrado");
            alert.setContentText("Archivo "+ Constantes.fileNames.get("trab")  + " en la ruta "+filePath);
            alert.show();

        }catch (DBFFieldNotFoundException ex){
            ExceptionDialog dialog = new ExceptionDialog(ex);
            dialog.setHeaderText("Error");
            dialog.setTitle("Error");
            dialog.show();
        }
        return lista;

    }

    public List<Concepto> getDetalleBoleta(String codTrab, String filePath){

        List<Concepto> listaConcept = new ArrayList<>();

        try{

            FileInputStream fileMontosPlanilla = new FileInputStream(filePath);
            DBFReader reader = new DBFReader(fileMontosPlanilla);
            DBFRow row;

            while ((row = reader.nextRow())!= null){

                String codTrabFromFile = row.getString("CODTRA");

                if(codTrab.equals(codTrabFromFile)){

                    Concepto concepto = new Concepto();
                    concepto.setCodCpt(row.getString("CODCPT"));
                    concepto.setNomCpt(Constantes.listaConceptos.get(concepto.getCodCpt()));
                    concepto.setMontoCpt(row.getDouble("IMPORT_G"));

                    listaConcept.add(concepto);

                }
            }

            reader.close();

        }catch (FileNotFoundException ex){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText("Archivo no encontrado");
            alert.setContentText("Archivo "+ Constantes.fileNames.get("trab")  + " en la ruta "+filePath);
            alert.show();
        }

        return listaConcept;

    }

    public String getCuentaBancaria(String codTrab, String filePath){

        String cuenta = "";
        String data1;

        try{

            FileInputStream fileMontosPlanilla = new FileInputStream(filePath);
            DBFReader reader = new DBFReader(fileMontosPlanilla);
            DBFRow row;

            while ((row = reader.nextRow())!= null){

                String codTrabFromFile = row.getString("CODTRA");

                if(codTrab.equals(codTrabFromFile)){

                    data1 = row.getString("AGEN_TRAB");

                    cuenta = this.buildCuentaBanc( data1, row.getString("CTA_TRAB"));

                    break;

                }


            }

            reader.close();

        }catch (FileNotFoundException ex){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText("Archivo no encontrado");
            alert.setContentText("Archivo "+ Constantes.fileNames.get("trab")  + " en la ruta "+filePath);
            alert.show();
        }

        return cuenta;

    }

    public LocalDate getFechaBoleta(String filePath){

        LocalDate fecha = LocalDate.now();

        try{

            FileInputStream filePlanilla = new FileInputStream(filePath);
            DBFReader reader = new DBFReader(filePlanilla);
            DBFRow row = reader.nextRow();
            fecha = this.transformMonthYear(row.getString("CODPLA"));

        }catch (FileNotFoundException ex){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText("Archivo no encontrado");
            alert.setContentText("Archivo "+ Constantes.fileNames.get("trab")  + " en la ruta "+filePath);
            alert.show();
        }
        return fecha;
    }

    private String buildCuentaBanc(String cod, String cuenta){

        String codAgent = StringUtils.leftPad(cod, 3, "0");

        StringBuilder sb = new StringBuilder(cuenta);
        sb.insert(cuenta.length()-1, "-");
        sb.insert(0, codAgent + "-");

        return sb.toString();

    }

    private String cleanName(String rawString){
        //"£" -> ú
        return rawString.replaceAll("¥", "Ñ");


    }

    private LocalDate transformMonthYear(String fecha){

        String year = fecha.substring(0,4);
        String month = fecha.substring(4,6);
        return LocalDate.of(Integer.parseInt(year), Integer.parseInt(month), 1);

    }
}
