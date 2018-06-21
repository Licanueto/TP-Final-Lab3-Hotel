package Clases;

import net.time4j.*;
import net.time4j.range.ChronoInterval;
import net.time4j.range.DateInterval;
import net.time4j.range.IntervalCollection;
import net.time4j.range.ValueInterval;
import net.time4j.CalendarUnit;

import java.util.*;
import java.io.*;
			  
              //////////////////////////////////////////////////////////////////////////
             /*  Se toma como "Disponible" a la ausencia de intervalo en el periodo  */
            //////////////////////////////////////////////////////////////////////////

          //////////////////////////////////////////////////////////////////////////
         /*                 "Disponible" es equivalente a "ocupable"             */
        ////////////////////////////////////////////////////////////////////////// 

/* ideas para exceptions: 	Que al crear una habitaci�n se chequee que no haya una con el mismo n�mero en la base de datos.
 * 							Que no deje poner una fecha de "inicio" posterior a una de "fin".
 * 							Qu� pasa si se carga un producto al frigobar que no exista?
*/
/**
 * Esta clase define los comportamientos b�sicos de la representaci�n de una habitaci�n de hotel promedio.
 * Cabe notar que el objeto generado por esta clase posee otro de la clase Frigobar y que se utilizan librer�as externas para el manejo de fechas e intervalos temporales.
 * 
 * @param numHabitacion Es el n�mero de la habitaci�n dentro del hotel, usualmente de 3 � 4 cifras donde las d�s ultimas determinan el n�mero de habitaci�n en el piso y la o las primeras el n�mero de piso.
 * @param capacidad Determina la cantidad m�xima de personas que la habitaci�n puede albergar.
 * @param tipo Descripci�n del tipo de habitaci�n que se trata.
 * @param precioDiario Es el costo diario por alquilar la habitaci�n.
 * @param frigobar Es un objeto de la clase frigobar que representa al frigobar que cada habitaci�n posee.
 * @param ColecIntervalosDeFechas Es una colecci�n que posee intervalos de fechas en los que la habitaci�n por alguna raz�n no se encuentra disponible para ser ocupada por un huesped.
 * 
 */

@SuppressWarnings("unused")
public class Habitacion implements Comparable<Habitacion>{ 

	private String numHabitacion;
    private byte capacidad;
    private String tipo;
    private double precioDiario;
    private Frigobar frigobar;
    private IntervalCollection<PlainDate> ColecIntervalosDeFechas;
    
