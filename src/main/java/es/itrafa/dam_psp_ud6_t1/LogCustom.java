/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
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
            // get the global logger to configure it
            Logger logger = Logger.getLogger(Logger.GLOBAL_LOGGER_NAME);
            
            // suppress the logging output to the console
            
            logger.setLevel(Level.INFO);
            fileTxt = new FileHandler("Logging.txt");
            
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
