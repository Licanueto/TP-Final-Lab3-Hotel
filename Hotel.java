
public class Hotel {
	
	private String nombre;
        private String direccion;
     	private String CUIT;
        private BaseDeDatos<Concerje> concerjes;
        private BaseDeDatos<Habitacion> habitaciones;
        private BaseDeDatos<Pasajero> pasajeros;
        private BaseDeDatos<Reserva> reservas;

     
     public Hotel(String nombre,String direccion,String CUIT){
        this.nombre = nombre;
        this.direccion = direccion;
	this.CUIT = CUIT;
        concerjes = new BaseDeDatos<>();
        habitaciones = new BaseDeDatos<>();
        pasajeros = new BaseDeDatos<>();
        reservas = new BaseDeDatos<>();
    }

}
