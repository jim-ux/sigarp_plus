package pe.sunarp.sigarpplus.entities;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class Trabajador {

    private String codTrab;
    private String apPaterno;
    private String apMaterno;
    private String nombres;
    private String documento;
    private String fechaIngreso;
    private String fechaCese;
    private String plaza;
    private String cargoLab;
    private String nomAfp;
    private String numeroAfp;

}
