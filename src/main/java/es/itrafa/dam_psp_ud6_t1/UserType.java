package es.itrafa.dam_psp_ud6_t1;

/**
 * Tipos de usuarios válidos
 * 
 * @author it-ra
 */
public enum UserType {
    /**
     * Usuario no registrado
     */
    NOVALID,
    /**
     * Usuarios administradores con derecho a modificar usuarios
     */
    ADMIN,
     /**
      * Usuarios estándar con derecho a las opciones normales (enunciado)
      */
     STANDAR
}
