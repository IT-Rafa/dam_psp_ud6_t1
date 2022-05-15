package es.itrafa.dam_psp_ud6_t1;

import java.io.BufferedReader;
import java.io.Console;
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
    private final static Logger LOG = Logger.getLogger(Logger.GLOBAL_LOGGER_NAME);
    // class
    private final ArrayList<User> userList;

    // CONSTRUCTOR
    /**
     * Constructor. Create admin user.
     */
    public DataValidation() {
        this.userList = new ArrayList<>();

        LOG.info("Creando administrador ");

        User admin = new User("admin", "t0d0P0DER0S0"); //o/0 son ceros
        userList.add(admin);
    }

    /**
     * Check user & password. if ok show menu upon userType
     *
     */
    public void userAccess() {
        LOG.info("Intento Acceso");
        System.out.println("ACCESO USUARIO:");
        String name = readLine("introduce nombre:%n");
        String password = readPassword("introduce contraseña:%n");

        if (password != null) {
            for (User u : userList) {
                if (u.getName().equals(name)
                        && u.getPassword().equals(password)) {
                    LOG.info(String.format("Acceso concedido al usuario %s", u.getName()));
                    if (u.getType() == UserType.ADMIN) {
                        showAdminMenu(u);
                    } else {
                        showStandardMenu(u);
                    }
                    return;
                }
            }
            System.out.println("Acceso denegado: Usuario o contraseña no coincide");
            LOG.info("Acceso denegado: Usuario o contraseña no coincide");
        }
        //password == null -> probably, no console(IDE mode)
        //and user don´t want write password in visible mode

        System.out.println("¿Reintentar?(s/n)");
        if (readLine("%s", "").equalsIgnoreCase("s")) {
            userAccess();
        }

    }

    private void showAdminMenu(User u) {
        System.out.print("MENU ADMIN");
        System.out.print("----------");
        System.out.print(" 1. Añadir Usuario");
        System.out.print(" 2. Cambiar Contraseña Usuario");
        System.out.print(" 3. Eliminar Usuario");
        System.out.print(" 0. SALIR");
        String opSt = readLine("%n%s%n", "Introduce opción válida:");
        int op = Integer.valueOf(opSt);
        if (op >= 0 && op <= 3) {
            switch (op) {
                case 0:
                    return;
                case 1:
                    LOG.info(String.format("Admin %s añade usuario", u.getName()));
                    break;
                case 2:
                    LOG.info(String.format("Admin %s cambiar contraseña a usuario", u.getName()));
                    break;
                case 3:
                    LOG.info(String.format("Admin %s elimina usuario", u.getName()));
                    break;
                default:
                    System.out.println("Opción no válida (¿Reintentar? (s/n):");
                    if (readLine("%s", "").equalsIgnoreCase("s")) {
                        showAdminMenu(u);
                    }
            }
        }

    }

    private void showStandardMenu(User u) {
        System.out.print("MENU USUARIO");
        System.out.print("----------");
        System.out.print(" 1. Comprobar matrícula");
        System.out.print(" 2. Mostrar contenido fichero");
        System.out.print(" 3. Validar frase con expresión regular");
        System.out.print(" 4. Validar usuario Twiter");
        System.out.print(" 5. Cambiar tu contraseña");
        System.out.print(" 0. SALIR");
        String op = readLine("%n%s%n", "Introduce opción válida:");
    }

    private String readLine(String format, Object... args) {
        BufferedReader reader = new BufferedReader(
                new InputStreamReader(System.in));
        try {
            System.out.format(format, args);
            return reader.readLine();

        } catch (IOException ex) {
            LOG.severe("Error entrada/salida");
        }
        return null;
    }

    private String readPassword(String format, Object... args) {
        if (System.console() != null) {
            char[] ps = System.console().readPassword(format, args);
            return String.valueOf(ps);
        }
        System.out.println("No existe acceso a la consola. Probablemente se esté usando un IDE.");
        System.out.println("Al introducir la contraseña será visible. ¿Desea seguir (s/n)?");
        if (readLine("%s", "").equalsIgnoreCase("s")) {
            return this.readLine(format, args);
        } else {
            LOG.info("Acceso cancelado por visibilidad contraseña");
            System.out.println("Acceso cancelado.");
            return null;
        }

    }
}
