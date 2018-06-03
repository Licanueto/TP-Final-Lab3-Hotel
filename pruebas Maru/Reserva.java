package Clases;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.ObjectInputStream;
import java.io.Serializable;
import java.util.ArrayList;

public class Reserva implements Serializable{
	
	private int numeroReserva;
	private String dni;
	private int cantidadDias;
	
	public Reserva(String dni, int cantidadDias, int numeroReserva)
	{
		//numeroReserva = generarNumeroReserva();
		this.dni = dni;
		this.cantidadDias = cantidadDias;
		this.numeroReserva = numeroReserva;
	}
	public int getNumeroReserva()
	{
		return numeroReserva;
	}
	public void setdni(String dni)
	{
		this.dni = dni;
	}
	public String getDni()
	{
		return dni;
	}
	public void setCantidadDias(int CantidadDias)
	{
		this.cantidadDias = cantidadDias;
	}
	public int getCantidadDias()
	{
		return cantidadDias;
	}
	@Override
	public String toString() 
	{
		
		return "\nNumero de reserva: " + numeroReserva + "\ndni: " + dni + 
				"\nCantidad de dias: " + cantidadDias;
	}
	
	
	
	
	


}


