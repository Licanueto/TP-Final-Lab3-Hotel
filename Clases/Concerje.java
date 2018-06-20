package Clases;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

import net.time4j.PlainDate;

public class Concerje extends Usuario implements IAbmUsuario, IAbmHabitacion {
	
	public Concerje(String dni, String nombre, String apellido)
	{
		super(dni, nombre, apellido);
	}
	
	@Override
	public String toString() {
		
		return super.toString();
	}
	 @Override
	    public void darDeAltaUsuario() {
	        Scanner scanner = new Scanner(System.in);
	        String nombrePasajero = "";
	        String apellidoPasajero = "";
	        String dniPasajero = "";
	        String celular = "";
	        String eMail = "";
	        String ciudadOrigen = "";
	        String domicilio = "";
	        char correcto = 'n';
	        Pasajero pasajero1;
	        int contador = 0;

	        while(correcto == 'n' || correcto == 'N') {
	            System.out.println("**Alta de nuevo Pasajero**");
	            System.out.println("Ingrese nombre(s): ");
	            nombrePasajero = scanner.next();
	            System.out.println("Ingrese Apellido(s)");
	            apellidoPasajero = scanner.next();
	            System.out.println("Ingrese DNI");
	            dniPasajero = scanner.next();
	            System.out.println("Ingrese numero de telefono");
	            celular = scanner.next();
	            System.out.println("Ingrese e-mail");
	            eMail = scanner.next();
	            System.out.println("Ingrese ciudad de origen");
	            ciudadOrigen = scanner.next();
	            System.out.println("Ingrese domicilio");
	            domicilio = scanner.next();
	            System.out.println("Los datos ingresados son: \nNombre: " + nombrePasajero + "\nApellido: " + apellidoPasajero +
	                    "\nDNI: " + dniPasajero + "\nCelular: " + celular + "\nE-Mail: " + eMail +
	                    "Ciudad de origen: " + ciudadOrigen + "\nDomicilio: " + domicilio +
	                    "\nEs correcta la informacion? oprimir 's' para guardar... 'n' para modificar... ");
	            correcto = scanner.next().charAt(0);
	        }
	        scanner.close();


	        pasajero1 = new Pasajero(nombrePasajero,apellidoPasajero,dniPasajero,celular,eMail,ciudadOrigen,domicilio);
	        BaseDeDatos.agregarPasajero(pasajero1);//una vez agregado al arreglo se puede volver a crear otroconcerje con esta variable


	    }
	 @Override
	public void darDeBajaUsuario(String dni) {
		 
		 try {
			 Pasajero pasajerito = BaseDeDatos.buscaPasajeroporDni(dni);
			 pasajerito.ponerEnBaja();
			 BaseDeDatos.agregarPasajero(pasajerito);
			 
		 }catch(PasajeroNullException p)
		 {
			 p.getMessage();
		 }catch(RuntimeException r)
		 {
			 r.getMessage();
		 }
		
		
	}
	 @Override
	public void darDeAltaUsuario(String dni) {
		
		 try {
			 Pasajero pasajerito = BaseDeDatos.buscaPasajeroporDni(dni);
			 pasajerito.ponerEnAlta();
			 BaseDeDatos.agregarPasajero(pasajerito);
			 
		 }catch(PasajeroNullException p)
		 {
			 p.getMessage();
		 }catch(RuntimeException r)
		 {
			 r.getMessage();
		 }
	}
	 public void comprobarPasajero(Pasajero pasajerito)
	 {
		 if(pasajerito == null)
			 throw new PasajeroNullException("El pasajero es inexistente");
	 }
	 
