package Clases;

import java.util.Date;

public class Reserva {
	
	private int numeroReserva;
	private String dniPasajero;
	private Date fechaIngreso;
	private Date fechaEgreso;
	private int [] numerosHabitaciones; //fijarse porque en el uml figuara Array<numeroHabitaciones> y me parece que no iria así
	private double saldo;
	private double monto;
	private boolean seHizoEfectiva;
	
	//saldo, monto y seHizoEfectiva se cargan despues, ahora van por defecto pero seria el resultado de otros metodos
	
	//preguntar este tema de los constructores vacios
	
	public Reserva()
	{
		numeroReserva = -1;
		dniPasajero = " ";
		fechaIngreso = new Date();
		fechaEgreso = new Date();
		numerosHabitaciones = null;
		saldo = 0;
		monto = 0;
		seHizoEfectiva = false;
		
	}
	
	public Reserva(int numeroReserva, String dniPasajero, Date fechaIngreso, Date fechaEgreso, int [] numeroHabitaciones)
	{
		this.numeroReserva = numeroReserva;
		this.dniPasajero = dniPasajero;
		this.fechaIngreso = fechaIngreso;
		this.fechaEgreso = fechaEgreso;
		this.numerosHabitaciones = numeroHabitaciones;
		saldo = 0;
		monto = 0;
		seHizoEfectiva = false;
		
		
	}
	
	public void setNumeroReserva(int numeroReserva)
	{
		this.numeroReserva = numeroReserva;
	}
	public int getNumeroReserva()
	{
		return numeroReserva;
	}
	public void setDniPasajero(String dniPasajero)
	{
		this.dniPasajero = dniPasajero;
	}
	public String getDniPasajero()
	{
		return dniPasajero;
	}
	public void setFechaIngreso(Date fechaIngreso)
	{
		this.fechaIngreso = fechaIngreso;
	}
	public Date getFechaIngreso()
	{
		return fechaIngreso;
	}
	public void setFechaEgreso(Date fechaEgreso)
	{
		this.fechaEgreso = fechaEgreso;
	}
	public Date getFechaEgreso()
	{
		return fechaEgreso;
	}
	public void setNumerosHabitaciones(int [] numerosHabitaciones)
	{
		this.numerosHabitaciones = numerosHabitaciones;
	}
	public int [] getNumerosHabitaciones()
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
	//Respecto de si se hizo efectiva creo que solo necesita get
	
	public boolean getSeHizoEfectiva()
	{
		return seHizoEfectiva;
	}
	
	
	//Ver como mostrar el arreglo de habitaciones porque por ahi habria que escribir un metodo
	
	@Override
	public String toString() {
		
		return "\nNumero de Reserva: " + numeroReserva + "\nDni Pasajero: " + dniPasajero + 
				"\nFecha de Ingreso: " + fechaIngreso + "\nnFecha de Egreso: " + fechaEgreso +
				"\nSaldo: " + saldo + "\nMonto: " + monto + "\nEstado de Reserva: " + seHizoEfectiva;
	}
	
	//public double calcularMonto()
	//public void descontarSaldo(double importe)

}
