package es.itrafa.dam_psp_ud6_t1;

import es.itrafa.dam_psp_ud6_t1.admin.DataValidation;
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

        DataValidation dv = new DataValidation();
        dv.addUser("admin", "t0d0P0DER0S0","paco", "1234");

    }
}
