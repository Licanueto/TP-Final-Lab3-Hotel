package Clases;
/**
 * Cuando necesitamos una determinada recerva y la colecci�n de Base de Datos que contiene las 
 * reservas est� vac�a. El m�todo encargado de proporcionarnos la reserva nos devuelve un null.
 * Mediante la excepci�n advertimos que el programa continua pero que no contamos con la reserva
 * solicitada y por lo tanto no se podr� realizar la operaci�n.
 * 
 *
 */
public class ReservaNulaException extends RuntimeException{
	
	public ReservaNulaException(String mensaje)
	{
		super(mensaje);
	}

}