    /**
	 * @param numHabitacion Es el n�mero de la habitaci�n dentro del hotel, usualmente de 3 � 4 cifras donde las d�s ultimas determinan el n�mero de habitaci�n en el piso y la o las primeras el n�mero de piso.
	 * @param capacidad Determina la cantidad m�xima de personas que la habitaci�n puede albergar.
	 * @param tipo Descripci�n del tipo de habitaci�n que se trata, puede expresar el nivel de comfort de la misma.
	 * @param precioDiario Es el costo diario por alquilar la habitaci�n.
	 * @return Objeto de tipo Habitacion.
     */
    public Habitacion(String numHabitacion,byte capacidad,String tipo,double precioDiario){
        this.numHabitacion = numHabitacion;
        this.capacidad = capacidad;
        this.tipo = tipo;
        this.precioDiario = precioDiario;
        frigobar = new Frigobar(); //ver el tema de parametros
        ColecIntervalosDeFechas = IntervalCollection.onDateAxis();
    }
    /**
	 * @param capacidad Determina la cantidad m�xima de personas que la habitaci�n puede albergar.
     */
    public void setCapacidad(byte capacidad) {

        this.capacidad = capacidad;
    }
	/**
	 * @param tipo Descripci�n del tipo de habitaci�n que se trata.
	 */
    public void setTipo(String tipo) {

        this.tipo = tipo;
    }
    /**
    * @param precioDiario Es el costo diario por alquilar la habitaci�n.
    */
    public void setPrecioDiario(double precioDiario) {

        this.precioDiario = precioDiario;
    }
    /**
     * @return El n�mero de la habitaci�n dentro del hotel, usualmente de 3 � 4 cifras donde las d�s ultimas determinan el n�mero de habitaci�n en el piso y la o las primeras el n�mero de piso.
     */
    public String getNumHabitacion() {

        return numHabitacion;
    }
    /**
     * @return La cantidad m�xima de personas que la habitaci�n puede albergar.
     */
    public byte getCapacidad() {

        return capacidad;
    }
    /**
     * @return Descripci�n del tipo de habitaci�n que se trata. Puede expresar el nivel de comfort de la misma.
     */
    public String getTipo() {

        return tipo;
    }
    /**
     * @return Costo diario por alquilar la habitaci�n.
	 */
    public double getPrecioDiario() {

        return precioDiario;
    }
    /**
     * @return Objeto de tipo Frigobar.
     */
    public Frigobar getFrigobar(){
	    return frigobar;
    }
    /**
     * @return Estado de la habitaci�n para la fecha actual.
     */
    public String getEstado() {
    	return getEstado(PlainDate.nowInSystemTime());
    }
    /**
     * @param fecha Fecha para la cual se desea saber el estado
     * @return Estado de la habitaci�n para la fecha ingresada.
     */
    public String getEstado(PlainDate fecha) {
    	if(!isDisponible(fecha)) {  // Si la coleccion no tiene intervalo con esa fecha implica disponibilidad, sino se itera la coleccion hasta dar con el intervalo que posea la fecha recibida por parametro
    		Iterator<ChronoInterval<PlainDate>> iterador = ColecIntervalosDeFechas.iterator();
    		while(iterador.hasNext()) {
    			@SuppressWarnings("unchecked")
				ValueInterval<PlainDate, DateInterval, String> intervaloCasteado = (ValueInterval<PlainDate, DateInterval, String>)iterador;
    			DateInterval intervaloCasteadoAgain = intervaloCasteado.getBoundaries();
    			if(intervaloCasteadoAgain.contains(fecha)) {
    				return intervaloCasteado.getValue();
    			}
        	}
    		System.out.println("Ac� no deber�a llegar nunca, si llega es que hay alg�n problema en getEstado(fecha) de la clase Habitaci�n");
    	}
    	return "Disponible";
    }
    
