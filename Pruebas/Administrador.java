package Clases;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;



public class Administrador extends Usuario implements IAbmUsuario{
	
	public Administrador(String dni,String nombre,String apellido) {
		super(dni,nombre,apellido);
	}
	
	
	public void serializarConcerjes () {
		
		ObjectOutputStream escritura = null;
		HashMap<String, Concerje> auxiliar  = BaseDeDatos.obtenerConcerjes();
		
		try {
			escritura = new ObjectOutputStream(new FileOutputStream("concerjes.dat"));
			escritura.writeObject(auxiliar);
		}
		catch (IOException e) {
			// TODO: handle exception
		}
		catch (Exception e) {
			// TODO: handle exception
		}
		finally {
			try {
				escritura.close();
			}
			catch(IOException e) {
				//hacer algo
			}
		}
	}
	
public void serializarHabitaciones () {
		
		ObjectOutputStream escritura = null;
		ArrayList<Habitacion> auxiliar  = BaseDeDatos.obtenerHabitaciones();
		
		try {
			escritura = new ObjectOutputStream(new FileOutputStream("habitaciones.dat"));
			escritura.writeObject(auxiliar);
		}
		catch (IOException e) {
			// TODO: handle exception
		}
		catch (Exception e) {
			// TODO: handle exception
		}
		finally {
			try {
				escritura.close();
			}
			catch(IOException e) {
				//hacer algo
			}
		}
	}

public void serializarPasajeros () {
	
	ObjectOutputStream escritura = null;
	HashMap<String, Pasajero> auxiliar  = BaseDeDatos.obtenerPasajeros();
	
	try {
		escritura = new ObjectOutputStream(new FileOutputStream("pasajeros.dat"));
		escritura.writeObject(auxiliar);
	}
	catch (IOException e) {
		// TODO: handle exception
	}
	catch (Exception e) {
		// TODO: handle exception
	}
	finally {
		try {
			escritura.close();
		}
		catch(IOException e) {
			//hacer algo
		}
	}
}

public void serializarReservas () {
	
	ObjectOutputStream escritura = null;
	ArrayList<Reserva> auxiliar  = BaseDeDatos.obtenerReservas();
	
	try {
		escritura = new ObjectOutputStream(new FileOutputStream("reservas.dat"));
		escritura.writeObject(auxiliar);
	}
	catch (IOException e) {
		// TODO: handle exception
	}
	catch (Exception e) {
		// TODO: handle exception
	}
	finally {
		try {
			escritura.close();
		}
		catch(IOException e) {
			//hacer algo
		}
	}
}
	
	public void backUp() {
		
		char seguir = 'n';
		Scanner scanner = new Scanner(System.in);
		System.out.println("Desea realizar un Back Up de la informacion del sistema? s/n");
		seguir = scanner.next().charAt(0);
		if(seguir == 's' || seguir == 'S') {
			
			serializarHabitaciones();
			serializarPasajeros();
			serializarReservas();
			serializarConcerjes();
		}
		scanner.close();
	}
	
	
	@Override
	public void darDeAltaUsuario() {
		
		Scanner scanner = new Scanner(System.in);
		String nombreConcerje = "";
		String apellidoConcerje = "";
		String dniConcerje = "";
		char correcto = 'n';
		Concerje concerje1;
		Concerje concerje2;
		Concerje concerje3;
		int contador = 0;
		
		while(correcto == 'n' || correcto == 'N') {
			System.out.println("**Alta de nuevo concerje**");
			System.out.println("Ingrese nombre(s): ");
			nombreConcerje = scanner.next();
			System.out.println("Ingrese Apellido(s)");
			apellidoConcerje = scanner.next();
			System.out.println("Ingrese DNI");
			dniConcerje = scanner.next();
			System.out.println("Los datos ingresados son: \nNombre: " + nombreConcerje + "\nApellido: " + apellidoConcerje +
					           "\nDNI: " + dniConcerje + "\nEs correcta la informacion? oprimir 's' para guardar... 'n' para modificar... ");
			correcto = scanner.next().charAt(0);
		}
		scanner.close();
		
		if(contador == 0) {
			concerje1 = new Concerje(nombreConcerje,apellidoConcerje,dniConcerje);
			BaseDeDatos.agregarConcerje(concerje1);
			contador++;
		}
		if(contador == 1) {
			concerje2 = new Concerje(nombreConcerje,apellidoConcerje,dniConcerje);
			BaseDeDatos.agregarConcerje(concerje2);
			contador++;
		}
		if(contador == 2) {
			concerje3 = new Concerje(nombreConcerje,apellidoConcerje,dniConcerje);
			BaseDeDatos.agregarConcerje(concerje3);
			contador++;
		}
		
		
			
	}
	
	@Override
	public void darDeBajaUsuario() {
		
		
	}
}
