package es.itrafa.dam_psp_ud6_t1.admin;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.logging.Level;
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

    public void userAccess() {
        try {
            int userIndex;
            BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
            System.out.print("Introduce nombre:");

            /*
            System.out.print("Introduce tu contraseña: ");
            String userPw = reader.readLine();
            for (User u : userList) {
                if (u.getName().equals(userName) && u.getPassword().equals(userPw)) {
                    userIndex = userList.indexOf(u);

                    if (userList.get(userIndex).getType() == UserType.ADMIN) {
                        LOGGER.info("Acceso administrador ");

                    } else {
                        LOGGER.info("Acceso usuario estándar ");
                    }
                }
            }
             */
            LOGGER.info("Intento acceso usuario no válido");

        } catch (IOException ex) {
            Logger.getLogger(DataValidation.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void addUser(String admin, String pwAdmin, String user, String pwUser) {

    }
}
