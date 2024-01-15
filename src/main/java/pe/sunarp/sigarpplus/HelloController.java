package pe.sunarp.sigarpplus;
import com.linuxense.javadbf.DBFReader;
import javafx.fxml.FXML;
import javafx.scene.control.Label;

import java.io.File;
import java.io.FileInputStream;
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

        String rutaArchivos = "D:\\jimenez-programas\\ARCHIVOS_REMUN";
        String selectedYear = "1996";
        String selectedMonth = new String("Enero").toUpperCase().substring(0,3);
        String rutaFinal = File.separator + selectedYear + File.separator+ (selectedMonth+selectedYear);

        File file = new File(rutaArchivos.concat(rutaFinal));

        File[] interno = file.listFiles(File::isFile);

        FileInputStream dbfFile = new FileInputStream(interno[12].getAbsolutePath());
        DBFReader reader = new DBFReader(dbfFile);
        Object[] row;


        while((row = reader.nextRecord())!= null){

            String value = row[17] !=null ? row[17].toString() : "Vacío";
            System.out.println(value);
        }








    }


}
