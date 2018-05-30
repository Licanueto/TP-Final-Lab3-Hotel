package Clases;

public class Pasajero extends Usuario{
	
	private String telefonoMovil;
	private String email;
	private String ciudadDeOrigen;
	private String domicilioOrigen;
	//private ArrayList<Reserva>historial;
	private boolean checkIn;
	
	public Pasajero()
	{
		super();
		telefonoMovil =" ";
		email = " ";
		ciudadDeOrigen = " ";
		domicilioOrigen = " ";
		//historial = new ArrayList<Reserva>();
		checkIn = false;
	}
	
	//el ArrayList y checkIn no creo que deban pasarse por parametro.
	public Pasajero(String dni, String nombre, String apellido, String telefonoMovil, String email, String ciudadDeOrigen, String domicilioOrigen)
	{
		super(dni, nombre, apellido);
		this.telefonoMovil = telefonoMovil;
		this.email= email;
		this.ciudadDeOrigen = ciudadDeOrigen;
		this.domicilioOrigen = domicilioOrigen;
		//historial = new ArrayList<Reserva>();
		checkIn = false;
	}
	//opcion suponiendo que no tenemos el nombre porque no quizo darlo y no tiene email porque es una persona mayor. El resto de los datos deberia tenerlos. 
	
	public Pasajero(String dni, String apellido, String telefonoMovil, String ciudadDeOrigen, String domicilioOrigen)
	{
		super(dni, apellido);
		this.telefonoMovil = telefonoMovil;
		this.ciudadDeOrigen = ciudadDeOrigen;
		this.domicilioOrigen = domicilioOrigen;
		//historial = new ArrayList<Reserva>();
		checkIn = false;
	}
	
	// Tenemos su dni, no tenemos ni su nombre ni apellido ni email pero igual podemos identificarlo y contactarlo.
	
	public Pasajero(String dni, String telefonoMovil, String ciudadDeOrigen, String domicilioOrigen)
	{
		super(dni);
		this.telefonoMovil = telefonoMovil;
		this.ciudadDeOrigen = ciudadDeOrigen;
		this.domicilioOrigen = domicilioOrigen;
		//historial = new ArrayList<Reserva>();
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
	/*public ArrayList<Reserva> getHistorial()
	{
		return historial;
	}
	*/
	public boolean getChekIn()
	{
		return checkIn; //retorna el estado de checkIn.
	}
	
	
	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return super.toString() + "\nTelefono: " + telefonoMovil + "\nE-mail: " + email + "\nCiudad de Origen: " + ciudadDeOrigen +
				"\nDomicilio de Origen: " + domicilioOrigen + "\nEstado de Check In: " + checkIn;
	}
	
	
	/////////////////////////////////////////////ATENCION ///////////////////////////////////////////////////////////
	//
	//HABRIA QUE EVALUAR LA POSIBILIDAD DE QUE MUESTRE SU HISTORIAL
	//
	////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	
	//public void agregarReserva(Reserva reserva)
	//public JSON listar()
	//public void buscar(int numeroReserva)
	//public int reservar()
	
	
	
	

}
