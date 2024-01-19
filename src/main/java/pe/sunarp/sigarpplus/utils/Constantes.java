package pe.sunarp.sigarpplus.utils;

import java.util.HashMap;
import java.util.Map;

public class Constantes {



    public static final Map<String, String> fileNames = new HashMap<>(){
        {
            put("trab", "SPMAEP.DBF");
            put("montos", "SPGENER.DBF");
            put("bancos", "SPBANCO.DBF");
        }
    };

    public static final Map<String, String> listaConceptos = new HashMap<>(){

        {

            put("1", "PAGO X DIAS");
            put("2", "NO COBRA REMUN.");
            put("101", "BASICA DEL EMPL");
            put("102", "BASICA DEL EMPL");
            put("103", "ENCARGATURA");
            put("104", "ASIG.FAM.P.FIJO");
            put("105", "ASIG.FAMILIAR");
            put("106", "ASIG.REFRIGERIO");
            put("107", "HORAS EXTRAS");
            put("108", "GRATIFICACION");
            put("109", "ESCOLARIDAD");
            put("110", "ESCOLARIDAD");
            put("111", "ENCARGATURA");
            put("112", "ENCARGATURA");
            put("113", "REM.VACACIONES");
            put("114", "ASIG.FAM.VACAC.");
            put("115", "VACAC.NO GOZADA");
            put("116", "CIERRE PLIEGO");
            put("117", "INDEMNIZACION");
            put("125", "BONIF.EXTRAORD.");
            put("131", "ALIMENTACION");
            put("132", "ASIGNAC. REFRIGER");
            put("140", "INDENMIZ.VACACION");
            put("175", "ALIMENTO");
            put("201", "REINT. BASICA");
            put("202", "REINT. BASICA");
            put("203", "REINT.ENCARGAT.");
            put("205", "REINT.ASIG.FAM.");
            put("208", "REINT.GRATIF.");
            put("209", "ESCOLARIDAD");
            put("210", "ESCOLARIDAD");
            put("217", "INDEMNIZACION");
            put("218", "SENT.JUDIC.GRAT");
            put("219", "SENT.JUDIC.VACA");
            put("220", "COMP.TIEM.SERV.");
            put("221", "COMP.VAC.TRUNCA");
            put("222", "CRED.DEVENGADOS");
            put("223", "ASIG.EXTRAORD.");
            put("224", "REMUNER. 1 MAYO");
            put("226", "ASIG.EXTRAORD.");
            put("227", "H.EXTRA NO ABON");
            put("228", "SENT.JUDIC.CTS");
            put("230", "REINTEGRO GRATI");
            put("234", "SENT.JUD.INTERES");
            put("235", "COMP.VACACIONES");
            put("236", "BONO ECONOMICO");
            put("237", "GRAT.VAC.09-10");
            put("238", "GRAT.VAC.10-11");
            put("239", "GRAT.VAC.11-12");
            put("240", "GRATIF. ANTIGUEDA");
            put("241", "GRAT.VAC.12-13");
            put("242", "SENT.JUD.INTERES");
            put("243", "GRAT.VAC.13-14");
            put("244", "GRAT.VAC.14-15");
            put("280", "BONO PRODUCTIVI");
            put("281", "MOVILIDAD");
            put("290", "ASIG.REFRI.PASI");
            put("291", "ASIG.REFRI.VALE");
            put("295", "ING.4-5TA CATEG");
            put("302", "F.PENS");
            put("303", "SNP");
            put("305", "ESSALUD-VIDA");
            put("306", "ASIG.JUD.");
            put("307", "WAWA WASI");
            put("308", "COOP.PETROPERU");
            put("309", "DEVOLUCION CTA");
            put("310", "AJUSTE REFRIGER");
            put("311", "IMP.5TA CATEGOR");
            put("312", "AJUSTE QUINTA");
            put("313", "DSCTO VISACION");
            put("315", "ASOCIACION PENSIO");
            put("316", "DSCTO. SEMINARIO");
            put("317", "DSCTO. SEMINARIO");
            put("318", "ADELANTO ESCOL");
            put("319", "IMP. PENSIONES");
            put("324", "DSCTO CAFAE");
            put("325", "ADEUDO F.PEN.");
            put("327", "MULTA SUNAT");
            put("328", "EMBARGO REMUN.");
            put("329", "EMBARGO REMUN.");
            put("330", "EMBARGO ATLANTIS");
            put("331", "CAFAE SUNARP");
            put("332", "CAFAE PRESTAMO");
            put("333", "SOMOS CATERING");
            put("381", "APORT.OBLIG.AFP");
            put("382", "COM.VAR.MIXTA");
            put("383", "COM.VAR.AFP");
            put("385", "SEGURO AFP");
            put("401", "DSCTO POR DEUDA");
            put("402", "DSCTO ALIMENTO");
            put("405", "REDIC.CTA ZR.IX");
            put("406", "DSCTO X CAPACIT");
            put("407", "AJUSTE REMUNER.");
            put("409", "DSCTO FOTOCHECK");
            put("410", "FONDO SEPELIO");
            put("411", "COOP. ATLANTIS");
            put("412", "APORTE SITRA EXTR");
            put("415", "CONSUMO REFRIGE");
            put("416", "GRATIFIC.PAGADA");
            put("417", "SITRA IX.ACTIVID");
            put("418", "INASIST.TARDANZ");
            put("419", "ACTIVIDAD SITRA");
            put("420", "PREST.SCOTIABAN");
            put("421", "SITRA - LIBRO");
            put("424", "ESSALUD-VIDA");
            put("425", "DEVOL.DSCTO SNP");
            put("426", "CUOTA SINDICAL");
            put("427", "CUOTA SINTREP");
            put("428", "MAYOR DERECHO");
            put("429", "DONACION");
            put("430", "DONACION F.B.R");
            put("431", "PRESTAMO GNB PERU");
            put("432", "DONACION Y.Y.S");
            put("433", "DONACION M.R.M");
            put("435", "DSCTO D.S. 017");
            put("436", "BCO CONTINENTAL");
            put("437", "CAJA MUN.MAYNAS");
            put("438", "RIMAC SEGUROS");
            put("439", "MAPFRE");
            put("440", "PER.PART./EXC.REF");
            put("441", "COOP. HUANCAYO");
            put("442", "INTERBANK");
            put("443", "CAJA MUN. ICA");
            put("444", "COOP.SN CRISTOB");
            put("445", "COOP. STA MARIA");
            put("446", "COLEG.CONTADORE");
            put("447", "COLEG.ECONOM.");
            put("448", "APORTE EXT.SITRA");
            put("449", "TELEFONO");
            put("450", "DONACION I.E.S");
            put("451", "SUNARP");
            put("452", "CAFAE SUNARP");
            put("453", "COLEGIO DE LIMA");
            put("454", "SEMINARIO SITRA");
            put("455", "COOP.REHABILITA");
            put("456", "CONVENIO");
            put("457", "EPS MAFRE");
            put("458", "CINEPLANET");
            put("459", "TARUMBA");
            put("460", "PREST.FINANCIER");
            put("461", "COOPSAP");
            put("462", "RELU SERVICE EIRL");
            put("463", "DSCTO. BEST INT");
            put("470", "DSCTO MOVILIDAD");
            put("471", "DSCTO VIATICOS");
            put("483", "AFP");
            put("489", "SITRA ACTIVIDAD");
            put("490", "PASI L 28501");
            put("491", "VALE ALIMENT.");
            put("499", "** TOPE AFP **");
            put("501", "TOTAL INGRESOS");
            put("502", "TOTAL EGRESOS");
            put("503", "TOTAL NETO");
            put("601", "ESSALUD-PATRONO");
            put("602", "ESSALUD-PATRONO");
            put("603", "ESSALUD (EPS)");
            put("604", "IMP.SOLIDARIDAD");
            put("699", "TOTAL PATRONO");

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
