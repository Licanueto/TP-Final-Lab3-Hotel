/* Todos los retornos boolean son para operación exitosa como para poner algo, modificarlos libremente */

Package Clases;

public class Concerje extends Usuario implements IabmHabitacion, IabmUsuario {
	
	
	private Array<Habitacion> habitaciones;
	
	// Implementación de Interfaces
	public void darDeAltaHab() {
		
	}
	public void dardeBajaHab() {
		
	}
	public void darDeAltaUsuario() {
		
	}
	public void darDeBajaUsuario() {
		
	}
	
	
	// Métodos
	public boolean realizarCheck-in (reserva){
		boolean checkInExitoso = false;
		
		
		return checkInExitoso;
	}

	public boolean realizarCheck-out (reserva){
		boolean checkOutExitoso = false;
		
		
		return checkOutExitoso;
	}

	public int reservar (dni,fechaIngreso,fechaEgreso,habitaciones[]) {
		int idNuevaReserva;
		
		
		return idNuevaReserva;
	}

	public boolean cancelarReserva (id) {
		boolean reservaCanceladaExitosamente = false;
		
		
		return reservaCanceladaExitosamente;
	}

	public boolean cargarPasajero(nombre,apellido,dni,telefono,email,ciudad,domicilio) {
		boolean pasajeroCargadoExitosamente;
		
		
		return pasajeroCargadoExitosamente;
	}

	public ArrayList buscarDisponibles(fechaIngreso,fechaEgreso,cantPasajeros) {
		ArrayList disponibles = new ArrayList();
		
		
		return disponibles;
	}

	public Array<Habitacion> verDisponibles(){
		
		
		
		return disponibles
	}

	public Array<Habitacion> verOcupadas (){
		
	}

	public double cobrar (reserva, importeACobrar) {
		
	}

	public String consultaHab (nroHabitacion) { // Este método probablemente haya que sobrecargarlo
		
	}
	
	@Override
	public String toString() {
		return super.toString();
	}
	
}
