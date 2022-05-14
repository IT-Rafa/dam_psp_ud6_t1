package es.itrafa.dam_psp_ud6_t1;

import java.io.*;
import java.util.logging.*;

/**
 *
 * @author it-ra
 */
public class Init {

    private static final Logger LOG;
    static {
        System.setProperty("java.util.logging.SimpleFormatter.format",
                "[%1$tF %1$tT] [%4$-7s] %5$s %n");
        LOG = Logger.getLogger(Init.class.getName());
                    // Configuro el logger y establezco el formato
           FileHandler fh = new FileHandler("MyLogFile.log", true);
            LOG.addHandler(fh);
            LOG.setLevel(Level.ALL);
    }
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        FileHandler fh;

        try {



            // Añado un mensaje al log   
            LOG.log(Level.WARNING, "Mi primer log");

        } catch (SecurityException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
