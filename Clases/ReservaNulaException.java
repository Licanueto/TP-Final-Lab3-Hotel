package Clases;
/**
 * Cuando necesitamos una determinada recerva y la colección de Base de Datos que contiene las 
 * reservas está vacía. El método encargado de proporcionarnos la reserva nos devuelve un null.
 * Mediante la excepción advertimos que el programa continua pero que no contamos con la reserva
 * solicitada y por lo tanto no se podrá realizar la operación.
 * 
 *
 */
public class ReservaNulaException extends RuntimeException{
	
	public ReservaNulaException(String mensaje)
	{
		super(mensaje);
	}

}
