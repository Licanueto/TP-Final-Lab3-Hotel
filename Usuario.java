package Clases;

public abstract class  Usuario {
	
	private String dni;
	private String nombre;
	private String apellido;
	private boolean estadoDeAlta;
	
	public Usuario()
	{
		dni = " ";
		nombre =" ";
		apellido = " ";
	}
	public Usuario(String dni)
	{
		this.dni = dni;
		nombre = " ";
		apellido = " ";
	}
	public Usuario(String dni, String apellido)
	{
		this.dni = dni;
		nombre = " ";
		this.apellido = apellido;
	}
	
	public Usuario(String dni, String nombre, String apellido)
	{
		this.dni = dni;
		this.nombre = nombre;
		this.apellido = apellido;
		this.estadoDeAlta = true;
		
	}
	public void setDni(String dni)
	{
		this.dni = dni;
	}
	public String getDni()
	{
		return dni;
	}
	public void setNombre(String nombre)
	{
		this.nombre = nombre;
	}
	public String getNombre()
	{
		return nombre;
	}
	public void setApellido(String apellido)
	{
		this.apellido = apellido;
	}
	public String getApellido()
	{
		return apellido;
	}
	public void ponerEnBaja(){
		if(estadoDeAlta){
			estadoDeAlta = false;
		}
		//else.... avisar o lanzar excepcion....
	}
	
	@Override
	public String toString() {
		
		return "\nDNI: " + dni + "\nNombre: " + nombre + "\nApellido: " + apellido;
	}
	
	//public abstract int reservar()
	
	//public abstract void cancelarReserva(int idNumeroReserva) 
	
	
	
	

}
