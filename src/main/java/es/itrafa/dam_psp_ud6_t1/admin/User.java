package es.itrafa.dam_psp_ud6_t1.admin;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 *
 * @author it-ra
 */
public class User implements Comparable<User>{

    // ATTRIBUTES
    private String name;
    private String password;
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

    // CONSTRUCTOR
    User(String name, String password) {
        this.name = name;
        this.password = password;
        Pattern pat = null;
        Matcher mat = null;

        pat = Pattern.compile("[0-9]{8}-[a-zA-Z]");
        mat = pat.matcher(name);
        
    }

    @Override
    public int compareTo(User o) {
        return name.compareTo(o.getName());
    }
}