	 @Override
	public void darAltaHab(String numeroHab) {
		
		 try {
			 
			 Habitacion hab = BaseDeDatos.buscarPorNumero(numeroHab);
			 comprobarHabitacion(hab);
			 int indice = BaseDeDatos.obtenerIndiceHabitacion(numeroHab);
			 hab.habilitar();
			 BaseDeDatos.agregarHabitacionAlIndice(hab, indice);
		 }catch(HabitacionNulaException h)
		 { 
			 h.getMessage();
		 }catch(RuntimeException r)
		 {
			 r.getMessage();
		 }
		
	}
	 @Override
	public void darBajaHab(String numeroHab) {
try {
			 
			 Habitacion hab = BaseDeDatos.buscarPorNumero(numeroHab);
			 comprobarHabitacion(hab);
			 int indice = BaseDeDatos.obtenerIndiceHabitacion(numeroHab);
			 hab.deshabilitar();
			 BaseDeDatos.agregarHabitacionAlIndice(hab, indice);
		 }catch(HabitacionNulaException h)
		 { 
			 h.getMessage();
		 }catch(RuntimeException r)
		 {
			 r.getMessage();
		 }
		
	}
	
	ArrayList<String> asignarHabitaciones(PlainDate fechaIngreso, PlainDate fechaEgreso, int cantPasajeros)
	{
		ArrayList<String>numerosHabitaciones = new ArrayList<String>();
		String aux = "";
		try {
			verificarDisponibilidad(cantPasajeros);
			
			int i = 0;
			ArrayList<Habitacion> libres = BaseDeDatos.buscarAptas(fechaIngreso, fechaEgreso);
			Collections.sort(libres);
			Collections.reverse(libres);
			while(cantPasajeros > 0)
			{
				while(i < libres.size())
				{
					if(cantPasajeros >= libres.get(i).getCapacidad())
					{
						cantPasajeros = cantPasajeros - libres.get(i).getCapacidad();
						aux = libres.get(i).getNumHabitacion();
						numerosHabitaciones.add(aux);
						
					}
					i++;
				}
				if(cantPasajeros > 0)
				{
					i = libres.size()-1;
					aux = libres.get(i).getNumHabitacion();
					numerosHabitaciones.add(aux);
					
					
					cantPasajeros = 0;
				}
			}
			
			
			
		}catch(FaltaDisponibilidadException e){
			
			e.getMessage();
			
		}catch(RuntimeException e) {
			
			e.getMessage();
		}finally {
			return numerosHabitaciones;
		}
	}
	public boolean verificarDisponibilidad(int cantPasajeros)throws FaltaDisponibilidadException
	{
		boolean verifica = BaseDeDatos.hayCapacidad(cantPasajeros);
		if (verifica  == false)
			throw new FaltaDisponibilidadException("No alcanza la capacidad del hotel para hospedar a los pasajeros");
		return verifica;
			
	}
	
	public int Reservar(String dni, PlainDate fechaIngreso, PlainDate fechaEgreso, ArrayList<String> numerosHabitaciones)
	{
		int numeroReserva = BaseDeDatos.obtenerUltimaReserva() + 1;
		Reserva nuevaReserva = new Reserva(numeroReserva, dni, fechaIngreso, fechaEgreso, numerosHabitaciones);
		BaseDeDatos.agregarReserva(nuevaReserva);
		
		try {
			for(int i = 0; i < numerosHabitaciones.size(); i++)
			{
				
				Habitacion hab = BaseDeDatos.buscarPorNumero(numerosHabitaciones.get(i));
				comprobarHabitacion(hab);
				hab.ocupar(fechaIngreso, fechaEgreso);
				BaseDeDatos.agregarHabitacionAlIndice(hab, i);
				
			}
		}catch(HabitacionNulaException h)
		{
			h.getLocalizedMessage();
		}catch(RuntimeException r)
		{
			r.getMessage();
		}
		
		return numeroReserva;
				
	}
	
