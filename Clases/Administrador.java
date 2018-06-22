package Clases;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

/**
 * Esta clase representa a un administrador del hotel, que cumple el rol de gerente general. Tiene la funcion de crear las habitaciones,
 * darlas de baja, crear y dar de alta o baja a los concerjes, y realizar un backup del sistema (guarda en archivos la informacion que
 * toma de la base de datos. Para que cualquier cambio quede en memoria secundaria, el administrador debe realizar un backup.
 *  Tambien puede consultar las habitaciones, reservas, pasajeros y gerentes.
 */

public class Administrador extends Usuario implements IAbmUsuario,IAbmHabitacion{

    public Administrador(String dni,String nombre,String apellido) {
        super(dni,nombre,apellido);
    }


    public void serializarConcerjes () {

        ObjectOutputStream escritura = null;
        HashMap<String, Concerje> auxiliar  = BaseDeDatos.obtenerConcerjes();

        try {
            escritura = new ObjectOutputStream(new FileOutputStream("concerjes.dat"));
            escritura.writeObject(auxiliar);
        }
        catch (IOException e) {
            // TODO: handle exception
        }
        catch (Exception e) {
            // TODO: handle exception
        }
        finally {
            try {
                escritura.close();
            }
            catch(IOException e) {
                //hacer algo
            }
        }
    }

    public void serializarHabitaciones () {

        ObjectOutputStream escritura = null;
        ArrayList<Habitacion> auxiliar  = BaseDeDatos.obtenerHabitaciones();

        try {
            escritura = new ObjectOutputStream(new FileOutputStream("habitaciones.dat"));
            escritura.writeObject(auxiliar);
        }
        catch (IOException e) {
            // TODO: handle exception
        }
        catch (Exception e) {
            // TODO: handle exception
        }
        finally {
            try {
                escritura.close();
            }
            catch(IOException e) {
                //hacer algo
            }
        }
    }

    public void serializarPasajeros () {

        ObjectOutputStream escritura = null;
        HashMap<String, Pasajero> auxiliar  = BaseDeDatos.obtenerPasajeros();

        try {
            escritura = new ObjectOutputStream(new FileOutputStream("pasajeros.dat"));
            escritura.writeObject(auxiliar);
        }
        catch (IOException e) {
            // TODO: handle exception
        }
        catch (Exception e) {
            // TODO: handle exception
        }
        finally {
            try {
                escritura.close();
            }
            catch(IOException e) {
                //hacer algo
            }
        }
    }

    public void serializarReservas () {

        ObjectOutputStream escritura = null;
        ArrayList<Reserva> auxiliar  = BaseDeDatos.obtenerReservas();

        try {
            escritura = new ObjectOutputStream(new FileOutputStream("reservas.dat"));
            escritura.writeObject(auxiliar);
        }
        catch (IOException e) {
            // TODO: handle exception
        }
        catch (Exception e) {
            // TODO: handle exception
        }
        finally {
            try {
                escritura.close();
            }
            catch(IOException e) {
                //hacer algo
            }
        }
    }

    public void backUp() {

        char seguir;
        Scanner scanner = new Scanner(System.in);
        System.out.println("Desea realizar un Back Up de la informacion del sistema? s/n");
        seguir = scanner.next().charAt(0);
        if(seguir == 's' || seguir == 'S') {

            serializarHabitaciones();
            serializarPasajeros();
            serializarReservas();
            serializarConcerjes();
        }
        scanner.close();
    }


    @Override
    public void darDeAltaUsuario() {

        Scanner scanner = new Scanner(System.in);
        String nombreConcerje = "";
        String apellidoConcerje = "";
        String dniConcerje = "";
        char correcto = 'n';
        Concerje concerje1;
        int contador = 0;

        while(correcto == 'n' || correcto == 'N') {
            System.out.println("**Alta de nuevo concerje**");
            System.out.println("Ingrese nombre(s): ");
            nombreConcerje = scanner.next();
            System.out.println("Ingrese Apellido(s)");
            apellidoConcerje = scanner.next();
            System.out.println("Ingrese DNI");
            dniConcerje = scanner.next();
            System.out.println("Los datos ingresados son: \nNombre: " + nombreConcerje + "\nApellido: " + apellidoConcerje +
                    "\nDNI: " + dniConcerje + "\nEs correcta la informacion? oprimir 's' para guardar... 'n' para modificar... ");
            correcto = scanner.next().charAt(0);
        }
        scanner.close();


        concerje1 = new Concerje(nombreConcerje,apellidoConcerje,dniConcerje);
        BaseDeDatos.agregarConcerje(concerje1);//una vez agregado al arreglo se puede volver a crear otroconcerje con esta variable
    }

    @Override
    public void darDeAltaUsuario(String dni) {
        try{
            Concerje concerje = BaseDeDatos.obtenerUnConcerje(dni);
            if(!isEstadoDeAlta()) {
                concerje.setEstadoDeAlta(true);
            }
        }
        catch (UsuarioInexistenteException e){
            e.printStackTrace();
        }
    }

    @Override
    public void darDeBajaUsuario(String dni) {
        try {
            Concerje concerje = BaseDeDatos.obtenerUnConcerje(dni);
            if(isEstadoDeAlta()){
                concerje.setEstadoDeAlta(false);
            }
        }
        catch (UsuarioInexistenteException e){
            e.printStackTrace();
        }
    }

    @Override
    public void darAltaHab(String numeroHab) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Ingrese numero para una nueva habitacion");
        String numero = scanner.next();
        System.out.println("Ingrese la capacidad de la habitacion");
        byte capacity = scanner.nextByte();
        System.out.println("Ingrese el tipo de habitacion (descripcion)");
        String type = scanner.next();
        System.out.println("Ingrese precio diario para la habitacion");
        double price = scanner.nextDouble();

        if(!BaseDeDatos.existeHabitacion(numero)) {
            Habitacion habitacion = new Habitacion(numero,capacity,type,price);
            BaseDeDatos.agregarHabitacion(habitacion);
        }
        else {
            System.out.println("Esa habitacion ya existe, la nueva no fue creada");
        }
        scanner.close();
    }

    @Override
    public void darBajaHab(String numeroHab) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Ingrese numero de la habitacion a eliminar");
        String numero = scanner.next();
        if(BaseDeDatos.existeHabitacion(numero)){
            BaseDeDatos.eliminarHabitacion(numero);
        }
        else {
            System.out.println("Esa habitacion no existe, no puede eliminarse");
        }
    }

    @Override
    public JSONObject getJson() throws JSONException{
        JSONObject obj = new JSONObject();
        obj.put("nombre", getNombre());
        obj.put("apellido", getApellido());
        obj.put("dni", getDni());

        return obj;
    }
}