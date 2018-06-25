package Clases;

public class UsuarioEnBajaException extends RuntimeException {
    public UsuarioEnBajaException(String mensaje){
        super(mensaje);
    }
}