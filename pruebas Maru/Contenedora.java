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

public class Contenedora implements Serializable{
	
	Hotel granHotel;
	ObjectOutputStream salida;
	ObjectInputStream entrada;
	String sFichero = "C:/Users/Maru/Documents/UTN - Programación/2018 - SEGUNDO AÑO/Programacion 3/Trabajo Practico GRAN HOTEL/Maru - Pruebas/Archivos -ArrayList/reservas.dat";
	File fichero;
	
	public Contenedora()
	{
		granHotel = new Hotel();
		fichero  = new File(sFichero);
		grabarHotel();
			
	
		
	}
	public void grabarHotel()
	{
		try {
			if(!fichero.exists()) { 
			salida = new ObjectOutputStream(new FileOutputStream("reservas.dat"));
		     salida.writeObject(granHotel);
		     salida.close();
			}
	
		}catch (FileNotFoundException e)
		{
			e.printStackTrace();
			e.getMessage();
		}catch(IOException ex)
		{
			ex.getMessage();
			ex.printStackTrace();
		}catch(Exception exc)
		{
			exc.printStackTrace();
		}
	}
	public void mostrarContenedora()
	{
		try{
			entrada = new ObjectInputStream(new FileInputStream("reservas.dat"));
			Hotel hotelito = (Hotel) entrada.readObject();
			hotelito.mostrarReservas();
			entrada.close();
			
		}catch(FileNotFoundException e)
		{
		      e.printStackTrace();
		      e.getMessage();
	    }catch(IOException ex)
	    {
		      ex.getMessage();
		      ex.printStackTrace();
	    }catch(Exception exc)
	     {
		     exc.printStackTrace();
	     }
	}
	
	
	
	
	
	
	
	
	
	
	

}
