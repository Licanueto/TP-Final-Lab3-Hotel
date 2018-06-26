package Clases;
/**
 * 
 * Cuando necesitamos un objeto de tipo Pasajero que se encuentra en Base de Datos pero
 * la correspondiente colecci�n est� vac�a y el m�todo encargado de tal operaci�n nos
 * devuelve un null. Es ah� donde se lanza esta excepci�n informando que no estamos
 * contando con ese objeto de tipo Pasajero porque tenemos un valor nulo en su lugar.
 *
 */
public class PasajeroNullException extends RuntimeException {
	
	public PasajeroNullException(String mensaje)
	{
		super(mensaje);
	}

}
