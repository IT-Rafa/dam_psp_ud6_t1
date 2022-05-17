package es.itrafa.dam_psp_ud6_t1;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

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
     *
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
     * Constructor. Prepara lista usuarios y añade usuario admin para poder
     * acceder la primera vez
     */
    public DataValidation() {
        this.userList = new ArrayList<>();
        readUsersFromFile();
    }

    /**
     * Pide y comprueba datos usuario. Muestra menú según tipo usuario.
     *
     */
    public void userAccess() {
        boolean retry = false;
        LOG.info("Intento Acceso");
        System.out.println("ACCESO USUARIO:");

        String name = readLine("introduce nombre:");
        if (name.isEmpty() || name.isBlank()) {
            System.out.println("No se introdujo usuario. Reintentar");
            name = readLine("introduce nombre:");
        }
        String password = readPassword("introduce contraseña:");
        if (password != null) {
            User u = checkUser(name, password);
            if (u != null) {
                LOG.info(String.format("Acceso concedido al usuario %s", u.getName()));
                if (u.getType() == UserType.ADMIN) {
                    String op = null;
                    while (op == null) {
                        op = readLine("%s", "¿Desea acceder como administrador? (s/n):");
                        if (op.equalsIgnoreCase("s")) {
                            showAdminMenu(u);

                        } else if (op.equalsIgnoreCase("n")) {
                            showStandardMenu(u);

                        } else {
                            System.out.println("Opción no válida. Reintentar");
                            op = null;
                        }
                    }

                } else {
                    showStandardMenu(u);
                }
            } else {
                System.out.println("Acceso denegado: Usuario o contraseña no coincide");
                LOG.info("Acceso denegado: Usuario o contraseña no coincide");

            }
        }

        while (retry == false) {
            String op = readLine("%s", "¿Desea volver a iniciar sesión? (s/n):");
            if (op.equalsIgnoreCase("s")) {
                userAccess();
                retry = true;

            } else if (op.equalsIgnoreCase("n")) {
                return;
            } else {
                System.out.println("Opción no válida. Reintentar");
            }
        }
    }

    private void showMenu(User u, boolean adminMode) {
        boolean endSession = false;
        while (!endSession) {
            if (adminMode) {
                if (showAdminMenu(u)) {
                    if (readLine("%s", "¿Desea seguir ejecutando tareas? (s/n):").equalsIgnoreCase("s")) {
                        endSession = showAdminMenu(u);
                    }
                }
            } else {
                if (showStandardMenu(u)) {
                    if (readLine("%s", "¿Desea seguir ejecutando tareas? (s/n):").equalsIgnoreCase("s")) {
                        endSession = showStandardMenu(u);
                    }
                }
            }

            LOG.info(String.format("Fin Sesión usuario %s", u.getName()));

        }
    }

    /**
     * Muestra y gestiona las opciones del usuario administrador
     *
     * @param u Usuario estándar
     */
    private boolean showAdminMenu(User u) {
        System.out.println("MENU ADMIN");
        System.out.println("----------");
        System.out.println(" 1. Añadir Usuario");
        System.out.println(" 2. Cambiar Contraseña Usuario");
        System.out.println(" 3. Eliminar Usuario");
        System.out.println(" 0. Fin sesión");

        String opSt = readLine("%n%s", "Introduce opción válida:");
        int op = Integer.valueOf(opSt);
        if (op >= 0 && op <= 3) {
            switch (op) {
                case 0:
                    LOG.info(String.format("Fin Sesión usuario %s", u.getName()));
                    return true;

                case 1:
                    LOG.info(String.format("Usuario %s añade usuario", u.getName()));
                    createUser(u);
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
        return false;

    }

    /**
     * Muestra y gestiona las opciones del menú del usuario estándar
     *
     * @param u Usuario estándar
     */
    private boolean showStandardMenu(User u) {
        System.out.println("MENU USUARIO");
        System.out.println("----------");
        System.out.println(" 1. Comprobar matrícula");
        System.out.println(" 2. Mostrar contenido fichero");
        System.out.println(" 3. Validar frase con expresión regular");
        System.out.println(" 4. Validar usuario Twiter");
        System.out.println(" 5. Cambiar tu contraseña");
        System.out.println(" 0. Fin sesión");

        String opSt = readLine("%n%s", "Introduce opción válida:");
        int op = Integer.valueOf(opSt);
        if (op >= 0 && op <= 5) {
            switch (op) {
                case 0:
                    LOG.info(String.format("Fin Sesión usuario %s", u.getName()));
                    return true;
                case 1:
                    LOG.info(String.format("Usuario %s comprueba matrícula", u.getName()));
                    checkSpanishPlate();
                    break;
                case 2:
                    LOG.info(String.format("Usuario %s Ve fichero", u.getName()));
                    readTxtFile();
                    break;
                case 3:
                    LOG.info(String.format("Usuario %s Valida frase con expresión regular", u.getName()));
                    checkRegExWithTxt();
                    break;
                case 4:
                    LOG.info(String.format("Usuario %s Valida usuario Twiter", u.getName()));
                    checkTwiterUser();
                    break;
                case 5:
                    LOG.info(String.format("Usuario %s cambiar su contraseña", u.getName()));
                    changePassword(u);
                    break;
                default:
                    break;
            }
        } else {
            if (readLine("%s", "Opción no válida (¿Reintentar? (s/n):").equalsIgnoreCase("s")) {
                showAdminMenu(u);
            }
        }
        return false;

    }

    /**
     * Pide al usuario un texto y comprueba que coincida con uno de los tipos de
     * matrículas españolas.
     *
     * https://itv.com.es/tipos-de-matriculas-espana
     * https://www.autonocion.com/historia-y-tipos-de-matriculas-espana/
     * https://matriculasdelmundo.com/matricula-espana-provincias.php
     * https://matriculasdelmundo.com/espana.html
     *
     * <ul>
     *
     * <li>Ordinaria(1900):1-3 Letras(provincia), guion opcional, entre 1 y 6
     * dígitos (doble numeración¿?)</li>
     *
     * <li>Ordinaria(1907):1-3 Letras(provincia), guion opcional, entre 1 y 6
     * dígitos</li>
     * <li>Ordinaria(1908):1-2 Letras(provincia), guion opcional, entre 1 y 6
     * dígitos. Nuevas siglas</li>
     * <li>Ordinaria(1926):1-2 Letras(provincia), guion opcional, entre 1 y 6
     * dígitos. Nuevas siglas</li>
     *
     * <li>Ordinaria(1971):Letra(s) provincia, guion opcional, cuatro dígitos,
     * guión opcional </li>
     *
     * <li>Ordinaria(2000): cuatro dígitos, guion opcional y tres letras
     * consonantes, a excepción de la Ñ y Q.</li>
     * <li></li>
     * </ul>
     */
    private void checkSpanishPlate() {
        String plate = readLine("Introduzca matrícula:");
        Pattern p_ord1907 = Pattern.compile("@[0-9a-zA-Z-_]");

        Matcher mat = p_ord1907.matcher(plate);
        // patron actual: ^[0-9]{1,4}(?!.*(LL|CH))[BCDFGHJKLMNPRSTVWXYZ]{3}
        if (mat.find()) {
            System.out.printf("%s fue válida entre 1907 y 1926", plate);
        } else {
            System.out.printf("%s no es un usuario de Twiter válido", plate);
        }
    }

    /**
     * Pide al usuario un texto y comprueba que coincida con el nombre coincida
     * con el formato 8 caracteres + . + 3 caracteres. Si existe un archivo con
     * el mismo nombre, lo muestra
     *
     */
    private void readTxtFile() {
        String filePath = readLine("Introduzca la ruta del archivo(ruta actual=.):");
        Pattern pat = Pattern.compile("[0-9a-zA-Z]{8}.[0-9a-zA-Z]{3}");
        Matcher mat = pat.matcher(filePath);

        if (mat.find()) {
            Path path = Paths.get(filePath);

            if (path.toFile().isFile()) {
                try (BufferedReader reader = Files.newBufferedReader(path, Charset.forName("UTF-8"))) {

                    String currentLine;
                    while ((currentLine = reader.readLine()) != null) {//while there is content on the current line
                        System.out.println(currentLine); // print the current line
                    }
                } catch (IOException ex) {
                    ex.printStackTrace(); //handle an exception here
                }
            } else {

                System.out.printf(path + "no existe");
            }
        }
    }

    /**
     * Pide al usuario un texto y una expresión regular y comprueba si encajan.
     *
     */
    private void checkRegExWithTxt() {
        String txt = readLine("Introduzca el texto a comprobar:");
        String p = readLine("Introduzca la expresión regular:");

        Pattern pat = Pattern.compile(p);
        Matcher mat = pat.matcher(txt);

        if (mat.find()) {
            System.out.printf("El texto %s coincide con el patrón %s%n", txt, p);
        } else {
            System.out.printf("El texto %s no coincide con el patrón %s%n", txt, p);
        }
    }

    /**
     * Pide al usuario un texto y comprueba que coincida con el formato de un
     * usuario de Twiter.
     * <p>
     * Usuario Twiter: empieza por @ y puede contener letras mayusculas y
     * minusculas, numeros, guiones y guiones bajos</p>
     */
    private void checkTwiterUser() {
        String twiterUser = readLine("Introduzca el usuario de Twiter:");
        Pattern pat = Pattern.compile("@[0-9a-zA-Z-_]");
        Matcher mat = pat.matcher(twiterUser);

        if (mat.find()) {
            System.out.printf("%s es un usuario de Twiter válido", twiterUser);
        } else {
            System.out.printf("%s no es un usuario de Twiter válido", twiterUser);
        }
    }

    private void changePassword(User user) {

        for (User u : userList) {
            if (u.equals(user)) {
                String pw = readLine("Introduzca la nueva contraseña:");
                u.setPassword(pw);

                System.out.println("Su contraseña ha cambiado");
                LOG.info(String.format("Usuario %s cambió su contraseña", u.getName()));
                return;
            }

            System.out.println("Error al cambiar la contraseña. Consulte administrador");
            LOG.severe("Usuario %s, con acceso concedido no se localiza para cambiar contraseña");
        }

    }

    private void createUser(User admin) {

    }

    private User checkUser(String name, String password) {
        for (User u : userList) {
            if (u.getName().equals(name)
                    && u.getPassword().equals(password)) {
                return u;
            }
        }
        return null;
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
                "Al introducir la contraseña será visible. ");

        while (true) {
            String op = readLine("%s", "¿Continuar? (s/n):");
            if (op.equalsIgnoreCase("s")) {
                return this.readLine(format, args);

            } else if (op.equalsIgnoreCase("n")) {
                LOG.info("Acceso cancelado por visibilidad contraseña");
                System.out.println("Acceso cancelado.");
                return null;
            } else {
                System.out.println("Opción no válida. s para pedir contraseña. n para cancelar");
            }
        }

    }

    private void readUsersFromFile() {
        try {
            String fileName = "USERS.txt";
            Path filePath = Paths.get(fileName);
            if (!Files.exists(filePath)) {
                LOG.warning(String.format(
                        "Archivo de usuarios no existe. Creamos archivo %s, args)",
                        fileName));
                Files.createFile(filePath);

            }

            if (!Files.isRegularFile(filePath) && !Files.isReadable(filePath)) {
                // File is not a readable file
            }

            BufferedReader br = new BufferedReader(new FileReader("path_to_some_file"));
            if (br.readLine() == null) {
                System.out.println("No errors, and file empty");
            }
        } catch (IOException ex) {
            Logger.getLogger(DataValidation.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
