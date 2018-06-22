package Clases;


import net.time4j.PlainDate;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.ArrayList;
import java.util.HashMap;

public final class BaseDeDatos {


    //CON EL CONSTRUCTOR PRIVADO, ESTO HACE QUE NO PUEDA SER INSTANCIADA

    //private static BaseDeDatos baseDeDatos;

    private static ArrayList<Habitacion> habitaciones;
    private static HashMap<String,Pasajero> pasajeros;
    private static ArrayList<Reserva> reservas;
    private static HashMap<String,Concerje> concerjes;
    private static HashMap<String,Administrador> administradores;

    private BaseDeDatos(){
        habitaciones = new ArrayList<>();
        pasajeros = new HashMap<>();
        reservas = new ArrayList<>();
        concerjes = new HashMap<>();
        administradores = new HashMap<>();
    }

    


    // ****VER LUEGO TRATAMIENTO DE EXCEPCIONES EN LOS METODOS QUE LEVANTAN LOS ARCHIVOS****
    public static void levantarHabitaciones() {
        ObjectInputStream lectura = null;
        Habitacion auxiliar;
        try {
            lectura = new ObjectInputStream(new FileInputStream("habitaciones.dat"));

            while(lectura.read() != -1) {
                auxiliar = (Habitacion) lectura.readObject();
                agregarHabitacion(auxiliar);
            }
        }
        catch (FileNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        catch (ClassNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        finally {
            try {
                lectura.close();
            }
            catch (IOException e) {
                // TODO: handle exception
            }
        }

    }

    public static void levantarPasajeros() {
        ObjectInputStream lectura = null;
        Pasajero auxiliar;
        try {
            lectura = new ObjectInputStream(new FileInputStream("pasajeros.dat"));

            while(lectura.read() != -1) {
                auxiliar = (Pasajero) lectura.readObject();
                agregarPasajero(auxiliar);
            }
        }
        catch (FileNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        catch (ClassNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        finally {
            try {
                lectura.close();
            }
            catch (IOException e) {
                // TODO: handle exception
            }
        }
    }

    public static void levantarReservas() {
        ObjectInputStream lectura = null;
        Reserva auxiliar;
        try {
            lectura = new ObjectInputStream(new FileInputStream("reservas.dat"));
            while (lectura.read() != -1) {
                auxiliar = (Reserva) lectura.readObject();
                agregarReserva(auxiliar);
            }
        }
        catch (FileNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        catch (ClassNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        finally {
            try {
                lectura.close();
            }
            catch (IOException e) {
                // TODO: handle exception
            }
        }

    }


    /////////// METODOS PARA HABITACIONES //////////////////////////////////////////////////////

    public static void agregarHabitacion(Habitacion habitacion){
        habitaciones.add(habitacion);
    }
    public static Habitacion buscarPorNumero(String numHab){
        for(Habitacion habitacion: habitaciones){
            if(habitacion.getNumHabitacion().equals(numHab)){
                return  habitacion;
            }
        }
        return null;
    }

    public static ArrayList<Habitacion> obtenerHabitaciones(){
        return habitaciones;
    }

    public static ArrayList<Habitacion> obtenerLibres(){   //devolver arraylist de string con las libres
        ArrayList<Habitacion> disponibles = new ArrayList<>();
        for(Habitacion habitacion: habitaciones){
            if(habitacion.isDisponible() && !habitacion.isOcupada()){
                disponibles.add(habitacion);
            }
        }
        return disponibles;
    }

    public static ArrayList<Habitacion> buscarPorCapacidad(byte numero){
        ArrayList<Habitacion> capacidadBuscada = new ArrayList<>();
        for(Habitacion habitacion: habitaciones){
            if(habitacion.getCapacidad() == numero){
                capacidadBuscada.add(habitacion);
            }
        }
        return capacidadBuscada;
    }
    public static ArrayList<Habitacion> buscarPorTipo(String tipoHab){
        ArrayList<Habitacion> tipoBuscado = new ArrayList<>();
        for(Habitacion habitacion: habitaciones){
            if(habitacion.getTipo().equalsIgnoreCase(tipoHab)){
                tipoBuscado.add(habitacion);
            }
        }
        return tipoBuscado;
    }
    public static ArrayList<Habitacion> buscarPrecioMenorA(double precioMax){
        ArrayList<Habitacion> precioBuscado = new ArrayList<>();
        for(Habitacion habitacion: habitaciones){
            if(habitacion.getPrecioDiario() <= precioMax){
                precioBuscado.add(habitacion);
            }
        }
        return precioBuscado;
    }
    public static ArrayList<String> listarHabitaciones(){
        ArrayList<String> listaHabitaciones = new ArrayList<>();
        for(Habitacion habitacion : habitaciones){
            listaHabitaciones.add(habitacion.mostrarHabitacion());
        }
        return listaHabitaciones;
    }

    public static void quitarHabitacion(String numero){
        for(int i = 0; i < habitaciones.size(); i++){
            if(habitaciones.get(i).getNumHabitacion().equalsIgnoreCase(numero)){
                habitaciones.remove(habitaciones.get(i));
            }
            if(i == habitaciones.size()){
                //lanzar una excepcion custom cuando no encuentra la habitacion de ese numero
            }
        }
    }
    public static void quitarHabitacion(Habitacion habitacion){
        habitaciones.remove(habitacion);
    }

    public static double obtenerTarifa(String numHabitacion) {
        for(int i = 0; i < habitaciones.size(); i++) {
            if(habitaciones.get(i).getNumHabitacion().equals(numHabitacion)) {
                return habitaciones.get(i).getPrecioDiario();
            }
        }
        return -1;
    }

    public static double obtenerSaldoFrigobar(String numHabitacion) {
        for(int i = 0; i < habitaciones.size(); i++) {
            if(habitaciones.get(i).getNumHabitacion().equals(numHabitacion)) {
                return habitaciones.get(i).getFrigobar().getSaldo();
            }
        }
        return -1;
    }
    public static boolean hayCapacidad(int cantPasajeros) {
        int capacidadTotal = 0;
        for(int i = 0; i < habitaciones.size(); i++) {
            capacidadTotal += habitaciones.get(i).getCapacidad();
        }
        if(capacidadTotal > cantPasajeros) {
            return true;
        }
        return false;
    }

    public static ArrayList<Habitacion> buscarAptas(PlainDate ingreso, PlainDate egreso){
        ArrayList<Habitacion> aptas = new ArrayList<>();
        for(int i = 0; i < habitaciones.size(); i++) {
            if(habitaciones.get(i).isDisponible(ingreso,egreso) ) {
                aptas.add(habitaciones.get(i));
            }
        }
        return aptas;
    }
   
    public static ArrayList<String> buscarNumerosDeDisponibles(){
        ArrayList<String> lista = new ArrayList<>();
        for(int i = 0; i < habitaciones.size();i++){
            if(habitaciones.get(i).isDisponible() ){
                lista.add(habitaciones.get(i).getNumHabitacion());
            }
        }
        return lista;
    }
    
    public static ArrayList<String> buscarNumerosDeNoDisponibles(){
        ArrayList<String> lista = new ArrayList<>();
        for(int i = 0; i < habitaciones.size(); i++){
            if(!habitaciones.get(i).isDisponible()){
                lista.add(habitaciones.get(i).getNumHabitacion());
            }
        }
        return lista;
    }
    public static int obtenerIndiceHabitacion(String numeroHab){
        int index = -1;
        for(int i = 0; i < habitaciones.size(); i++){
            if(numeroHab.equals(habitaciones.get(i).getNumHabitacion())){
                index = i;
            }
        }
        return index;
    }
    public static void agregarHabitacionAlIndice(Habitacion habitacion,int indice){
        habitaciones.add(indice,habitacion );
    }




    ///////////         METODOS DE PASAJEROS       ////////////////////////////////////////
    public static void agregarPasajero(Pasajero pasajero){
        pasajeros.put(pasajero.getDni(),pasajero);

    }

    public static HashMap<String,Pasajero> obtenerPasajeros(){
        return pasajeros;
    }

    public static  void quitarPasajero(String numDoc){
        pasajeros.remove(numDoc);
    }

    public static Pasajero buscaPasajeroporDni(String dniPasajero){
        Pasajero buscado = pasajeros.get(dniPasajero);
        return buscado;
    }

    public static String buscaNombreYapellido(String dniPasajero){
        String ApellidoYnombre = pasajeros.get(dniPasajero).getApellido() + " " +  pasajeros.get(dniPasajero).getNombre();
        return ApellidoYnombre;

    }

    public static String buscaTelefonoPorDni(String dniPasajero){
        String telefono = pasajeros.get(dniPasajero).getTelefonoMovil();
        return telefono;
    }

//////////////        METODOS DE RESERVAS   ////////////////////////////

    public static void agregarReserva(Reserva elemento) {
        reservas.add(elemento);
    }

    public static ArrayList<Reserva> obtenerReservas(){
        return reservas;
    }

    public static Reserva obtenerReserva(int numero) {
        for(Reserva reserva: reservas){
            if(reserva.getNumeroReserva() == numero) {
                return reserva;
            }
        }
        return null;//capturar un nullpointerexception mas arriba, en el metodo que quiera modificar el retorno
        //o bien una excepcion custom lanzada desde este metodo
    }

    public static int obtenerUltimaReserva() {
        return reservas.get(reservas.size() - 1).getNumeroReserva();
    }
    public static int obtenerIndiceReserva(int numeroReserva){
        int index = -1;
        for(int i = 0; i < reservas.size(); i++){
            if(numeroReserva == reservas.get(i).getNumeroReserva()){
                index = i;
            }
        }
        return index;
    }
    public static void agragarReservaAlIndice(int indice,Reserva reserva){
        reservas.add(indice,reserva );
    }


/////////// METODOS DE coNCERJES ////////////////////////

    public static void agregarConcerje(Concerje concerje) {
        concerjes.put(concerje.getDni(),concerje);
    }

    public static HashMap<String,Concerje> obtenerConcerjes(){
        return concerjes;
    }

    public static Concerje obtenerUnConcerje(String dni) {
        Concerje aux = concerjes.get(dni);
        return aux;
    }
///////////ADMINISTRADORES////////////////////////
    public static void agregarAdministrador(Administrador administrador){
        administradores.put(administrador.getDni(),administrador );
    }

    public static HashMap<String, Administrador> obtenerAdministradores() {
        return administradores;
    }
    public static Administrador obtenerAdministrador(String dni){
        return administradores.get(dni);
    }
    //FALTA TERMINAR.............
}
