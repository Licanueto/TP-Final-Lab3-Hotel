package Clases;


import net.time4j.PlainDate;

import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

/**
 * Clase que almacena toda la informacion acerca de los usuarios del sistema, habitaciones y reservas en distintas colecciones.
 * La funcionalidad de la clase es agregar, almacenar, buscar segun distintos parametros y retornar diferentes datos, segun lo soliciten
 * los usuarios.
 * La clase es final, su constructor privado y sus atributos y metodos son estaticos.
 * Al momento de comenzar el programa la informacion se levanta desde los archivos correspondientes.
 */
public final class BaseDeDatos implements Serializable{


    //CON EL CONSTRUCTOR PRIVADO, ESTO HACE QUE NO PUEDA SER INSTANCIADA

    private static ArrayList<Habitacion> habitaciones = new ArrayList<>();
    private static HashMap<String,Pasajero> pasajeros = new HashMap<>();
    private static ArrayList<Reserva> reservas = new ArrayList<>();
    private static HashMap<String,Concerje> concerjes = new HashMap<>();
    private static HashMap<String,Administrador> administradores = new HashMap<>();


    /**
     * Constructor de BaseDeDatos. Notese que es privado y por ende la clase no puede ser instanciada.
     */
    private BaseDeDatos(){
        habitaciones = new ArrayList<>();
        pasajeros = new HashMap<>();
        reservas = new ArrayList<>();
        concerjes = new HashMap<>();
        administradores = new HashMap<>();
    }

    /**
     * Lee un archivo "habitaciones.dat" y carga sus objetos - las habitaciones - en memoria, m�s espec�ficamente a Base de datos.
     */
    // ****VER LUEGO TRATAMIENTO DE EXCEPCIONES EN LOS METODOS QUE LEVANTAN LOS ARCHIVOS****

    public static void levantarAllCollections(){
        levantarPasajeros();
        levantarReservas();
        levantarConcerjes();
        levantarHabitaciones();
        levantarAdministradores();
    }

    public static void levantarHabitaciones() {
        ObjectInputStream lectura = null;

        try {
            File file = new File("habitaciones.dat");
            if(file.exists()) {

                lectura = new ObjectInputStream(new FileInputStream(file));
                habitaciones = (ArrayList<Habitacion>) lectura.readObject();
            }
        }
        catch (FileNotFoundException e) {

            e.printStackTrace();
        }
        catch (ClassNotFoundException e) {

            e.printStackTrace();
        }
        catch (IOException e) {

            e.printStackTrace();
        }
        finally {
                try {
                     lectura.close();
                 }
                 catch (NullPointerException e){
                    e.printStackTrace();
                 }
                catch (IOException e) {
                      e.printStackTrace();
                 }
        }

    }
    /**
     * Lee un archivo "pasajeros.dat" y carga sus objetos - los pasajeros - en memoria, m�s espec�ficamente a Base de datos.
     */
    public static void levantarPasajeros() {
        ObjectInputStream lectura = null;

        try {
            File file = new File("pasajeros.dat");
            if(file.exists()) {

                lectura = new ObjectInputStream(new FileInputStream(file));
                pasajeros = (HashMap<String, Pasajero>) lectura.readObject();
            }
        }
        catch (FileNotFoundException e) {

            e.printStackTrace();
        }
        catch (ClassNotFoundException e) {

            e.printStackTrace();
        }
        catch (IOException e) {

            e.printStackTrace();
        }
        finally {
            try {
                lectura.close();
            }
            catch (NullPointerException e){
                e.printStackTrace();
            }
            catch (IOException e) {
                e.printStackTrace();
            }
        }

    }

    public static void levantarConcerjes() {
        ObjectInputStream lectura = null;

        try {
            File file = new File("concerjes.dat");
            if(file.exists()) {

                lectura = new ObjectInputStream(new FileInputStream(file));
                concerjes = (HashMap<String, Concerje>) lectura.readObject();
            }
        }
        catch (FileNotFoundException e) {

            e.printStackTrace();
        }
        catch (ClassNotFoundException e) {

            e.printStackTrace();
        }
        catch (IOException e) {

            e.printStackTrace();
        }
        finally {
            try {
                lectura.close();
            }
            catch (NullPointerException e){
                e.printStackTrace();
            }
            catch (IOException e) {
                e.printStackTrace();
            }
        }

    }


