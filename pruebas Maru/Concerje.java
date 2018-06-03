package Clases;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;

public class Concerje implements Serializable{
	
	public Concerje()
	{
		
	}
	public int reservar(String dni, int cantidadDias) //ArrayList<Habitacion> habitaciones)
	{
		int numeroReserva = generarNumeroReserva();
		ObjectInputStream entrada;
		ObjectOutputStream salida;
		Reserva reser = new Reserva(dni, cantidadDias, numeroReserva);
		
		
		try
		{
			entrada = new ObjectInputStream(new FileInputStream("reservas.dat"));
			
			Hotel reservorio = (Hotel) entrada.readObject();
			
			
			entrada.close();
			
			reservorio.agregarReserva(reser);
			
			salida = new ObjectOutputStream(new FileOutputStream("reservas.dat"));
			salida.writeObject(reservorio);
			
			
			
			salida.close();
			
			
		}catch (FileNotFoundException e)
		{
			e.printStackTrace();
		}catch(IOException io)
		{
			io.printStackTrace();
		}catch(ClassNotFoundException cl)
		{
			cl.printStackTrace();
		}
		
		return numeroReserva;
	}
	
	public int generarNumeroReserva()
	{
		int numeroReserva = 0;
		ObjectInputStream entrada;
		try 
		{
		       entrada = new ObjectInputStream(new FileInputStream("reservas.dat"));
		       Hotel reser = (Hotel) entrada.readObject();
		       numeroReserva = reser.getSize();
		       entrada.close();
		       
		       
		       
		}catch (FileNotFoundException e)
		{
			e.printStackTrace();
			numeroReserva = 100;
			
		}catch(Exception ex)
		{
			ex.printStackTrace();
			numeroReserva = 200;
	    }finally {
	    	//System.out.println(numeroReserva);
	    	return numeroReserva;
	    }
		
		
		
	}
			
	

}
