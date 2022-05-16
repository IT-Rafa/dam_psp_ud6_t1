package es.itrafa.dam_psp_ud6_t1;

import java.util.logging.*;

/**
 * Inicia la aplicación
 *
 * @param args the command line arguments
 */
public class Init {

    // ATTRIBUTES
    /**
     * Log usado para toda la aplicación
     *
     * @see LogCustom
     */
    private final static Logger LOGGER = Logger.getLogger(Logger.GLOBAL_LOGGER_NAME);

    // METHODS
    /**
     * Configura logs de todo el programa, prepara datos, permite acceso al
     * usuario y, si es válido, muestra opciones correspondientes.
     *
     * @param args Argumentos linea comandos (no usado)
     */
    public static void main(String[] args) {
        // Configuramos log
        LogCustom.setup();
        LOGGER.info("Inicio ejecución dam_psp_ud5_t1");

        // Se crea entorno con administrador
        DataValidation dv = new DataValidation();

        // Se inicia acceso de usuario
        dv.userAccess();
        LOGGER.info("Fin ejecución dam_psp_ud5_t1");
    }
}