    /**
     * Lee un archivo "reservas.dat" y carga sus objetos - las reservas - en memoria, m�s espec�ficamente a Base de datos.
     */
    public static void levantarReservas() {
        ObjectInputStream lectura = null;

        try {
            File file = new File("reservas.dat");
            if(file.exists()) {

                lectura = new ObjectInputStream(new FileInputStream(file));
                reservas = (ArrayList<Reserva>) lectura.readObject();
            }
        }
        catch (FileNotFoundException e) {

            e.printStackTrace();
        }
        catch (ClassNotFoundException e) {

            e.printStackTrace();
        }
        catch (IOException e) {

            e.printStackTrace();
        }
        finally {
            try {
                lectura.close();
            }
            catch (NullPointerException e){
                e.printStackTrace();
            }
            catch (IOException e) {
                e.printStackTrace();
            }
        }

    }

    public static void levantarAdministradores() {
        ObjectInputStream lectura = null;

        try {
            File file = new File("administradores.dat");
            if(file.exists()) {

                lectura = new ObjectInputStream(new FileInputStream(file));
                administradores = (HashMap<String, Administrador>) lectura.readObject();
            }
        }
        catch (FileNotFoundException e) {

            e.printStackTrace();
        }
        catch (ClassNotFoundException e) {

            e.printStackTrace();
        }
        catch (IOException e) {

            e.printStackTrace();
        }
        finally {
            try {
                lectura.close();
            }
            catch (NullPointerException e){
                e.printStackTrace();
            }
            catch (IOException e) {
                e.printStackTrace();
            }
        }

    }

    /////////// METODOS PARA HABITACIONES //////////////////////////////////////////////////////

    /**
     * Agrega un objeto de tipo Habitacion a BaseDeDatos.
     * @param habitacion objeto de tipo Habitacion.
     */
    public static void agregarHabitacion(Habitacion habitacion){
        habitaciones.add(habitacion);
    }

    public static void eliminarHabitacion(String numero){
        int index;
        for(int i = 0; i < habitaciones.size(); i++){
            if(habitaciones.get(i).getNumHabitacion().equals(numero)){
                index = i;
                habitaciones.remove(i);
                return;
            }
        }
    }

    /**
     * Busca y devuelve una habitaci�n en la base de datos por n�mero.
     * @param numHab N�mero de la habitaci�n a buscar.
     * @return Objeto de tipo Habitacion.
     */
    public static Habitacion buscarPorNumero(String numHab){
        for(Habitacion habitacion: habitaciones){
            if(habitacion.getNumHabitacion().equals(numHab)){
                return  habitacion;
            }
        }
        return null;
    }
    /**
     * Produce una lista de todas las habitaciones del hotel
     * @return Lista en forma de ArrayList de objetos de tipo Habitacion.
     */
    public static ArrayList<Habitacion> obtenerHabitaciones(){
        return habitaciones;
    }

