package Clases;

import java.util.*;
import java.io.*;

/**
 * Esta clase define los comportamientos b�sicos de un frigobar de la habitaci�n de un hotel, lugar en donde el "Frigobar" se encuentra enmarcado.
 * El frigobar posee solo dos atributos:
 * @param saldo El saldo acumulado del frigobar. Este inicia en cero al comienzo de la estad�a, acumula lo que se vaya consumiendo y vuelve a cero cuando el pasajero abona.
 * @param productos Es un HashMap que contiene el los productos ofrecidos en el frigobar en forma de String y el precio de cada uno en forma de Double.
 *
 */
public class Frigobar {
		
		private double saldo;
		private static HashMap<String,Double> productos;

		/**
		 * Constructor de Frigobar. Inicia con el saldo en 0 y el listado y valor de los productos cargados por defecto.
		 */
		public Frigobar() {
			this.saldo = 0;
			productos = new HashMap<>();
			agregarAlInventario();
		}
		
		/**
		 * Devuelve el saldo acumulado hasta el momento en el Frigobar.
		 * @return Saldo acumulado
		 */
		public double getSaldo() {
			return saldo;
		}
		/**
		 * Agrega un listado harcodeado de tanto productos como sus respectivos valores al inventario
		 */
		public void agregarAlInventario() {// Los productos se encuentran escritos con minuscula ya que en consumirProducto el
			productos.put("coca cola",30.5);// par�metro recibido se interpreta en minuscula sin importar como haya sido ingresado
			productos.put("agua mineral", 30.0);
			productos.put("chocolate",25.0);
			productos.put("cerveza",42.5);
		}
		/**
		 * Carga el equivalente del valor consumido por el pasajero al saldo.
		 * @param producto Producto consumido, estos pueden ser:  Coca Cola | Agua mineral | Chocolate | Cerveza
		 * @param cantidad Cantidad de unidades consumidas.
		 */
		public void consumirProduto(String producto,byte cantidad) {
			String productoConsumido = producto.toLowerCase(); // Lo convierte a minuscula para asegurarse de que est� bien escrito
			saldo += productos.get(productoConsumido) * cantidad;
		}
		/**
		 * Vuelve el saldo del frigobar a cero, �til al momento de pagar.
		 * N�tese que este m�todo no devuelve la cantidad saldada as� que para mantener la informaci�n previamente se deber� ejecutar getSaldo() para mantener esa informaci�n.
		 */
		public void cancelarSaldo() {
			saldo = 0;
		}

}


