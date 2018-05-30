
public class Habitacion {
	
	 private String numHabitacion;
	    private byte capacidad;
	    private String tipo;
	    private double precioDiario;
	    private String estado;
	    private Frigobar frigobar;
	    private boolean disponible;
	    private boolean ocupada;

	    public Habitacion(String numHabitacion){ //crear habitacion solo con  el numero despues se ve el resto
	        this.numHabitacion = numHabitacion;
	        frigobar = new Frigobar();
	        this.disponible = true;
	        this.ocupada = false;
	    }

	    public Habitacion(String numHabitacion,byte capacidad,String tipo,double precioDiario,String estado,
	                      boolean disponible,boolean ocupada){
	        this.numHabitacion = numHabitacion;
	        this.capacidad = capacidad;
	        this.tipo = tipo;
	        this.precioDiario = precioDiario;
	        this.estado = estado;
	        frigobar = new Frigobar(); //ver el tema de parametros
	        this.disponible = true;
	        this.ocupada = false;
	    }

	    public void setCapacidad(byte capacidad) {

	        this.capacidad = capacidad;
	    }

	    public void setTipo(String tipo) {

	        this.tipo = tipo;
	    }

	    public void setPrecioDiario(double precioDiario) {

	        this.precioDiario = precioDiario;
	    }

	    public void setEstado(String estado) {

	        this.estado = estado;
	    }

	    public String getNumHabitacion() {

	        return numHabitacion;
	    }

	    public byte getCapacidad() {

	        return capacidad;
	    }

	    public String getTipo() {

	        return tipo;
	    }

	    public double getPrecioDiario() {

	        return precioDiario;
	    }

	    public String getEstado() {

	        return estado;
	    }

	    public boolean isDisponible() {

	        return disponible;
	    }

	    public boolean isOcupada() {

	        return ocupada;
	    }

	    public void ocupar(){
	        if(!ocupada){
	            ocupada = true;
	        }
	    }
	    public void desocupar(){
	        if(ocupada){
	            ocupada = false;
	        }
	    }
	    public void habilitar(){
	        if(!disponible){
	            disponible = true;
	        }
	    }
	    public void deshabilitar(){
	        if(disponible){
	            disponible = false;
	        }
	    }

	    @Override
	    public String toString() {
	        return "\nNumero de habitacion: " + numHabitacion + "\nCapacidad: " + capacidad + " pasajeros" + "\nTipo: " +
	                tipo + "\nPrecio diario: " + precioDiario + " pesos" + "\nEstado: " + estado + "\n";
	    }

	    public String mostrarHabitacion(){
	        String estaOcupada;
	        String estaDisponible;

	        if(ocupada){
	            estaOcupada = "Ocupada";
	        }
	        else{
	            estaOcupada = "Libre";
	        }
	        if(disponible){
	            estaDisponible = "Disponible";
	        }
	        else {
	            estaDisponible = "No disponible";
	        }

	        return toString() + "\n" + estaDisponible + "\n" + estaOcupada ;
	    }

}
