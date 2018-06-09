package Clases;

import java.util.ArrayList;

import net.time4j.PlainDate;

public class Reserva {
	
	private int numeroReserva;
	private String dniPasajero;
	private PlainDate fechaIngreso;
	private PlainDate fechaEgreso;
	private ArrayList<String> numerosHabitaciones; //fijarse porque en el uml figuara Array<numeroHabitaciones> y me parece que no iria as�
	private double saldo;
	private double monto;
	private boolean seHizoEfectiva;
	
	public Reserva(int numeroReserva, String dni, PlainDate fechaIngreso, PlainDate fechaEgreso, ArrayList<String> numerosHabitaciones)
	{
		this.numeroReserva = numeroReserva;
		this.dniPasajero = dniPasajero;
		this.fechaIngreso = fechaIngreso;
		this.fechaEgreso = fechaEgreso;
		this.numerosHabitaciones = numerosHabitaciones;
		saldo = 0;
		monto = 0;
		seHizoEfectiva = false; //se hace efectiva cuando se realiza el check in.
	}
	public int getNumeroReserva()
	{
		return numeroReserva;
	}
	public void setDni(String dniPasajero)
	{
		this.dniPasajero = dniPasajero;
	}
	public String getDniPasajero()
	{
		return dniPasajero;
	}
	public PlainDate getFechaIngreso()
	{
		return fechaIngreso;
	}
	public PlainDate getFechaEgreso()
	{
		return fechaEgreso;
	}
	public ArrayList<String> getNumerosHabitaciones()
	{
		return numerosHabitaciones;
	}
	public void setSaldo(double saldo)
	{
		this.saldo = saldo;
	}
	public double getSaldo()
	{
		return saldo;
	}
	public void setMonto(double monto)
	{
		
		this.monto = monto;
	}
	public double getMonto()
	{
		return monto;
	}
	public boolean getSeHizoEfectiva()
	{
		return seHizoEfectiva;
	}
	
	//Metodo que devuelve en un string si se hizo efectiva la reserva, es decir si se realiz� el check in
	
	public String mostrarEfectiva()
	{
		String respuesta;
		if (seHizoEfectiva == true)
			respuesta = "Reserva efectiva";
		else
			respuesta = "Reserva no efectiva";
		return respuesta;
	}
	
	@Override
	public String toString() {
		
		return "\nNumero de reserva: " + numeroReserva + "\nDni pasajero: " + dniPasajero + "\nFecha Ingreso: " + fechaIngreso 
				+ "\nFecha Egreso: " + fechaEgreso + "\nSaldo: " + saldo + "\nMonto: " + monto + mostrarEfectiva();
	}
	
	public double calcularMonto()
	{
		monto = 
	}
	
	
	
	
}

