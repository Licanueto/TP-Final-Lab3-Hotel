package hotel;

import net.time4j.*;
import net.time4j.range.ChronoInterval;
import net.time4j.range.DateInterval;
import net.time4j.range.IntervalCollection;
import net.time4j.range.ValueInterval;

import java.util.*;
import java.io.*;
														   //////////////////////////////////////////////////////////////////////////
 														  /*  Se toma como "Disponible" a la ausencia de intervalo en el periodo  */
													 	 //////////////////////////////////////////////////////////////////////////
										

													  //////////////////////////////////////////////////////////////////////////
													 /*                 A hacer:  Desocupar() && Habilitar()                 */
													////////////////////////////////////////////////////////////////////////// 

@SuppressWarnings("unused")
public class Habitacion {
	
	private String numHabitacion;
    private byte capacidad;
    private String tipo;
    private double precioDiario;
    private Frigobar frigobar;
    private IntervalCollection<PlainDate> ColecIntervalosDeFechas;
    /*
    private String estado;
    private boolean disponible;
    private boolean ocupada;
    */
    
    public Habitacion(String numHabitacion,byte capacidad,String tipo,double precioDiario,String estado){
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
    
    public String getEstado() {
    	return getEstado(PlainDate.nowInSystemTime());
    }
    
    public String getEstado(PlainDate fecha) {
    	if(isDisponible(fecha)) {  // Si la coleccion no tiene intervalo con esa fecha implica disponibilidad, sino se itera la coleccion hasta dar con el intervalo que posea la fecha recibida por parametro
    		return "Disponible";
    	}
    	else {
    		Iterator<ChronoInterval<PlainDate>> iterador = ColecIntervalosDeFechas.iterator();
    		while(iterador.hasNext()) {
    			ValueInterval<PlainDate, DateInterval, String> intervaloCasteado = (ValueInterval<PlainDate, DateInterval, String>)iterador;
    			DateInterval intervaloCasteadoAgain = intervaloCasteado.getBoundaries();
    			if(intervaloCasteadoAgain.contains(fecha)) {
    				return intervaloCasteado.getValue();
    			}
        	}
    		System.out.println("Acá no debería llegar nunca, si llega es que hay algun problema en getEstado(fecha) de la clase Habitación");
    	}
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
    public boolean isDisponible() {
    	return isDisponible(PlainDate.nowInSystemTime());
    }    
    public boolean isDisponible (PlainDate fecha) {
    	return isDisponible(fecha, fecha);
    }
    public boolean isDisponible (PlainDate inicio,PlainDate fin) {
    	DateInterval intervaloRecibido = DateInterval.between(inicio, fin);
    	Iterator<ChronoInterval<PlainDate>> iterador = ColecIntervalosDeFechas.iterator();
    	while(iterador.hasNext()) {
    		ValueInterval<PlainDate, DateInterval, String> intervaloCasteado = (ValueInterval<PlainDate, DateInterval, String>)iterador;
    		DateInterval intervaloAComparar = intervaloCasteado.getBoundaries();
    		if(intervaloRecibido.intersects(intervaloAComparar)) {
    			return false
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
    	setEstado(inicio,fin,"Ocupada");
    }
    
    public void desocupar(){
        // TO DO
    }
    
    
    public void deshabilitar() {
    	deshabilitar(PlainDate.nowInSystemTime(),"Deshabilitada");
    }
    public void deshabilitar(String estado) {
    	deshabilitar(PlainDate.nowInSystemTime(), estado);
    }
    public void deshabilitar(PlainDate fechaAPartirDeLaCual,String estado){
    	DateInterval intervaloSinValor = DateInterval.since(fechaAPartirDeLaCual);
    	ValueInterval<PlainDate, DateInterval, String> intervalo = intervaloSinValor.withValue(estado);
    	ColecIntervalosDeFechas = ColecIntervalosDeFechas.plus(intervalo);
    }
    public void deshabilitar(PlainDate inicio, PlainDate fin, String estado) {
    	DateInterval intervaloSinValor = DateInterval.between(inicio, fin);
    	ValueInterval<PlainDate, DateInterval, String> intervalo = intervaloSinValor.withValue(estado);
    	ColecIntervalosDeFechas = ColecIntervalosDeFechas.plus(intervalo);
    }
    
    public void habilitar(){
    	// TO DO
    }
    @Override
     public String toString() {
        return "\nNumero de habitacion: " + numHabitacion + "\nCapacidad: " + capacidad + " pasajeros" + "\nTipo: " +
                tipo + "\nPrecio diario: " + precioDiario + " pesos/n";
    }
/*
    public String mostrarHabitacion(){
        String estado;
        
        if(ocupada){
            estaOcupada = "Ocupada";
        }
        else{
            estaOcupada = "Libre";
        }
        if(disponible){
            estaDisponible = "Disponible";
        }
        else {
            estaDisponible = "No disponible";
        }

        return toString() + "\n" + estaDisponible + "\n" + estaOcupada ;/*
    }

}
