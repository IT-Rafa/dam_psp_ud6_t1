package es.itrafa.dam_psp_ud6_t1;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.logging.Logger;

/**
 * Pide usuario y contraseña y controla el acceso a las opciones de los menús.
 * <p>
 * Se crea un usuario administrador al crear la instancia del objeto</p>
 * <p>
 * El administrador gestiona usuarios y los usuarios estándar hacen las
 * validaciones indicadas en el enunciado.</p>
 *
 * @author it-ra
 */
public class DataValidation {

    // ATTRIBUTES
    /**
     * Log usado para toda la aplicación
     * @see LogCustom
     */
    private final static Logger LOG = Logger.getLogger(Logger.GLOBAL_LOGGER_NAME);
    /**
     * Contiene los usuarios registrados
     */
    private final ArrayList<User> userList;

    // METHODS
    // CONSTRUCTOR
    /**
     * Constructor. Prepara lista usuarios y añade usuario admin para poder acceder la primera vez
     */
    public DataValidation() {
        this.userList = new ArrayList<>();

        LOG.info("Creando administrador ");

        User admin = new User("admin", "NewMagager00", UserType.ADMIN); //o/0 son ceros
        userList.add(admin);
    }

    /**
     * Pide y comprueba datos usuario. Muestra menú según tipo usuario.
     *
     */
    public void userAccess() {
        LOG.info("Intento Acceso");
        System.out.println("ACCESO USUARIO:");
        String name = readLine("introduce nombre:");
        String password = readPassword("introduce contraseña:");

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

        if (readLine("%s", "¿Reintentar?(s/n)").equalsIgnoreCase("s")) {
            userAccess();
        }

    }

    /**
     * Muestra y gestiona las opciones del usuario administrador
     *
     * @param u Usuario estándar
     */
    private void showAdminMenu(User u) {
        System.out.println("MENU ADMIN");
        System.out.println("----------");
        System.out.println(" 1. Añadir Usuario");
        System.out.println(" 2. Cambiar Contraseña Usuario");
        System.out.println(" 3. Eliminar Usuario");
        System.out.println(" 0. SALIR");

        String opSt = readLine("%n%s", "Introduce opción válida:");
        int op = Integer.valueOf(opSt);
        if (op >= 0 && op <= 3) {
            switch (op) {
                case 0:
                    LOG.info(String.format("Fin Sesión usuario %s", u.getName()));
                    if (readLine("%s", "¿Iniciar nueva sesión? (s/n):").equalsIgnoreCase("s")) {
                        userAccess();
                    }
                    return;
                case 1:
                    LOG.info(String.format("Usuario %s añade usuario", u.getName()));
                    break;
                case 2:
                    LOG.info(String.format("Usuario %s cambia contraseña a usuario", u.getName()));
                    break;
                case 3:
                    LOG.info(String.format("Usuario %s elimina usuario", u.getName()));
                    break;
                default:
                    break;

            }
        } else {
            if (readLine("%s", "Opción no válida (¿Reintentar? (s/n):").equalsIgnoreCase("s")) {
                showAdminMenu(u);
            }
        }
        if (readLine("%s", "¿Desea seguir ejecutando tareas? (s/n):").equalsIgnoreCase("s")) {
            showAdminMenu(u);
        } else {
            LOG.info(String.format("Fin Sesión usuario %s", u.getName()));
            if (readLine("%s", "¿Iniciar nueva sesión? (s/n):").equalsIgnoreCase("s")) {
                userAccess();
            }
        }

    }
    /**
     * Muestra y gestiona las opciones del menú del usuario estándar
     *
     * @param u Usuario estándar
     */
    private void showStandardMenu(User u) {
        System.out.print("MENU USUARIO");
        System.out.print("----------");
        System.out.print(" 1. Comprobar matrícula");
        System.out.print(" 2. Mostrar contenido fichero");
        System.out.print(" 3. Validar frase con expresión regular");
        System.out.print(" 4. Validar usuario Twiter");
        System.out.print(" 5. Cambiar tu contraseña");
        System.out.print(" 0. SALIR");
        String op = readLine("%n%s", "Introduce opción válida:");
    }

    /**
     * Controla la peticiones normales hechas al usuario por consola.
     *
     * @param format String con formato donde se indica que se pide
     * @param args Lista argumentos usados en format
     * @return Respuesta del usuario
     */
    private String readLine(String format, Object... args) {
        BufferedReader reader = new BufferedReader(
                new InputStreamReader(System.in));
        try {
            System.out.format(format, args);
            System.out.println();
            return reader.readLine();

        } catch (IOException ex) {
            LOG.severe("Error entrada/salida");
        }
        return null;
    }

    /**
     * Controla la peticiones de contraseña hechas al usuario por consola. Si se
     * tiene consola asignada (Por IDE no se suele asignar) no muestra la
     * respuesta del usuario por pantalla (eco). Si no, Muestra aviso de que se
     * verá la contraseña y permite cancelar la petición
     *
     * @param format String con formato donde se indica que se pide
     * @param args Lista argumentos usados en format
     * @return Respuesta del usuario o null si no se quiere introducir la
     * contraseña de forma visible
     */
    private String readPassword(String format, Object... args) {
        if (System.console() != null) {
            char[] ps = System.console().readPassword(format, args);
            return String.valueOf(ps);
        }
        System.out.format("%s%n%s",
                "No existe acceso a la consola. Probablemente se esté usando un IDE.",
                "Al introducir la contraseña será visible. ¿Desea seguir (s/n)?");

        if (readLine("%s", "")
                .equalsIgnoreCase("s")) {
            return this.readLine(format, args);

        } else {
            LOG.info("Acceso cancelado por visibilidad contraseña");
            System.out.println("Acceso cancelado.");
            return null;
        }

    }
}
