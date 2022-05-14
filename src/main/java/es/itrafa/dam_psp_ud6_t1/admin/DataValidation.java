package es.itrafa.dam_psp_ud6_t1.admin;

import java.util.ArrayList;
import java.util.logging.Logger;

/**
 *
 * @author it-ra
 */
public class DataValidation {
    // ATTRIBUTES
    // log
    private final static Logger LOGGER = Logger.getLogger(Logger.GLOBAL_LOGGER_NAME);
    // class
    private final ArrayList<User> userList;

    // CONSTRUCTOR
    /**
     * Constructor. Create admin user.
     */
    public DataValidation() {
        this.userList = new ArrayList<>();
        LOGGER.info("Creando administrador ");
        User admin = new User("admin", "t0d0P0DER0S0"); //o/0 son ceros
        userList.add(admin);
    }

    protected void addUser(String admin, String pwAdmin, String user, String pwUser) {
        if(userList.contains(admin)){
            
        }
    }
}
