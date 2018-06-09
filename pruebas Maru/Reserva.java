package Clases;

import java.util.ArrayList;

import net.time4j.PlainDate;

public class Reserva {
	
	private int numeroReserva;
	private String dniPasajero;
	private PlainDate fechaIngreso;
	private PlainDate fechaEgreso;
	private ArrayList<String> numerosHabitaciones; //fijarse porque en el uml figuara Array<numeroHabitaciones> y me parece que no iria así
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
	
	//Metodo que devuelve en un string si se hizo efectiva la reserva, es decir si se realizó el check in
	
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
	
	public double calcularMonto()
	{
		
		BaseDeDatos aux;
		double tarifa = 0;
		double frigo = 0;
		double parcial = 0;
		for(i = 0; i < numerosHabitaciones.size(); i++)
		{
			tarifa = aux.obtenerTarifa(numerosHabitaciones[i]);
			frigo = aux.obtenerSaldoFrigobar(numerosHabitaciones[i]);
			parcial += tarifa + frigo;
		}
		monto = parcial * cantidadDias;
		return monto;
		
		
	}
	public double calcularMonto() //OTRA VERSION FIJATE QUE SE ESTABA MULTIPLICANDO FRIGOBAR POR LOS DIAS....
	{
		double tarifa = 0;
		double frigo = 0;
		
		for(int i = 0; i < numerosHabitaciones.size(); i++)
		{
			//obtener tarifa y obtenersaldo son metodos estaticos para poder acceder por fuera sin referencias....
			tarifa += BaseDeDatos.obtenerTarifa(numerosHabitaciones.get(i)); //ACUMULA LAS TARIFAS DIARIAS DE TODAS LAS HABITACIONES
			frigo += BaseDeDatos.obtenerSaldoFrigobar(numerosHabitaciones.get(i)); //ACUMULA TODOS LOS TOTALES DE FRIGOBARES
		}
		monto = tarifa * cantidadDias;  //MULTIPLICA ACUMULADO DE TARIFAS DIARIAS POR CANT DE DIAS
		monto += frigo;  // SUMAMOS TOTAL DE FRIGOBARES A LO ANTERIOR
		
		return monto;
		
		
	}
	
	public void descontarSaldo(double importe)
	{
		saldo = monto - importe;
	}
	
	
	
	
}

