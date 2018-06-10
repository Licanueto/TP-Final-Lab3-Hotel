package clases;

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
		 /*						 A hacer: frigobar  						     */
	    ////////////////////////////////////////////////////////////////////////// 
												
	  //////////////////////////////////////////////////////////////////////////
	 /*                 "Disponible" es equivalente a "ocupable"             */
    ////////////////////////////////////////////////////////////////////////// 

	// El implements comparable y el compareTo() no están funcionando;
	// Estaría bueno que al crear la habitación se verifique que el numero de habitación no haya sido ya asignado a otra hab

	// Casos desabilitar() L-Limpieza D-Desinfeccion R-Reparacion

@SuppressWarnings("unused")
public class Habitacion {//implements Comparable<Habitacion>{ 
	
	// prueba javadoc (no funca)
	/**
	 * Este objeto representa la Habitación de un Hotel
	 * @param razon L-Limpieza D-Desinfeccion R-Reparacion
	 * @param tipo Expresa el nivel de comfort de la habitación
	 * @param capacidad cantidad de pasajeros que puede albergar la habitación
	 * */
	
	
	
	private String numHabitacion;
    private byte capacidad;
    private String tipo;
    private double precioDiario;
    private Frigobar frigobar;
    private IntervalCollection<PlainDate> ColecIntervalosDeFechas;
    
    public Habitacion(String numHabitacion,byte capacidad,String tipo,double precioDiario){
        this.numHabitacion = numHabitacion;
        this.capacidad = capacidad;
        this.tipo = tipo;
        this.precioDiario = precioDiario;
        frigobar = new Frigobar(); //ver el tema de parametros
        ColecIntervalosDeFechas = IntervalCollection.onDateAxis();
    }

    public void setCapacidad(byte capacidad) {

        this.capacidad = capacidad;
    }

    public void setTipo(String tipo) {

        this.tipo = tipo;
    }

    public void setPrecioDiario(double precioDiario) {

        this.precioDiario = precioDiario;
    }

    public String getNumHabitacion() {

        return numHabitacion;
    }

    public byte getCapacidad() {

        return capacidad;
    }

    public String getTipo() {

        return tipo;
    }

    public double getPrecioDiario() {

        return precioDiario;
    }
    
    public void setEstado(String estado) {
    	setEstado(PlainDate.nowInSystemTime(), PlainDate.nowInSystemTime(), estado);
    }
    public void setEstado(PlainDate fecha, String estado) {
    	setEstado(fecha, fecha, estado);
    }
    public void setEstado(PlainDate inicio, PlainDate fin, String estado) {
    	DateInterval intervaloSinValor = DateInterval.between(inicio, fin); 
    	ValueInterval<PlainDate, DateInterval, String> intervalo = intervaloSinValor.withValue(estado); 
    	ColecIntervalosDeFechas = ColecIntervalosDeFechas.plus(intervalo); 
    }
    
    public void deshabilitar() {
    	char razon = 'z';
    	deshabilitar(razon);
    }
    public void deshabilitar(char razon) {
    	//prueba javadoc
    	/**
    	 * Deshabilita la habitacion
    	 * @param razon L-Limpieza D-Desinfeccion R-Reparacion
    	 * */
    	switch(razon) {
    		case 'r' : 
    		case 'R' : setEstado("Deshabilitada: Reparación");
						break;
    		case 'd' : 
    		case 'D' : setEstado("Deshabilitada: Desinfección");
						break;
    		case 'l' : 
    		case 'L' : setEstado("Deshabilitada: Limpieza");
						break;
    		default: setEstado("Deshabilitada");
    		}
    }
    public void deshabilitar(PlainDate fecha) {
    	setEstado("Deshabilitada");
    }
    public void deshabilitar(PlainDate fecha, char razon) {
    	switch(razon) {
		case 'r' : 
		case 'R' : setEstado(fecha,"Deshabilitada: Reparación");
					break;
		case 'd' : 
		case 'D' : setEstado(fecha,"Deshabilitada: Desinfección");
					break;
		case 'l' : 
		case 'L' : setEstado(fecha,"Deshabilitada: Limpieza");
					break;
		default: setEstado(fecha,"Deshabilitada");
	}
    }
    public void deshabilitar(PlainDate inicio, PlainDate fin) {
    	setEstado(inicio,fin,"Deshabilitada");
    }
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
    
