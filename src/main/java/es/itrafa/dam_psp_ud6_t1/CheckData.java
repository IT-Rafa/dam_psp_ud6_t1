package es.itrafa.dam_psp_ud6_t1;

import java.beans.PropertyChangeSupport;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 *
 * @author it-ra
 */
class CheckData {
   private ListArray<User> securityList;
   static{
       
   }

    
    private void addUser(String user, String password) {
        
    }

    void signIn() {
        askPassword();
    }

    private void askPassword() {
        String password = null;

        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        try {
            System.out.println("Introduce la contraseña:");
            String input = reader.readLine();
            Pattern patt = Pattern.compile("[0-9]{8}-[a-zA-Z]");
            Matcher check = patt.matcher(password);
            if (check.find()) {
                System.out.println("Correcto!! " + password);
            } else {
                System.out.println("El password esta mal " + password);
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    /**
     * @return the securityList
     */
    public String[][] getSecurityList() {
        return securityList;
    }

    /**
     * @param securityList the securityList to set
     */
    public void setSecurityList(String[][] securityList) {
        java.lang.String[][] oldSecurityList = this.securityList;
        this.securityList = securityList;
        propertyChangeSupport.firePropertyChange(PROP_SECURITYLIST, oldSecurityList, securityList);
    }
    private final transient PropertyChangeSupport propertyChangeSupport = new java.beans.PropertyChangeSupport(this);
    public static final String PROP_SECURITYLIST = "securityList";



}
