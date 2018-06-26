package Clases;
/**
 * 
 * Cuando el pasajero llega al hotel con un n�mero de hu�spedes superior a la capacidad
 * que tiene el hotel en el momento dado de la reserva, lanza la excepci�n advirtiendo
 * que esa cantidad sobrepasa la capacidad hotelera.
 *
 */
public class FaltaDisponibilidadException extends RuntimeException{
	
	public FaltaDisponibilidadException(String mensaje)
	{
		super(mensaje);
	}
	
}
