package Clases;

import java.io.Serializable;
import java.util.ArrayList;

public class Hotel implements Serializable{
	
	private ArrayList<Reserva>reservas;
	
	public Hotel()
	{
		reservas = new ArrayList<Reserva>();
	}
	
	public void agregarReserva(Reserva reserva)
	{
		reservas.add(reserva);
	}
	public void borrarReserva(Reserva reserva)
	{
		reservas.remove(reserva);
	}
	public void mostrarReservas()
	{
		for(Reserva reserva: reservas)
		{
			System.out.println(reserva.toString());
		}
	}
	public int getSize()
	{
		return reservas.size();
	}
	
	

}
