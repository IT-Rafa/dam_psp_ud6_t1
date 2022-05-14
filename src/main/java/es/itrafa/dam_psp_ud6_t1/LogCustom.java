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

    static private FileHandler fileTxt;
    static private SimpleFormatter formatterTxt;

    static public void setup() {

        try {
            // 1$ = Date. A ZoneDateTime object
            // 2$ = source. String with caller or logger's name
            // 3$ = logger's name
            // 4$ = the log level
            // 5$ = the log message
            // 6$ = thrown Associated with the log and its backtrace.
            System.setProperty("java.util.logging.SimpleFormatter.format",
                    "[%4$-11s] [%1$tF %1$tT] %2$s%n -- %5$s %n");

            // get the global logger to configure it
            Logger logger = Logger.getLogger(Logger.GLOBAL_LOGGER_NAME);

            logger.setLevel(Level.INFO);
            fileTxt = new FileHandler("Logging.txt", true);

            // create a TXT formatter
            formatterTxt = new SimpleFormatter();
            fileTxt.setFormatter(formatterTxt);
            logger.addHandler(fileTxt);

        } catch (IOException ex) {
            Logger.getLogger(LogCustom.class.getName()).log(Level.SEVERE, null, ex);

        } catch (SecurityException ex) {
            Logger.getLogger(LogCustom.class.getName()).log(Level.SEVERE, null, ex);
        }

    }
}