	public void realizarCheckIn(int numeroReserva)
	{
		try{
			Reserva reser = BaseDeDatos.obtenerReserva(numeroReserva);
			comprobarReserva(reser);
			int indice = BaseDeDatos.obtenerIndiceReserva(numeroReserva);
			comprobarIndice(indice);
			reser.hacerEfectiva();
			BaseDeDatos.agragarReservaAlIndice(indice, reser);
		}catch(ReservaNulaException r)
		{
			r.getMessage();
		}catch(IndiceIncorrectoException i)
		{
			i.getMessage();
		}
		
	}
	public void realizarChekOut(int numeroReserva, double importe) //importe que ingresa en dinero el cliente
	{
		
		try {
			Reserva reser = BaseDeDatos.obtenerReserva(numeroReserva);
			comprobarReserva(reser);
			int indice = BaseDeDatos.obtenerIndiceReserva(numeroReserva);
			comprobarIndice(indice);
			cobrar(reser, importe);
			ArrayList<String> habitaciones = reser.getNumerosHabitaciones();
			for(int i = 0; i < habitaciones.size(); i++)
			{
				Habitacion hab = BaseDeDatos.buscarPorNumero(habitaciones.get(i));
				comprobarHabitacion(hab);
				hab.getFrigobar().cancelarSaldo();
				int indHab = BaseDeDatos.obtenerIndiceHabitacion(habitaciones.get(i));
				comprobarIndice(indHab);
				BaseDeDatos.agregarHabitacionAlIndice(hab, indHab);
			}
		}catch(ReservaNulaException r)
		{
			r.getLocalizedMessage();
		}catch(IndiceIncorrectoException i)
		{
			i.getMessage();
		}
		catch(HabitacionNulaException h)
		{
			h.getMessage();
		}catch(RuntimeException r)
		{
			r.getMessage();
		}
		
	}
	public void cobrar(Reserva reserva, double importe) //recibimos el importe que paga y chekeamos si cancelamos
	{

         if(importe == reserva.calcularMonto())
         {
        	 reserva.confirmarPago();
         }else if(importe < reserva.calcularMonto())
         {
        	 reserva.descontarSaldo(importe);
         }
		
		
	}
	public void cancelarReserva(int numeroReserva)
	{
		
		try {
			Reserva reservita = BaseDeDatos.obtenerReserva(numeroReserva);
			comprobarReserva(reservita);
			ArrayList<String> habitaciones = reservita.getNumerosHabitaciones();
			
			for(int i = 0; i < habitaciones.size(); i ++)
			{
				Habitacion hab = BaseDeDatos.buscarPorNumero(habitaciones.get(i));
				comprobarHabitacion(hab);
				int indice = BaseDeDatos.obtenerIndiceHabitacion(habitaciones.get(i));
				comprobarIndice(indice);
				hab.desocupar(reservita.getFechaIngreso(), reservita.getFechaEgreso());
				BaseDeDatos.agregarHabitacionAlIndice(hab, indice);
			}
			}catch(ReservaNulaException r)
			{
				r.getMessage();
			}catch(HabitacionNulaException h)
			{
				h.getMessage();
			}catch(IndiceIncorrectoException i)
			{
				i.getMessage();
			}catch(RuntimeException r)
		    {
				r.getMessage();
		    }
		
	}
			
	
	
	public void comprobarReserva(Reserva reservita)
	{
		if(reservita == null)
			throw new ReservaNulaException("No existe la reserva solicitada");
	}
	public void comprobarHabitacion(Habitacion hab)
	{
		if(hab == null)
			throw new HabitacionNulaException("Habitacion no existente. ");
	}
	public void comprobarIndice(int indice)
	{
		if (indice == -1)
			throw new IndiceIncorrectoException("Indice incorrecto en la busqueda de habitacion o de reserva");
	}
	public ArrayList<String> verDisponibles()
	{
		ArrayList<String> disponibles = BaseDeDatos.buscarNumerosDeDisponibles();
		return disponibles;
	}
	public ArrayList<String> verOcupadas()
	{
		ArrayList<String> habitaciones = BaseDeDatos.buscarNumerosDeNoDisponibles();
		return habitaciones;
	}
	
	
	
	
	
	
}
