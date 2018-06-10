package Clases;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import net.time4j.PlainDate;

public class Concerje extends Usuario {
	
	public Concerje(String dni, String nombre, String apellido)
	{
		super(dni, nombre, apellido);
	}
	
	@Override
	public String toString() {
		
		return super.toString();
	}
	
	ArrayList<String> asignarHabitaciones(PlainDate fechaIngreso, PlainDate fechaEgreso, int cantPasajeros)
	{
		ArrayList<String>numerosHabitaciones = new ArrayList<String>();
		try {
			boolean disponibilidad = verificarDisponibilidad();
			
			int i = 0,j = 0;
			ArrayList<Habitacion> libres = BaseDeDatos.buscarAptas(fechaIngreso, fechaEgreso);
			Collections.sort((List) libres);
			Collections.reverse(libres);
			while(cantPasajeros > 0)
			{
				while(i < libres.size())
				{
					if(cantPasajeros >= libres.get(i).getCapacidad())
					{
						cantPasajeros = cantPasajeros - libres.get(i).getCapacidad();
						numerosHabitaciones.get(j) = libres.get(i).getNumeroHabitacion();
						j++;
					}
					i++;
				}
				if(cantPasajeros > 0)
				{
					i = libres.size()-1;
					numerosHabitaciones.get(j) = libres.get(i).getNumeroHabitacion();
					cantPasajeros = 0;
				}
			}
			return numerosHabitaciones;
			
			
		}catch(FaltaDisponibilidadException e){
			
			e.getMessage();
			
		}catch(RuntimeException e) {
			
			e.getMessage();
		}
	}
	public boolean verificarDisponibilidad()throws FaltaDisponibilidadException
	{
		boolean verifica;
		if (verifica = BaseDeDatos.hayCapacidad(cantPasajeros) == false)
			throw new FaltaDisponibilidadException("No alcanza la capacidad del hotel para hospedar a los pasajeros");
		return verifica;
			
	}
	
	public int Reservar()
	
	

	
}