    /**
     * Cambia el estado en el que la habitaci�n se encuentra el d�a actual, este es la raz�n por la cual la habitaci�n no se encuentra libre.
     * Si se quiere utilizar un estado predefinido se debe utilizar el m�todo deshabilitar().
     * @param estado Descripci�n del estado en el que se encuentra la habitaci�n hoy.
     */
    public void setEstado(String estado) {
    	setEstado(PlainDate.nowInSystemTime(), PlainDate.nowInSystemTime(), estado);
    }
    /**
     * Cambia el estado en el que la habitaci�n se encuentra en determinada fecha, este es la raz�n por la cual la habitaci�n no se encuentra libre.
     * ADVERTENCIA: Con este m�todo se "pisa" el estado anterior, debe realizarse un chequeo previo si dada la l�gica del programa este fuera un caso factible.
     * @param fecha Fecha para la cual se desea modificar el estado.
     * @param estado Descripci�n del estado en el que se encuentra la habitaci�n en la fecha.
     */
    public void setEstado(PlainDate fecha, String estado) {
    	setEstado(fecha, fecha, estado);
    }
    /**
     * Cambia el estado en el que la habitaci�n se encuentra en un determinado per�odo, este (el estado) es la raz�n por la cual la habitaci�n no se encuentra libre.
     * ADVERTENCIA: Con este m�todo se pueden "pisar" otros intervalos inadvertidamente, debe realizarse un chequeo previo si dada la l�gica del programa este fuera un caso factible.
     * @param inicio Fecha de inicio del intervalo para el cual aplicar� el estado.
     * @param fin Fecha de finalizaci�n del intervalo para el cual aplicar� el estado.
     * @param estado Descripci�n del estado en el que se encuentra la habitaci�n en el per�odo.
     */
    public void setEstado(PlainDate inicio, PlainDate fin, String estado) {
    	DateInterval intervaloSinValor = DateInterval.between(inicio, fin); 
    	ValueInterval<PlainDate, DateInterval, String> intervalo = intervaloSinValor.withValue(estado); 
    	ColecIntervalosDeFechas = ColecIntervalosDeFechas.plus(intervalo); 
    }
    /**
     * Deshabilita una habitaci�n el d�a de la fecha pasando su estado a ser el de "Deshabilitada"
     */
    public void deshabilitar() {
    	char razon = 'z';
    	deshabilitar(razon);
    }
    /**
     * Deshabilita una habitaci�n el d�a de la fecha actual pasando su estado a ser el determinado por la raz�n ingresada.
     * @param razon Raz�n por la cual la habitaci�n se est� deshabilitando.  L-Limpieza D-Desinfeccion R-Reparaci�n 
     * 	 Nota: 	Cualquier otro caracter pondr� a la habitaci�n en "Deshabilitada".
     * 			Si desea especificar alg�n caso en particular no contemplado por este m�todo utilize setEstado().
     */
    public void deshabilitar(char razon) {
    	switch(razon) {
    		case 'r' : 
    		case 'R' : setEstado("Deshabilitada: Reparaci�n");
						break;
    		case 'd' : 
    		case 'D' : setEstado("Deshabilitada: Desinfecci�n");
						break;
    		case 'l' : 
    		case 'L' : setEstado("Deshabilitada: Limpieza");
						break;
    		default: setEstado("Deshabilitada");
    		}
    }
    /**
     * Deshabilita una habitaci�n el d�a de la fecha recibida por par�metro pasando su estado a ser el de "Deshabilitada".
     * @param fecha Fecha para la cual se desea deshabilitar la habitaci�n.
     */
    public void deshabilitar(PlainDate fecha) {
    	setEstado("Deshabilitada");
    }
    /**
     * Deshabilita una habitaci�n el d�a de la fecha recibida por par�metro pasando su estado a ser el determinado por la raz�n ingresada.
     * @param fecha Fecha para la cual se desea deshabilitar la habitaci�n.
     * @param razon Raz�n por la cual la habitaci�n se est� deshabilitando.  L-Limpieza D-Desinfeccion R-Reparaci�n 
     * 	 Nota: 	Cualquier otro caracter pondr� a la habitaci�n en "Deshabilitada".
     * 			Si desea especificar alg�n caso en particular no contemplado por este m�todo utilize setEstado().
     */    
    public void deshabilitar(PlainDate fecha, char razon) {
    	switch(razon) {
		case 'r' : 
		case 'R' : setEstado(fecha,"Deshabilitada: Reparaci�n");
					break;
		case 'd' : 
		case 'D' : setEstado(fecha,"Deshabilitada: Desinfecci�n");
					break;
		case 'l' : 
		case 'L' : setEstado(fecha,"Deshabilitada: Limpieza");
					break;
		default: setEstado(fecha,"Deshabilitada");
    	}
    }
    /**
     * Deshabilita una habitaci�n en el intervalo entre las fechas recibidas por par�metro pasando su estado a ser el de "Deshabilitada".
     * @param inicio Fecha desde la cual se dehabilitar� la habitaci�n.
     * @param fin Fecha hasta la cual se dehabilitar� la habitaci�n.
     */
    public void deshabilitar(PlainDate inicio, PlainDate fin) {
    	setEstado(inicio,fin,"Deshabilitada");
    }
    /**
     * Deshabilita una habitaci�n en el intervalo entre las fechas recibidas por par�metro pasando su estado a ser el determinado por la raz�n ingresada.
     * @param inicio Fecha desde la cual se dehabilitar� la habitaci�n.
     * @param fin Fecha hasta la cual se dehabilitar� la habitaci�n.
     * @param razon Raz�n por la cual la habitaci�n se est� deshabilitando.  L-Limpieza D-Desinfeccion R-Reparaci�n 
     * 	 Nota: 	Cualquier otro caracter pondr� a la habitaci�n en "Deshabilitada".
     * 			Si desea especificar alg�n caso en particular no contemplado por este m�todo utilize setEstado().
     */
    public void deshabilitar(PlainDate inicio, PlainDate fin, char razon) {
    	switch(razon) {
			case 'r' : 
			case 'R' : setEstado(inicio,fin,"Deshabilitada: Reparación");
						break;
			case 'd' : 
			case 'D' : setEstado(inicio,fin,"Deshabilitada: Desinfección");
						break;
			case 'l' : 
			case 'L' : setEstado(inicio,fin,"Deshabilitada: Limpieza");
						break;
			default: setEstado(inicio,fin,"Deshabilitada");
		}
    }
    /**
     * Deshabilita una habitaci�n de manera indefinida a partir de la fecha recibida por par�metro pasando su estado a ser el determinado por la raz�n ingresada.
     * ADVERTENCIA: Este m�todo "Pisar�" cualquier intervalo posterior a la fecha recibida quedando a cargo de quien decida usar el m�todo la responsabilidad de chequear que esto no produzca una p�rdida de informacion ni atente contra el buen funcionamiento del programa.
     * @param fechaAPartirDeLaCual Fecha a partir de la cual desea deshabilitar la habitaci�n.
     * @param razon Raz�n por la cual la habitaci�n se est� deshabilitando.  L-Limpieza D-Desinfeccion R-Reparaci�n 
     * 	 Nota: 	Cualquier otro caracter pondr� a la habitaci�n en "Deshabilitada".
     * 			Si desea especificar alg�n caso en particular no contemplado por este m�todo utilize setEstado().
     */
    public void deshabilitarIndefinidamente(PlainDate fechaAPartirDeLaCual,char razon){
    	DateInterval intervaloSinValor = DateInterval.since(fechaAPartirDeLaCual); // Crea un intervalo común "Sin valor"
    	ValueInterval<PlainDate, DateInterval, String> intervalo;	// Crea un intervalo con valor pero no lo inicia
    	switch(razon) {	// Inicia el intervalo con valor usando el intervalo sin valor + el valor del String segun el case (razón)
		case 'r' : 
		case 'R' : intervalo = intervaloSinValor.withValue("Deshabilitada: Reparación");
					break;
		case 'd' : 
		case 'D' : intervalo = intervaloSinValor.withValue("Deshabilitada: Desinfección");
					break;
		case 'l' : 
		case 'L' : intervalo = intervaloSinValor.withValue("Deshabilitada: Limpieza");
					break;
		default: intervalo = intervaloSinValor.withValue("Deshabilitada");
		}
    	ColecIntervalosDeFechas = ColecIntervalosDeFechas.plus(intervalo);	// Agrega el intervalo a la colección;
    }
    
    	
    /*  // En este metodo estoy comparando los Strings para ver si son ambos "Disponible" cuando en realidad es mejor no usar la palabra Disponible at all y tomar la ausencia de intervalo como disponibilidad
    public boolean isDisponible(PlainDate fecha) { // hay que hacer otro isDisponible para dos fechas (inicio y fin)
    	if (ColecIntervalosDeFechas.encloses(fecha)){ // Si la colección posée en sí misma la fecha, se la recorre
    		Iterator<ChronoInterval<PlainDate>> iterador = ColecIntervalosDeFechas.iterator();
    		while(iterador.hasNext()) {
    			ValueInterval<PlainDate, DateInterval,String> intervalo = (ValueInterval<PlainDate, DateInterval, String>)iterador; //se castea el iterador a ValueInterval ya que la coleccion no sabe qué tipo de dato contiene
	    		if(intervalo.contains(fecha)) { // Si el iterador se encuentra en el intervalo que posee la fecha
	    			if(intervalo.getValue().equals("Disponible") || intervalo.getValue().equals("disponible")) {
	    				return true;
	    			}else return false;
	    		}  		
    		}
	    	System.out.println("La funcion isDisponible() de Habitacion no debería llegar acá, se retorna false por si acaso");return false;
	    	// Esto nunca se deberia ejecutar ya que implica que encloses diga que el dia se encuentra en la coleccion pero que al recorrerla el intervalo no se encuentra (no entra al if)
    	}else return true; // El dia de hoy no se encuentra en ningun intervalo
    }*/
    /**
     * Determina si en el d�a de la fecha actual la habitaci�n se encuentra disponible para ser ocupada por un pasajero.
     * @return TRUE si est� disponible, FALSE si no lo est�.
     */
    public boolean isDisponible() { // Disponible es "Disponible para ser ocupada"
    	return isDisponible(PlainDate.nowInSystemTime());
    }    
    /**
     * Determina si en el d�a de la fecha recibida la habitaci�n se encuentra disponible para ser ocupada por un pasajero.
     * @param fecha Fecha por la cual se est� consultando la disponibilidad.
     * @return TRUE si est� disponible, FALSE si no lo est�.
     */
    public boolean isDisponible (PlainDate fecha) {
    	return isDisponible(fecha, fecha);
    }
    /**
     * Determina si en la totalidad del intervalo recibido la habitaci�n se encuentra disponible para ser ocupada por un pasajero.
     * @param inicio Fecha desde la cual se est� consultando la disponibilidad.
     * @param fin Fecha hasta la cual se est� consultando la disponibilidad.
     * @return TRUE si est� disponible, FALSE si no lo est�.
     */
    public boolean isDisponible (PlainDate inicio,PlainDate fin) {
    	PlainDate finReal = fin.minus(1,CalendarUnit.DAYS);
    	DateInterval intervaloRecibido = DateInterval.between(inicio, finReal);
    	Iterator<ChronoInterval<PlainDate>> iterador = ColecIntervalosDeFechas.iterator();
    	while(iterador.hasNext()) {
    		@SuppressWarnings("unchecked")
			ValueInterval<PlainDate, DateInterval, String> intervaloCasteado = (ValueInterval<PlainDate, DateInterval, String>)iterador;
    		DateInterval intervaloAComparar = intervaloCasteado.getBoundaries();
    		if(intervaloRecibido.intersects(intervaloAComparar)) {
    			return false;
    		}
    	}
    	return true;
    }
    
