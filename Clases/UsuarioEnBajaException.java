package Clases;
/**
 * 
 * Puede suceder que un usuario sea dado de baja por alg�n motivo importante
 * que el hotel considere. Si se desea realizar una operaci�n con los datos
 * del usuario se lanzar� esta excepci�n advirtiendo que no se puede realizar
 * lo requerido dado que el usuario fue dado de baja.El sistema presenta las
 * posibilidades de darlo de alta nuevamente.
 *
 */
public class UsuarioEnBajaException extends RuntimeException {
    public UsuarioEnBajaException(String mensaje){
        super(mensaje);
    }
}