package pe.sunarp.sigarpplus.utils;

import java.util.HashMap;
import java.util.Map;

public class Constantes {


    public static final String DATABASE_PATH = "D:\\jimenez-programas\\ARCHIVOS_REMUN";

    public static final Map<String, String> fileNames = new HashMap<>(){
        {
            put("trab", "SPMAEP.DBF");
            put("montos", "SPGENER.DBF");
            put("bancos", "SPBANCO.DBF");
        }
    };

    public static final Map<String, String> listaConceptos = new HashMap<>(){

        {
            put("1","PAGOS POR DIAS");
            put("2","NO COBRA REMUNERACION");
            put("101","BASICA DEL EMPLEADO PERMANENTE");
            put("102","BASICA DEL EMPLEADO PLAZO FIJO");
            put("103","ENCARGATURA");
            put("104","ASIGNACION FAMILIAR PLAZO FIJO");
            put("105","ASIGNACION FAMILIAR");
            put("106","ASIGNACION POR REFRIGERIO");
            put("107","HORAS EXTRAS");
            put("108","GRATIFICACION");
            put("109","ESCOLARIDAD");
            put("110","ESCOLARIDAD LAUDO ARBITRAL");
            put("111","SENTENCIA JUD.LAUDOS ARBITRAL");
            put("112","GASTOS EJERCICIOS ANTERIORES");
            put("113","REMUNERACION POR VACACIONES");
            put("114","ASIGNACION FAMILIAR VACACIONES");
            put("115","VACACIONES NO GOZADAS");
            put("116","CIERRE DE PLIEGO");
            put("117","INDEMNIZACION DESPIDO ARBITRARIO");
            put("125","BONIF.EXTRAORDINARIA LEY 29351");
            put("131","ALIMENTACION EN EFECTIVO");
            put("132","ASIGNACION POR REFRIGERIO");
            put("140","INDEMNIZACION VACACIONES");
            put("175","");
            put("201","REINTEGRO BASICA");
            put("202","REINT.BASICA EMPL. PLAZO FIJO");
            put("203","REINTEGRO POR ENCARGATURA");
            put("205","REINTEGRO ASIGNACION FAMILIAR");
            put("208","REINT. GRATIFICACION");
            put("209","ESCOLARIDAD");
            put("210","ESCOLARIDAD");
            put("217","INDEMNIZACION SENTENCIA JUDICIAL");
            put("218","SENTENCIA JUDICIAL GRATIFICACION");
            put("219","SENTENCIA JUDICIAL VACACIONES");
            put("220","COMP. X TIEMPO DE SERVICIOS");
            put("221","COMP. POR VACACIONES TRUNCAS");
            put("222","CREDITOS DEVENGADOS Y RECONOC.");
            put("223","ASIG.EXTRAORDINARIA DU 031-2008");
            put("224","REMUNERACION 1 DE MAYO");
            put("226","ASIG.EXTRAORDINARIA DU 074-2009");
            put("227","H.EXTRAS NO ABONADAS EN SU MES");
            put("228","SENTENCIA JUDICIAL COMP.TIEMPO SERV");
            put("230","REINTEGRO GRATIFICACION");
            put("234","SENT.JUD.INTERESES REMUNERACIONES");
            put("235","COMPRA VACACIONE.ART.19 DL 713");
            put("236","BONO POR CRECIMIENTO ECONOMICO");
            put("237","GRATIFICACION VACACIONES 2009-2010");
            put("238","GRATIFICACION VACACIONES 2010-2011");
            put("239","GRATIFICACION VACACIONES 2011-2012");
            put("240","GRATIFICACION POR ANTIGUEDAD");
            put("241","GRATIFICACION VACACIONES 2012-2013");
            put("242","SENT.JUD.INTERESES CTS");
            put("243","GRATIFICACION VACACIONES 2013-2014");
            put("280","BONO PRODUCTIVIDAD");
            put("281","MOVILIDAD");
            put("290","ASIG.REFRIGERIO P.A.S.I LEY 28501");
            put("291","ASIG.REFRIGERIO VALES DE ALIMENTACION");
            put("295","INGRESO 4TA-5TA CATEGORIA.SNP");
            put("302","FONDO NACIONAL DE PENSIONES");
            put("303","SISTEMA NACIONAL DE PENSIONES");
            put("305","ESSALUD-VIDA SEGURO DE VIDA");
            put("306","ASIG. JUDI. NOR. Y DEV.");
            put("307","WAWA WASI");
            put("308","COOP.AHORRO CREDITO PETROPERU");
            put("309","DEVOLUCION POR RESPONSABILIDAD");
            put("310","AJUSTE ADELANTO REFRIGERIO");
            put("311","IMPUESTO QUINTA CATEGORIA");
            put("312","AJUSTE DE IMPUESTO DE QUINTA 2012");
            put("313","DSCTO X FALTA DE VISACION");
            put("315","ASOCIACION DE PENSIONISTAS");
            put("316","DSCTO. SEMINARIO");
            put("317","DSCTO. SEMINARIO SANEAMIENTO CATASTRAL");
            put("318","ADELANTO DE ESCOLARIDAD");
            put("319","IMPUESTO A LAS PENSIONES");
            put("324","DESCUENTO CAFAE");
            put("325","ADEUDO FONDO DE PENSIONES");
            put("327","MULTA SUNAT");
            put("328","EMBARGO REMUNERACIONES BCO RIPLEY");
            put("329","EMBARGO REMUNER.DINER CLUB PERU S.A");
            put("330","EMBARGO COOP.AHORRO Y CREDITO ATLANTIS");
            put("331","CAFAE  SUNARP");
            put("332","CAFAE PRESTAMO");
            put("333","LUIS PAVEL PALOMINO QUISPE");
            put("381","APORTE OBLIGATORIO AFP");
            put("382","COMISION VARIABLE MIXTA");
            put("383","COMISION VARIABLE AFP");
            put("385","SEGURO AFP");
            put("401","DESCUENTO POR DEUDA A LA ORLC");
            put("402","DSCTO ALIMENTACION POR COMISION");
            put("405","RENDICCION DE CTA A LA ZONA REG.IX");
            put("406","DSCTO X CAPACITACION PAGADA");
            put("407","AJUSTE POR REMUNERACION PAGADAS");
            put("409","DCTO X PERDIDA/DETERIOR FOTOCK");
            put("410","FONDO DE AYUDA MUTUA X FALLECIMIENT");
            put("411","COOPERATIVA ATLANTIS");
            put("412","APORTE EXTRAORDINARIO SITRA 0.6%");
            put("415","CONSUMO DE REFRIGERIO");
            put("416","GRATIFICACION PAGADA");
            put("417","SITRA IX ACTIVIDAD");
            put("418","INASISTENCIAS Y TARDANZAS");
            put("419","ACTIVIDAD SITRA NO SINDICALIZADOS");
            put("420","PRESTAMO PERSONAL BCO.SCOTIABANK");
            put("421","SITRA IX - LIBRO");
            put("424","ESSALUD VIDA (AJUSTE MAYO 2007)");
            put("425","DEVOLUCION DSCTO SIST.NAC.PENS");
            put("426","CUOTA SINDICAL SITRA ZRIX");
            put("427","SINDICATO SINTREP");
            put("428","RECUPERO DE PAGO POR MAYOR DERECHO");
            put("429","DONACION:");
            put("430","DONACION: FREDI BARAHONA REYNA");
            put("431","PRESTAMO GNB PERU SA");
            put("432","DONACION: YOLANDA YACTAYO SALGADO DE VAR");
            put("435","DSCTO D.S. Nº 017-2002-TR");
            put("436","PRESTAMO BANCO CONTINENTAL");
            put("437","CAJA MUNICIPAL DE MAYNAS");
            put("438","RIMAC SEGUROS Y REASEGUROS");
            put("439","MAPFRE");
            put("440","PERMISOS PARTICULARES/EXCESO REFRIGERIO");
            put("441","COOPERATIVA AHORROS HUANCAYO");
            put("442","INTERBANK");
            put("443","CAJA MUNICIPAL DE ICA");
            put("444","COOPERATIVA SAN CRISTOBAL");
            put("445","COOPERATIVA SANTA MARIA MAGDALENA");
            put("446","COLEGIO DE CONTADORES");
            put("447","COLEGIO DE ECONOMISTAS");
            put("448","APORTE EXTRAORDINARIO SITRA IX");
            put("449","TELEFONO");
            put("450","DONACION INGRID ESTACIO SORIA");
            put("451","SUNARP DSCTO");
            put("452","CAFAE SUNARP (CONVERSATORIO)");
            put("453","COLEGIO DE LIMA (DIPLOMADO)");
            put("454","SEMINARIO SITRA");
            put("455","COOP. LA REHABILITADORA");
            put("456","Z.R. Nº II - SEDE CHICLAYO");
            put("457","EPS MAFRE");
            put("458","CINEPLEX S.A.    RUC 20429683581");
            put("459","TARUMBA-GRUPO DE TEATRO  RUC 20113331181");
            put("460","PRESTAMO BANCO FINANCIERO");
            put("461","COOP.AHORRO Y CRED.SERV.ADUANERO DEL PER");
            put("462","RELU SERVICE EIRL");
            put("463","DSCTO. BEST INTERNACIONAL S.A.C.");
            put("470","AJUSTE MAYOR PAGO DE MOVILIDAD");
            put("471","AJUSTE VIATICOS REFRIGERIO");
            put("483","REAJUSTE AFP");
            put("489","SITRA ACTIVIDAD");
            put("490","ADELANTO PASI L.28501");
            put("491","ADELANTO VALE ALIMENTACION");
            put("499","** TOPE DE SEGURO AFP **");
            put("501","TOTAL INGRESOS");
            put("502","TOTAL EGRESOS");
            put("503","TOTAL NETO");
            put("601","ESSALUD (EX-IPSS)");
            put("602","ESSALUD-PATRONO (EX-IPSS)");
            put("603","ESSALUD (CREDITO EPS)");
            put("604","IMPUESTO SOLIDARIDAD(EX-FONAVI");
            put("699","TOTAL PATRONO");
            put("244","GRATIFICACION VACACIONES 2014-2015");
            put("433","DONACI: MILAGROS EVELINA ROTTIERS MOLINA");

        }

    };

    public static final HashMap<String, String> listaAFP = new HashMap<>(){{
        put("HO",	"HORIZONTE");
        put("PR",	"PROFUTURO");
        put("IN",	"INTEGRA");
        put("UV",	"UNION VIDA");
        put("UN", "UNION");
        put("PM",	"PRIMA AFP");
        put("HA",	"HABITAT");
    }};

}
