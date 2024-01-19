package pe.sunarp.sigarpplus.config;

import javafx.scene.control.Alert;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.text.MessageFormat;

public class Database {

    private final String driver = "com.mysql.cj.jdbc.Driver";
    private final String database = "db_sigarp_plus";
    private final String hostname = "localhost";
    private final String dbPort = "3306";
    private Connection connection;


    public Connection getConnection(){

        try{

            String url = MessageFormat.format("jdbc:mysql://{0}:{1}/{2}", this.hostname, this.dbPort, this.database );
            Class.forName(driver);
            this.connection = DriverManager.getConnection(url, "root", "");

        }catch (SQLException ex){

            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText("Error N° " + ex.getErrorCode());
            alert.setContentText(ex.getMessage());
            alert.show();

        }catch (ClassNotFoundException ex){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setContentText(ex.getMessage());
            alert.show();

            ex.printStackTrace();
        }

        return this.connection;

    }
}
