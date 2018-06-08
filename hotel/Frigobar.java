package hotel;

import java.util.*;
import java.io.*;



public class Frigobar {
	
	private double saldo;
	private static HashMap<String,Double> productos;
	
	public Frigobar() {
		this.saldo = 0;
		productos = new HashMap<>();
		agregarAlInventario();
	}
	
	
	public double getSaldo() {
		return saldo;
	}
	
	public void agregarAlInventario() {
		productos.put("Coca Cola",30.5);
		productos.put("Agua mineral", 30.0);
		productos.put("Chocolate",25.0);
		productos.put("Cerveza",42.5);
	}
	
	public void consumirProduto(String producto,byte cantidad) {
		saldo += productos.get(producto) * cantidad;
	}
	
	public void cancelarSaldo() {
		saldo = 0;
	}

}