    public String getEstado() {
    	return getEstado(PlainDate.nowInSystemTime());
    }  
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
    		System.out.println("Acá no debería llegar nunca, si llega es que hay algun problema en getEstado(fecha) de la clase Habitación");
    	}
    	return "Disponible";
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
    public boolean isDisponible() { // Disponible es "Disponible para ser ocupada"
    	return isDisponible(PlainDate.nowInSystemTime());
    }    
    public boolean isDisponible (PlainDate fecha) {
    	return isDisponible(fecha, fecha);
    }
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
    
    public boolean isOcupada() {//provisionalmente.. o no
        return !isDisponible();
    }
    public boolean isOcupada(PlainDate fecha) {
    	return !isDisponible(fecha);
    }
    public boolean isOcupada(PlainDate inicio,PlainDate fin) {
    	return !isDisponible(inicio, fin);
    }
    
    public void ocupar(){
        setEstado(PlainDate.nowInSystemTime(),"Ocupada");
    }
    public void ocupar(PlainDate fecha) {
    	setEstado(fecha,"Ocupada");
    }
    public void ocupar(PlainDate inicio,PlainDate fin) {
    	PlainDate finReal = fin.minus(1,CalendarUnit.DAYS);
    	setEstado(inicio,finReal,"Ocupada");
    }
    
    public void desocupar(){
    	desocupar(PlainDate.nowInSystemTime());
    }
    public void desocupar(PlainDate fecha) {
    	desocupar(fecha, fecha);;
    }
    public void desocupar(PlainDate inicio, PlainDate fin){
    	PlainDate finReal =fin.minus(1, CalendarUnit.DAYS);
    	DateInterval intervalo = DateInterval.between(inicio, finReal);
    	ColecIntervalosDeFechas.minus(intervalo);
	 }
    
    
    
    
    public void habilitar(){
    	habilitar(PlainDate.nowInSystemTime());
    }
    public void habilitar(PlainDate fecha) {
    	habilitar(fecha,fecha);
    }
    public void habilitar(PlainDate inicio,PlainDate fin) {
    	PlainDate finReal =fin.minus(1, CalendarUnit.DAYS);
    	DateInterval intervalo = DateInterval.between(inicio, finReal);
    	ColecIntervalosDeFechas.minus(intervalo);
	 }    
    
    public int obtenerCantidadDeDias(PlainDate inicio, PlainDate fin) {
    	DateInterval intervalo = DateInterval.between(inicio, fin);
    	int cantidadDeDias = (int)intervalo.getLengthInDays()-1; //resta 1 porque cuando entra un dia y se va al otro cuenta como un solo día
    	return cantidadDeDias;
    }

    public void mostrarHabitacion() {
    	System.out.println(toString()+"\n Estado Actual: "+getEstado(PlainDate.nowInSystemTime()));
    }
    
    @Override
     public String toString() {
        return "\n Numero de habitacion: " + numHabitacion + "\n Capacidad: " + capacidad + " asajeros" + "\n Tipo: " +
                tipo + "\n Precio diario: $" + precioDiario + "/n";
    }
    
    /*@Override
	public int compareTo(Object o) {
		if(o instanceof Habitacion) {
			Habitacion hab = (Habitacion)o;
			if(capacidad > hab.getCapacidad()) {
				return 1;
			}
				else if(capacidad < hab.getCapacidad()) {
					return -1;
				}
			}
		return 0;
	}*/
}
