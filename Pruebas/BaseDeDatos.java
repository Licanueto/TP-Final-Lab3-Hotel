package Clases;

import java.util.ArrayList;
import java.util.HashMap;

public class BaseDeDatos {

    //PARA CREAR LA BASE DE DATOS SERIA: BaseDeDatos baseDeDatosUnica = BaseDeDatos.getSingletonInstance();
    //
    //CON EL CONSTRUCTOR PRIVADO, ESTO HACE QUE PUEDA SER INSTANCIADA SOLO UNA VEZ.

    private static BaseDeDatos baseDeDatos;

    private ArrayList<Habitacion> habitaciones;
    private HashMap<String,Pasajero> pasajeros;
    private ArrayList<Reserva> reservas;

    private BaseDeDatos(){
        habitaciones = new ArrayList<>();
        pasajeros = new HashMap<>();
        reservas = new ArrayList<>();
    }

    public static BaseDeDatos getSingletonInstance(){ //se podria tambien lanzar una excepcion  en caso de entrar al else
        if(baseDeDatos == null){
            baseDeDatos = new BaseDeDatos();
        }
        else{
            System.out.println("No se puede crear mas de una base de datos");
        }
        return baseDeDatos;
    }

    //PREVIENE QUE LA BASE DE DATOS SEA CLONADA
    @Override
    protected Object clone(){
            try {
                throw new CloneNotSupportedException();
            } catch (CloneNotSupportedException ex) {
                System.out.println("No se puede clonar un objeto de la clase BaseDeDatos");
            }
            return null;
    }

    /////////// METODOS PARA HABITACIONES //////////////////////////////////////////////////////

    public void agregarHabitacion(Habitacion habitacion){
        habitaciones.add(habitacion);
    }
    public Habitacion buscarPorNumero(String numHab){
        for(Habitacion habitacion: habitaciones){
            if(habitacion.getNumHabitacion().equals(numHab)){
               return  habitacion;
            }
        }
        return null;
    }
    public ArrayList<Habitacion> obtenerLibres(){
        ArrayList<Habitacion> disponibles = new ArrayList<>();
        for(Habitacion habitacion: habitaciones){
            if(habitacion.isDisponible() && !habitacion.isOcupada()){
                disponibles.add(habitacion);
            }
        }
        return disponibles;
    }

    public ArrayList<Habitacion> buscarPorCapacidad(byte numero){
        ArrayList<Habitacion> capacidadBuscada = new ArrayList<>();
        for(Habitacion habitacion: habitaciones){
            if(habitacion.getCapacidad() == numero){
                capacidadBuscada.add(habitacion);
            }
        }
        return capacidadBuscada;
    }
    public ArrayList<Habitacion> buscarPorTipo(String tipoHab){
        ArrayList<Habitacion> tipoBuscado = new ArrayList<>();
        for(Habitacion habitacion: habitaciones){
            if(habitacion.getTipo().equalsIgnoreCase(tipoHab)){
                tipoBuscado.add(habitacion);
            }
        }
        return tipoBuscado;
    }
    public ArrayList<Habitacion> buscarPrecioMenorA(double precioMax){
        ArrayList<Habitacion> precioBuscado = new ArrayList<>();
        for(Habitacion habitacion: habitaciones){
            if(habitacion.getPrecioDiario() <= precioMax){
                precioBuscado.add(habitacion);
            }
        }
        return precioBuscado;
    }
    public ArrayList<String> listarHabitaciones(){
        ArrayList<String> listaHabitaciones = new ArrayList<>();
        for(Habitacion habitacion : habitaciones){
            listaHabitaciones.add(habitacion.mostrarHabitacion());
        }
        return listaHabitaciones;
    }

    public void quitarHabitacion(String numero){
        for(int i = 0; i < habitaciones.size(); i++){
            if(habitaciones.get(i).getNumHabitacion().equalsIgnoreCase(numero)){
                habitaciones.remove(habitaciones.get(i));
            }
            if(i == habitaciones.size()){
                //lanzar una excepcion custom cuando no encuentra la habitacion de ese numero
            }
        }
    }
    public void quitarHabitacion(Habitacion habitacion){
        habitaciones.remove(habitacion);
    }

    //////////////////////////////////////////////////////////////////////////
    public void agregarPasajero(Pasajero pasajero){
        pasajeros.put(pasajero.getDni(),pasajero);
    }

    public void quitarPasajero(String numDoc){
            pasajeros.remove(numDoc);
    }

    public Pasajero buscaPasajeroDni(String dniPasajero){
        Pasajero buscado = pasajeros.get(dniPasajero);
        return buscado;
    }

    public String buscaNombreYapellido(String dniPasajero){
        String ApellidoYnombre = pasajeros.get(dniPasajero).getApellido() + " " +  pasajeros.get(dniPasajero).getNombre();
        return ApellidoYnombre;

    }

    public String buscaTelefonoPorDni(String dniPasajero){
        String telefono = pasajeros.get(dniPasajero).getTelefonoMovil();
        return telefono;
    }
}
//FALTA TERMINAR.............
