package es.itrafa.dam_psp_ud6_t1;

import java.io.IOException;
import java.util.logging.FileHandler;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;

/**
 * Controla los log usados por la aplicación.
 * 
 * @author it-ra
 */
public class LogCustom {
    //ATTRIBUTES
    /**
     * Ruta del archivo con los log
     */
    static private final String PATHLOGFILE = "LOGFILE.log";
    /**
     * Representa el log a formatear usado por el programa
     */
    static private final Logger logger;
    /**
     * Controla la salida hacia el archivo
     */
    static private FileHandler fileTxt;
    /**
     * Define el formato
     */
    static private SimpleFormatter formatterTxt;

    // init static var logger
    static {
        logger = Logger.getLogger(Logger.GLOBAL_LOGGER_NAME);
    }

    /**
     * Configura formato y forma de salida de los log, incluido el archivo.
     */
    static public void setup() {

        try {
            // Definimos formato global de salida
            // 1$ = Date. A ZoneDateTime object
            // 2$ = source. String with caller or logger's name
            // 3$ = logger's name
            // 4$ = the log level
            // 5$ = the log message
            // 6$ = thrown Associated with the log and its backtrace.
            System.setProperty("java.util.logging.SimpleFormatter.format",
                    "[%4$-11s] [%1$tF %1$tT] %2$s:%n  MSG: %5$s%n");

            // Indicamos precisión de los log
            logger.setLevel(Level.INFO);
            
            // Creamos manejador para salida por fichero
            fileTxt = new FileHandler(PATHLOGFILE);

            // Preparamos formato (usando el formato ya creado)
            formatterTxt = new SimpleFormatter();
            
            // Indicamos el manejador para salida por fichero use el formato indicado
            fileTxt.setFormatter(formatterTxt);
            
            // Añadimos el manejador de salida a fichero al los activos
            logger.addHandler(fileTxt);
            
            // Eliminamos los manejadores heredados (En principio, consola)
            logger.setUseParentHandlers(false);


        } catch (IOException | SecurityException ex) {
            Logger.getLogger(LogCustom.class.getName()).log(Level.SEVERE, null, ex);

        }

    }

}