    /**
     * Produce una lista de las habitaciones que se encuentran disponibles para ser ocupadas en el presente momento.
     * @return Lista en forma de ArrayList de objetos de tipo Habitacion.
     */
    public static ArrayList<Habitacion> obtenerLibres(){   //devolver arraylist de string con las libres
        ArrayList<Habitacion> disponibles = new ArrayList<>();
        for(Habitacion habitacion: habitaciones){
            if(habitacion.isDisponible()){
                disponibles.add(habitacion);
            }
        }
        return disponibles;
    }
    /**
     * Realiza una recolecci�n de todas las habitaciones de una determinada capacidad.
     * @param numero Cantidad de pasajeros que las habitaciones buscadas pueden albergar como m�ximo.
     * @return Lista en forma de ArrayList de objetos de tipo Habitacion.
     */
    public static ArrayList<Habitacion> buscarPorCapacidad(byte numero){
        ArrayList<Habitacion> capacidadBuscada = new ArrayList<>();
        for(Habitacion habitacion: habitaciones){
            if(habitacion.getCapacidad() == numero){
                capacidadBuscada.add(habitacion);
            }
        }
        return capacidadBuscada;
    }
    /**
     * Produce y devuelve una lista de todas las habitaciones de determinado tipo que la base de datos posee.
     * @param tipoHab Tipo de habitaci�n de la cual se desea generar la lista.
     * @return Lista en forma de ArrayList de objetos de tipo Habitacion.
     */
    public static ArrayList<Habitacion> buscarPorTipo(String tipoHab){
        ArrayList<Habitacion> tipoBuscado = new ArrayList<>();
        for(Habitacion habitacion: habitaciones){
            if(habitacion.getTipo().equalsIgnoreCase(tipoHab)){
                tipoBuscado.add(habitacion);
            }
        }
        return tipoBuscado;
    }
    /**
     * Genera y devuelve una lista de las habitaciones cuyo precio por noche sea menor o igual a aquel recibido por par�metro.
     * @param precioMax Cota m�xima de precio para realizar la lista.
     * @return Lista en forma de ArrayList de objetos de tipo Habitacion.
     */
    public static ArrayList<Habitacion> buscarPrecioMenorA(double precioMax){
        ArrayList<Habitacion> precioBuscado = new ArrayList<>();
        for(Habitacion habitacion: habitaciones){
            if(habitacion.getPrecioDiario() <= precioMax){
                precioBuscado.add(habitacion);
            }
        }
        return precioBuscado;
    }

