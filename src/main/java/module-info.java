module pe.sunarp.boletera {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.base;
    requires static lombok;
    requires layout;
    requires com.github.albfernandez.javadbf;
    requires java.logging;
    requires org.apache.commons.lang3;

    opens pe.sunarp.sigarpplus to javafx.fxml;
    exports pe.sunarp.sigarpplus;



    //Controladores


    exports pe.sunarp.sigarpplus.controllers;
    opens pe.sunarp.sigarpplus.controllers to javafx.fxml;

    opens pe.sunarp.sigarpplus.controllers.boletas to javafx.fxml;
    exports pe.sunarp.sigarpplus.controllers.boletas;


    //Entidades
    opens pe.sunarp.sigarpplus.entities to javafx.base;
    exports pe.sunarp.sigarpplus.entities;

}
