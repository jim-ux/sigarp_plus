package pe.sunarp.sigarpplus.entities;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.util.Date;

@Getter @Setter
public class Trabajador {

    private String codTrab;
    private String apPaterno;
    private String apMaterno;
    private String nombres;
    private String documento;
    private Date fechaIngreso;
    private Date fechaCese;
    private String plaza;
    private String cargoLab;
    private String nomAfp;
    private String numeroAfp;
    private Double sueldoBasico;

}