    public static boolean existeHabitacion(String numero){
        boolean estaOno = false;
        for(int i = 0; i < habitaciones.size(); i++){
            if(habitaciones.get(i).getNumHabitacion().equals(numero)){
                estaOno = true;
            }
        }
        return estaOno;
    }
    /**
     * Produce una lista del estado actual de todas las habitaciones del hote.
     * @return Lista en forma de ArrayList de Strings del estado de cada habitacion.
     */
    public static ArrayList<String> listarHabitaciones(){
        ArrayList<String> listaHabitaciones = new ArrayList<>();
        for(Habitacion habitacion : habitaciones){
            listaHabitaciones.add(habitacion.mostrarHabitacion());
        }
        return listaHabitaciones;
    }
    /**
     * Elimina una habitaci�n de la Base de datos a partir de su n�mero de habitaci�n.
     * @param numero N�mero en forma de String de la habitaci�n a ser eliminada.
     */
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
    /**
     * Elimina una habitaci�n de la Base de datos a partir de una instancia de Habitacion
     * @param habitacion Objeto de tipo Habitacion.
     */
    public static void quitarHabitacion(Habitacion habitacion){
        habitaciones.remove(habitacion);
    }
    /**
     * Devuelve la precio por noche de una habitaci�n determinada a partir de su n�mero de habitaci�n.
     * @param numHabitacion N�mero en forma de String de la habitaci�n para la cual se desea saber la tarifa.
     * @return Precio de la habitaci�n por noche. Devuelve '-1' si la habitaci�n no existe.
     */
    public static double obtenerTarifa(String numHabitacion) {
        for(int i = 0; i < habitaciones.size(); i++) {
            if(habitaciones.get(i).getNumHabitacion().equals(numHabitacion)) {
                return habitaciones.get(i).getPrecioDiario();
            }
        }
        return -1;
    }
    /**
     * Devuelve el saldo acumulado del frigobar de una determinada habitaci�n para un momento dado.
     * @param numHabitacion N�mero de habitaci�n para la cual se desea averiguar el saldo.
     * @return Saldo de la habitaci�n. Devuelve '-1' si la habitaci�n no existe.
     */
    public static double obtenerSaldoFrigobar(String numHabitacion) {
        for(int i = 0; i < habitaciones.size(); i++) {
            if(habitaciones.get(i).getNumHabitacion().equals(numHabitacion)) {
                return habitaciones.get(i).getFrigobar().getSaldo();
            }
        }
        return -1;
    }
    /**
     * Determina si el hotel en su totalidad posee la capacidad suficiente para albergar una determinada cantidad de huespedes.
     * @param cantPasajeros Cantidad de personas para la cual se desea saber si hay capacidad
     * @return TRUE si hay capacidad, FALSE si no la hay.
     */
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
    /**
     * Produce una lista con las habitaciones disponibles para ser ocupadas dada una estad�a.
     * @param ingreso Fecha del inicio de la estad�a en formato PlainDate.
     * @param egreso Fecha del fin de la estad�a en formato PlainDate.
     * @return Lista en formato ArrayList de objetos de tipo Habitacion.
     */
    public static ArrayList<Habitacion> buscarAptas(PlainDate ingreso, PlainDate egreso){
        ArrayList<Habitacion> aptas = new ArrayList<>();
        for(int i = 0; i < habitaciones.size(); i++) {
            if(habitaciones.get(i).isDisponible(ingreso,egreso)) {
                aptas.add(habitaciones.get(i));
            }
        }
        return aptas;
    }
    /**
     * Produce una lista con el n�mero de todas las habitaciones disponibles para ser ocupadas en el momento actual.
     * @return Lista en forma de ArrayList de Strings.
     */
    public static ArrayList<String> buscarNumerosDeDisponibles(){
        ArrayList<String> lista = new ArrayList<>();
        for(int i = 0; i < habitaciones.size();i++){
            if(habitaciones.get(i).isDisponible() ){
                lista.add(habitaciones.get(i).getNumHabitacion());
            }
        }
        return lista;
    }
    /**
     * Produce una lista con el n�mero de todas las habitaciones que no est�n disponibles para ser ocupadas en el momento actual.
     * @return Lista en forma de ArrayList de Strings.
     */
    public static ArrayList<String> buscarNumerosDeNoDisponibles(){
        ArrayList<String> lista = new ArrayList<>();
        for(int i = 0; i < habitaciones.size(); i++){
            if(!habitaciones.get(i).isDisponible()){
                lista.add(habitaciones.get(i).getNumHabitacion());
            }
        }
        return lista;
    }
    /**
     * Devuelve el n�mero del indice de la lista de habitaciones de Base de datos en donde se encuentra una determinada habitaci�n.
     * M�todo util al ser usado de forma conjunta con agregarHabitacionAlIndice().
     * @param numeroHab N�mero de la habitaci�n para la cual se desea saber el indice.
     * @return Indice en el ArrayList de habitaciones.
     */
    public static int obtenerIndiceHabitacion(String numeroHab){
        int index = -1;
        for(int i = 0; i < habitaciones.size(); i++){
            if(numeroHab.equals(habitaciones.get(i).getNumHabitacion())){
                index = i;
            }
        }
        return index;
    }
    /**
     * Agrega una habitaci�n al ArrayList de habitaciones en Base de datos en un determinado indice.
     * M�todo util al ser usado de forma conjunta con obtenerIndiceHabitacion().
     * @param habitacion Objeto de tipo Habitacion a insertar.
     * @param indice Indice en el que se desea insertar la habitaci�n,
     */
    public static void agregarHabitacionAlIndice(Habitacion habitacion,int indice){
        habitaciones.add(indice,habitacion );
    }

    public static String listarFrigobar(){
        StringBuffer stringBuffer = new StringBuffer();
        Frigobar frigobar = habitaciones.get(0).getFrigobar();
        HashMap<String,Double> frigo = frigobar.getProductos();
        Iterator iterator = frigo.entrySet().iterator();
        while(iterator.hasNext()){
            Map.Entry passengers = (Map.Entry) iterator.next();
            stringBuffer.append("\nProducto: " + passengers.getKey() + " - Precio: $" +
            String.valueOf(passengers.getValue()));
        }
        String listado = stringBuffer.toString();
        return listado;
    }

