package App;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;

import Clases.Concerje;
import Clases.Contenedora;
import Clases.Hotel;
import Clases.Reserva;

public class Main {

	public static void main(String[] args) {
		
		Contenedora contenedora = new Contenedora();
		
		Concerje concerje1 = new Concerje();
		
		
		
		concerje1.reservar("29754703", 15);
		concerje1.reservar("297588803", 1);
		concerje1.reservar("7878", 10);
		concerje1.reservar("254543", 21);
		concerje1.reservar("2975443", 17);
		
		contenedora.mostrarContenedora();
		
		
		
		
		
		
		

		
		
		
		
		
		
	

}
}
