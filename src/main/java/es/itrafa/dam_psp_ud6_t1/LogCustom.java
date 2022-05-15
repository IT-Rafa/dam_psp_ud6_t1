package es.itrafa.dam_psp_ud6_t1;

import java.io.IOException;
import java.util.logging.FileHandler;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;

/**
 *
 * @author it-ra
 */
public class LogCustom {
    //ATTRIBUTES
    static private final String PATHLOGFILE = "LOGFILE.log";
    static private final Logger logger;
    static private FileHandler fileTxt;
    static private SimpleFormatter formatterTxt;

    // init static var logger
    static {
        logger = Logger.getLogger(Logger.GLOBAL_LOGGER_NAME);
    }

    /**
     * Configure log
     */
    static public void setup() {

        try {
            // 1$ = Date. A ZoneDateTime object
            // 2$ = source. String with caller or logger's name
            // 3$ = logger's name
            // 4$ = the log level
            // 5$ = the log message
            // 6$ = thrown Associated with the log and its backtrace.
            System.setProperty("java.util.logging.SimpleFormatter.format",
                    "[%4$-11s] [%1$tF %1$tT] %2$s:%n  MSG: %5$s%n");

            // set loggers to show upon level
            logger.setLevel(Level.INFO);
            
            // Create output log to file (add parameter true to append logs)
            fileTxt = new FileHandler(PATHLOGFILE);

            // create format (use default; changed with SystemProperty
            formatterTxt = new SimpleFormatter();
            
            // assign format to new file output
            fileTxt.setFormatter(formatterTxt);
            
            // Add output log to actives
            logger.addHandler(fileTxt);
            
            // Remove console hangler - no log in console
            logger.setUseParentHandlers(false);


        } catch (IOException | SecurityException ex) {
            Logger.getLogger(LogCustom.class.getName()).log(Level.SEVERE, null, ex);

        }

    }

}
