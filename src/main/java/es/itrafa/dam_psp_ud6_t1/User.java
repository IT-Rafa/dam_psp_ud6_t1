package es.itrafa.dam_psp_ud6_t1;

/**
 *
 * @author it-ra
 */
public class User implements Comparable<User>{

    // ATTRIBUTES
    /**
     * Nombre usuario
     */
    private String name;
        /**
     * Contraseña usuario
     */
    private String password;
    /**
     * Tipo de usuario
     * @see UserType
     */
    private UserType type;

    //GETTER/SETTER
    /**
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * @param name the user name to set
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * @return the password
     */
    public String getPassword() {
        return password;
    }

    /**
     * @param password the password to set
     */
    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * @return the type of user
     */
    public UserType getType() {
        return type;
    }
    // METHODS
    // CONSTRUCTOR
    /**
     * Crea un usuario (No válido hasta ser añadido a lista)
     * <p>Controla que la contraseña sea válida(PENDIENTE)</p>
     * 
     * @see DataValidation
     * @see UserType
     * 
     * @param name Nombre usuario
     * @param password Contraseña usuario
     * @param type Tipo usuario 
     */
    User(String name, String password, UserType type) {
        this.name = name;
        this.password = password;
        this.type = type;

    }
    /**
     * Si dos usuarios tienen mismo nombre, se consideran iguales
     * @param o Usuario a comparar
     * 
     * @return 0 si son iguales. Valor negativo si o es menor, y positivo si o es mayor (creo)
     */
    @Override
    public int compareTo(User o) {
        return name.compareTo(o.getName());
    }
}
