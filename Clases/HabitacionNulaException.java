package Clases;
/**
 * Cuando se pide una habitaci�n a la Base de Datos pero la colecci�n est� vac�a
 * y el m�todo devuelve un null en vez de un objeto de tipo Habitacion.
 *
 *
 */
public class HabitacionNulaException extends RuntimeException{
	
	public HabitacionNulaException(String mensaje)
	{
		super(mensaje);
	}

}