    /**
     * Determina si en el d�a de la fecha actual la habitaci�n se encuentra ocupada por un pasajero.
     * @return TRUE si est� ocupada, FALSE si no lo est�.
     */
    public boolean isOcupada() {//provisionalmente.. o no
        return !isDisponible();
    }
    /**
     * Determina si en el d�a de la fecha recibida la habitaci�n se encuentra ocupada por un pasajero.
     * @param fecha Fecha por la cual se est� consultando.
     * @return TRUE si est� ocupada, FALSE si no lo est�.
     */
    public boolean isOcupada(PlainDate fecha) {
    	return !isDisponible(fecha);
    }
    /**
     * Determina si en alg�n d�a del intervalo recibido la habitaci�n se encuentra ocupada por un pasajero.
     * @param inicio Fecha desde la cual se est� consultando.
     * @param fin Fecha hasta la cual se est� consultando.
     * @return TRUE si est� ocupada, FALSE si no lo est�.
     */
    public boolean isOcupada(PlainDate inicio,PlainDate fin) {
    	return !isDisponible(inicio, fin);
    }
    /**
     * Ocupa una habitaci�n para la fecha actual ("hoy" de cuando se ejecute el m�todo).
     */
    public void ocupar(){
        setEstado(PlainDate.nowInSystemTime(),"Ocupada");
    }
    /**
     * Ocupa una habitaci�n para la fecha recibida. Notese que ocupa la habitaci�n solo por ese dia.
     * @param fecha Fecha para la cual se desea ocupar la habitaci�n.
     */
    public void ocupar(PlainDate fecha) {
    	setEstado(fecha,"Ocupada");
    }
    /**
     * Ocupa una habitaci�n durante el intervalo recibido.
     * @param inicio Fecha a partir de la cual se desea ocupar la habitaci�n.
     * @param fin Fecha hasta la cual se desea ocupar la habitaci�n.
     */
    public void ocupar(PlainDate inicio,PlainDate fin) {
    	PlainDate finReal = fin.minus(1,CalendarUnit.DAYS);
    	setEstado(inicio,finReal,"Ocupada");
    }
    /**
     * Desocupa una habitaci�n el dia de hoy.
     */
    public void desocupar(){
    	desocupar(PlainDate.nowInSystemTime());
    }
    /**
     * Desocupa una habitaci�n en una fecha determinada. 
     * Notese que probablemente est� queriendo usar la versi�n de este m�todo que desocupa en un intervalo ya que en este caso se desocupar� solo en la fecha recibida, es decir si por ejemplo se encontrara ocupada de lunes a miercoles y se la desocupa el martes tanto el lunes como el miercoles se mantendr�n ocupados.
     * @param fecha Fecha para la cual se desea desocupar la habitaci�n.
     */
    public void desocupar(PlainDate fecha) {
    	desocupar(fecha, fecha);;
    }
    /**
     * Desocupa una habitaci�n en un intervalo determinado.
     * @param inicio Fecha a partir de la cual se desea ocupar la habitaci�n.
     * @param fin Fecha hasta la cual se desea ocupar la habitaci�n.
     */
    public void desocupar(PlainDate inicio, PlainDate fin){
    	PlainDate finReal =fin.minus(1, CalendarUnit.DAYS);
    	DateInterval intervalo = DateInterval.between(inicio, finReal);
    	ColecIntervalosDeFechas.minus(intervalo);
	 }
    
