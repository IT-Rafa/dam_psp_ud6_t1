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

        LOGGER.setLevel(Level.INFO);
        
        LOGGER.severe("severe Log");
        LOGGER.warning("warning Log");
        LOGGER.info("info Log");
        LOGGER.finest("finest Log");
    }
}