    public static ArrayList<Habitacion> obtenerHabitacionesDeReserva(String dni){
        int ultima = obtenerNumeroDeUltimaReserva(dni);
        ArrayList<Habitacion> habReserva = new ArrayList<>();
        ArrayList<String> arreNumerosHabitacion = new ArrayList<>();
        for(int i = 0; i < reservas.size(); i++){
            if(ultima == reservas.get(i).getNumeroReserva()){
               arreNumerosHabitacion  = reservas.get(i).getNumerosHabitaciones();
            }
        }
        for(int i = 0; i < arreNumerosHabitacion.size(); i++){
            for(int j = 0; j < habitaciones.size(); j++){
                if(arreNumerosHabitacion.get(i).equals(habitaciones.get(j).getNumHabitacion())){
                    habReserva.add(habitaciones.get(j));
                }
            }
        }
        return habReserva;
    }


    ///////////         METODOS DE PASAJEROS       ////////////////////////////////////////
    /**
     * Agrega un pasajero a la Base de datos.
     * @param pasajero Objeto de tipo pasajero a agregar a la Base de datos.
     */
    public static void agregarPasajero(Pasajero pasajero){
        pasajeros.put(pasajero.getDni(),pasajero);

    }
    /**
     * @return Devuelve un HashMao con todos los pasajeros cargados en la Base de datos del hotel.
     */
    public static HashMap<String,Pasajero> obtenerPasajeros(){
        return pasajeros;
    }
    /**
     * Elimina un pasajero de la Base de datos de pasajeros a partir de su DNI.
     * @param numDoc N�mero de DNI en forma de String del pasajero a eliminar.
     */
    public static  void quitarPasajero(String numDoc){
        pasajeros.remove(numDoc);
    }
    /**
     * Busca y devuelve un pasajero en la Base de datos.
     * @param dniPasajero DNI del pasajero que se busqueda.
     * @return Objeto de tipo Pasajero. Devuelve null si no se lo encuentra.
     */
    public static Pasajero buscaPasajeroporDni(String dniPasajero){
        Pasajero buscado = pasajeros.get(dniPasajero);
        return buscado;
    }
    /**
     * Busca un pasajero en la Base de datos y retorna su nombre y apellido en forma de String.
     * @param dniPasajero DNI del pasajero del que se quiere saber el nombre y apellido.
     * @return Nombre y apellido del pasajero en forma de String.
     */
    public static String buscaNombreYapellido(String dniPasajero){
        String ApellidoYnombre = pasajeros.get(dniPasajero).getApellido() + " " +  pasajeros.get(dniPasajero).getNombre();
        return ApellidoYnombre;

    }
    /**
     * Busca un pasajero en la Base de datos mediante su DNI y devuelve su n�mero de tel�fono.
     * @param dniPasajero DNI del pasajero del cual se quiere saber el n�mero de tel�fono.
     * @return N�mero de tel�fono en forma de String.
     */
    public static String buscaTelefonoPorDni(String dniPasajero){
        String telefono = pasajeros.get(dniPasajero).getTelefonoMovil();
        return telefono;
    }



//////////////        METODOS DE RESERVAS   ////////////////////////////

    /**
     * Agrega una reserva a la Base de datos.
     * @param elemento Objeto de tipo Reserva a agregar.
     */
    public static void agregarReserva(Reserva elemento) {
        reservas.add(elemento);
    }
    /**
     * Produce una lista de todas las reservas en la base de datos
     * @return Lista en forma de ArrayList de objetos de tipo Reserva.
     */
    public static ArrayList<Reserva> obtenerReservas(){
        return reservas;
    }
    /**
     * Devuelve una reserva a partir del n�mero de reserva.
     * @param numero N�mero de la reserva a retornar.
     * @return Objeto de tipo Reserva.
     */
    public static Reserva obtenerReserva(int numero) {
        for(Reserva reserva: reservas){
            if(reserva.getNumeroReserva() == numero) {
                return reserva;
            }
        }
        return null;//capturar un nullpointerexception mas arriba, en el metodo que quiera modificar el retorno
        //o bien una excepcion custom lanzada desde este metodo
    }
    /**
     * Devuelve la �ltima reserva que haya sido cargada a la base de datos.
     * @return Objeto de tipo Reserva.
     */
    public static int obtenerUltimaReserva() {
        if(reservas.size() == 0){
            return 0;
        }
        else {
            return reservas.get(reservas.size() - 1).getNumeroReserva();
        }
    }

