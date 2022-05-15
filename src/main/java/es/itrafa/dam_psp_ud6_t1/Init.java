package es.itrafa.dam_psp_ud6_t1;

import java.util.logging.*;

/**
 *
 * @author it-ra
 */
public class Init {

    private final static Logger LOGGER = Logger.getLogger(Logger.GLOBAL_LOGGER_NAME);

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        LogCustom.setup();

        LOGGER.info("Inicio ejecución dam_psp_ud5_t1");

        // Se crea entorno con administrador
        DataValidation dv = new DataValidation();

        // administrador crea nuevo usuario estandar
        dv.userAccess();
        LOGGER.info("Fin ejecución dam_psp_ud5_t1");

    }
}
