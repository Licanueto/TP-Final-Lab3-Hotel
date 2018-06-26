package Clases;
/**
 * 
 * Para el trabajo con ArrayList, cada vez que se realiza una modificaci�n se hace sobre un auxiliar, 
 * que luego es ingresado a la colecci�n que se encuentra en la Base de Datos. Al tratarse de un 
 * ArrayList, necesitamos el �ndice para pisar la versi�n anterior del objeto. La excepci�n es lanzada
 * si la colecci�n est� vac�a y el m�todo nos devuelve como valor un �ndice igual a -1.
 *
 */
public class IndiceIncorrectoException extends RuntimeException{
	
	public IndiceIncorrectoException(String mensaje)
	{
		super(mensaje);
	}

}
