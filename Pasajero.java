ppackage Clases;

import java.util.ArrayList;

import org.json.JSONException;
import org.json.JSONObject;

public class Pasajero extends Usuario{
	
	private String telefonoMovil;
	private String email;
	private String ciudadDeOrigen;
	private String domicilioOrigen;
	private ArrayList<Reserva>historial;
	private boolean checkIn;
	
	public Pasajero()
	{
		super();
		telefonoMovil =" ";
		email = " ";
		ciudadDeOrigen = " ";
		domicilioOrigen = " ";
		historial = new ArrayList<Reserva>();
		checkIn = false;
	}
	
	
	public Pasajero(String dni, String nombre, String apellido, String telefonoMovil, String email, String ciudadDeOrigen, String domicilioOrigen)
	{
		super(dni, nombre, apellido);
		this.telefonoMovil = telefonoMovil;
		this.email= email;
		this.ciudadDeOrigen = ciudadDeOrigen;
		this.domicilioOrigen = domicilioOrigen;
		historial = new ArrayList<Reserva>();
		checkIn = false;
	}
	 
	
	public Pasajero(String dni, String apellido, String telefonoMovil, String ciudadDeOrigen, String domicilioOrigen)
	{
		super(dni, apellido);
		this.telefonoMovil = telefonoMovil;
		this.ciudadDeOrigen = ciudadDeOrigen;
		this.domicilioOrigen = domicilioOrigen;
		historial = new ArrayList<Reserva>();
		checkIn = false;
	}
	
	
	
	public Pasajero(String dni, String telefonoMovil, String ciudadDeOrigen, String domicilioOrigen)
	{
		super(dni);
		this.telefonoMovil = telefonoMovil;
		this.ciudadDeOrigen = ciudadDeOrigen;
		this.domicilioOrigen = domicilioOrigen;
		historial = new ArrayList<Reserva>();
		checkIn = false;
	}
	
	public void setTelefonoMovil(String telefonoMovil)
	{
		this.telefonoMovil = telefonoMovil;
	}
	public String getTelefonoMovil()
	{
		return telefonoMovil;
	}
	public void setEmail(String email)
	{
		this.email = email;
	}
	public String getEmail()
	{
		return email;
	}
	public void setciudadDeOrigen(String ciudadDeOrigen)
	{
		this.ciudadDeOrigen = ciudadDeOrigen;
	}
	public String getCiudadDeOrigen()
	{
		return ciudadDeOrigen;
	}
	public void setDomicilioDeOrigen(String domicilioOrigen)
	{
		this.domicilioOrigen = domicilioOrigen;
	}
	public String getDomicilioOrigen()
	{
		return domicilioOrigen;
	}
	public ArrayList<Reserva> getHistorial()
	{
		return historial;
	}
	
	public boolean getChekIn()
	{
		return checkIn; 
	}
	
	public String obtenerEstadoCheckIn()
	{
		String respuesta;
		if (checkIn == true)
			respuesta = "Check In realizado";
		else
			respuesta = "Check In sin realizar";
		return respuesta;
			
			
	}
	
	
	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return super.toString() + "\nTelefono: " + telefonoMovil + "\nE-mail: " + email + "\nCiudad de Origen: " + ciudadDeOrigen +
				"\nDomicilio de Origen: " + domicilioOrigen + "\nEstado de Check In: " + obtenerEstadoCheckIn();
	}
	
	//El siguiente método nos devuelve en un String el historial del pasajero
	String obtenerHistorial()
	{
		int longitud = historial.size();
		StringBuffer historia = new StringBuffer();
		for(int i = 0; i < longitud; i++)
		{
			historia.append(historial.get(i).toString());
		}
		String historialPasajero =  historia.toString();
		
		
		return historialPasajero;
		
	}
	
	//El siguiente método agrega una reserva al historial del pasajero si es que éste hizo efectiva la reserva, es decir realizó el chek in
	
	public void agregarReservaAlHistorial(Reserva reser)
	{
		if (checkIn == true)
		{
			historial.add(reser);
		}
	}
	
	JSONObject getJson() throws JSONException 
	{
		JSONObject obj = new JSONObject();
		obj.put("nombre", getNombre());
		obj.put("apellido", getApellido());
		obj.put("dni", getDni());
		obj.put("telefonoMovil", getTelefonoMovil());
		obj.put("email", getEmail());
		obj.put("ciudadDeOrigen", getCiudadDeOrigen());
		obj.put("domicilioOrigen", getDomicilioOrigen());
		
		return obj;
		
			
	}
	
	
	
}