    /**
     * Habilita una habitaci�n para ser utilizada el dia de hoy
     */
    public void habilitar(){
    	habilitar(PlainDate.nowInSystemTime());
    }
    /**
     * Habilita una habitaci�n para ser utilizada en una fecha determinada. 
     * Notese que probablemente est� queriendo usar la versi�n de este m�todo que habilita en un intervalo ya que en este caso se habilitar� solo para la fecha recibida, es decir si por ejemplo se encontrara deshabilitada de lunes a miercoles y se la habilita el martes tanto el lunes como el miercoles se mantendr�n deshabilitadas.
     * @param fecha Fecha para la cual se desea desocupar la habitaci�n.
     */    
    public void habilitar(PlainDate fecha) {
    	habilitar(fecha,fecha);
    }
    /**
     * Habilita una habitaci�n para ser utilizada en un intervalo determinado.
     * Notese que quiz�s la habitaci�n se encuentre deshabilitada indefinidamente, en ese caso debe utilizarse habilitarIndefinidamente().
     * @param inicio Fecha a partir de la cual se desea habilitar la habitaci�n.
     * @param fin Fecha hasta la cual se desea habilitar la habitaci�n.
     */
    public void habilitar(PlainDate inicio,PlainDate fin) {
    	PlainDate finReal =fin.minus(1, CalendarUnit.DAYS);
    	DateInterval intervalo = DateInterval.between(inicio, finReal);
    	ColecIntervalosDeFechas.minus(intervalo);
	 }    
    /**
     * Habilita una habitaci�n para ser utilizada en un intervalo infinito a partir de la fecha ingresada.
     * Nota: Este m�todo "Pisar�" todos los intervalos posteriores a la fecha ingresada, es responsabilidad del usuario del m�todo chequear que esto no sea un problema.
     * @param fechaAPartirDeLaCual Fecha a partir de la cual se desea habilitar indefinidamente una habitaci�n.
     */
    public void habilitarIndefinidamente(PlainDate fechaAPartirDeLaCual) {
    	DateInterval intervalo = DateInterval.since(fechaAPartirDeLaCual);
    	ColecIntervalosDeFechas.minus(intervalo);
	 }
    /**
     * Devuelve una habitaci�n en forma de String.
     * @return Objeto de tipo Habitaci�n
     */
    public String mostrarHabitacion() {
    	String eString = (toString()+"\n Estado Actual: "+getEstado(PlainDate.nowInSystemTime()));
    	return eString;
    }
    /**
     * Muestra el estado de una habitaci�n por pantalla.
     * @return Objeto de tipo Habitaci�n
     */
    public void mostrarHabitacionPorPantalla() {
    	System.out.println(toString()+"\n Estado Actual: "+getEstado(PlainDate.nowInSystemTime()));
    }
    @Override
     public String toString() {
    	return "\n Numero de habitacion: " + numHabitacion + "\n Capacidad: " + capacidad + " personas" + "\n Tipo: " +
                tipo + "\n Precio diario: $" + precioDiario + "/n";
    }
    
    @Override
	public int compareTo(Habitacion hab) {
		if(capacidad > hab.getCapacidad()) {
				return 1;
			}
				else if(capacidad < hab.getCapacidad()) {
					return -1;
				}
		return 0;
	}
}
