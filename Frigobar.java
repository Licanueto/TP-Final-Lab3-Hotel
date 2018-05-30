
public class Frigobar {
	
	 /**
     * Hoy deciamos con Lisandro de agregarle distintos productos al frigobar asi se puede manejar mas claro, por
     * ejemplo: private double cocacola = 24,50; y eso se puede ir manejando con un metodo que sea consumir o algo
     * por el estilo que setee el valor a 0 y lo acumule en saldoDebido, asi nos evitamos manejar 3 variables que estan
     * bastante confusas.
     * O sino directamente trabajar con 2 variables, 1 que sea el total de valor de los productos en el frigobar y lo
     * que se consume se resta de esa variable y se acumula en saldoDebido directamente.
     */

    private double cantidad; //lo que vale lo que esta en el frigobar
    private double precio;   //lo que se va consumiendo(se debe)
    private double saldoDebido; //acumulador periodico para precio(lo que se debe cuando se recarga el frigobar)

    public Frigobar(){
        this.cantidad = 0;
        this.precio = 0;
        this.saldoDebido = 0;
    }

    public Frigobar(double cantidad){ //podemos crearlo ya lleno o no?? ver eso
        this.cantidad = cantidad;
        this.precio = 0;
        this.saldoDebido = 0;
    }

    public double calularTotalApagar(){
        if (precio == 0) {
            return saldoDebido;
        }
        else{
            saldoDebido += precio;
            precio = 0;
            return saldoDebido;
        }
    }
    public void rellenarFrigobar(double cantidad){
        saldoDebido += precio;
        precio = 0;
        this.cantidad = cantidad;
    }

}