    public static ArrayList<String> obtenerHistorialPrint(String dni) {
        ArrayList<Reserva> historialPasajero = pasajeros.get(dni).getHistorial();
        ArrayList<String> historialParaImprimir = new ArrayList<>();
        for(Reserva reserva : historialPasajero){
            historialParaImprimir.add(reserva.toString());
        }
        return historialParaImprimir;
    }

    public static int obtenerNumeroDeUltimaReserva(String dni){
        Pasajero pasajero = pasajeros.get(dni);
        ArrayList<Reserva> reser = pasajero.getHistorial();
        int index = reser.size() - 1;
        
        int numeroUltima = reser.get(index).getNumeroReserva();
        return numeroUltima;
    }
    public static double obtenerSaldoUltimaReserva(String dni){
        Pasajero pasajero = pasajeros.get(dni);
        ArrayList<Reserva> reser = pasajero.getHistorial();
        int index = reser.size() - 1;
        double saldoUltimo = reser.get(index).getSaldo();
        return saldoUltimo;
    }


    /**
     * Devuelve a partir de un n�mero de reserva el indice en el que se la encuentra en el ArrayList de reservas en la base de datos.
     * @param numeroReserva N�mero de reserva para la cual se desea saber el indice.
     * @return
     */
    public static int obtenerIndiceReserva(int numeroReserva){
        int index = -1;
        for(int i = 0; i < reservas.size(); i++){
            if(numeroReserva == reservas.get(i).getNumeroReserva()){
                index = i;
            }
        }
        return index;
    }
    /**
     * Coloca una reserva en un indice determinado en la base de datos
     * @param indice Indice en el que se desea colocar la reserva.
     * @param reserva Objeto de tipo reserva a colocar.
     */
    public static void agragarReservaAlIndice(int indice,Reserva reserva){
        reservas.add(indice,reserva );
    }


/////////// METODOS DE coNCERJES ////////////////////////

    /**
     * Agrega un concerje a la base de datos.
     * @param concerje
     */
    public static void agregarConcerje(Concerje concerje) {
        concerjes.put(concerje.getDni(),concerje);
    }
    /**
     * Devuelve un mapa con todos los concerjes cargados en la base de datos.
     * @return HashMap con las instancias de Concerje.
     */
    public static HashMap<String,Concerje> obtenerConcerjes(){
        return concerjes;
    }
    /**
     * Devuelve un concerje a partir de su DNI
     * @param dni DNI del concerje a obtener.
     * @return Objeto de tipo Concerje.
     */
    public static Concerje obtenerUnConcerje(String dni) {
        Concerje aux = concerjes.get(dni);
        return aux;
    }
///////////ADMINISTRADORES////////////////////////
    /**
     * Agrega un Administrador a la base de datos.
     * @param administrador Objeto de tipo Administrador a agregar.
     */
    public static void agregarAdministrador(Administrador administrador){
        //System.out.println(administrador.getDni());
        administradores.put(administrador.getDni(),administrador );
    }
    /**
     * Devuelve un mapa con todos los administradores cargados en la base de datos.
     * @return HashMap con las instancias de Administrador.
     */
    public static HashMap<String, Administrador> obtenerAdministradores() {
        return administradores;
    }
    /**
     * Devuelve un Administrador a partir de su DNI.
     * @param dni DNI del Administrador a devolver.
     * @return Objeto de tipo Administrador.
     */
    public static Administrador obtenerAdministrador(String dni){
        return administradores.get(dni);
    }

}