package Clases;
public class ClaveIncorrectaException extends RuntimeException {
    public ClaveIncorrectaException(String mensaje){
        super(mensaje);
    }
}
