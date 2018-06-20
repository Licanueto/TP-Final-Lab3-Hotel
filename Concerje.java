package Clases;

import net.time4j.PlainDate;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

public class Concerje extends Usuario implements IAbmUsuario{

    public Concerje(String dni, String nombre, String apellido)
    {
        super(dni, nombre, apellido);
    }

    @Override
    public String toString() {

        return super.toString();
    }

    ArrayList<String> asignarHabitaciones(PlainDate fechaIngreso, PlainDate fechaEgreso, int cantPasajeros)
    {
        ArrayList<String>numerosHabitaciones = new ArrayList<String>();
        String aux = "";
        try {
            verificarDisponibilidad(cantPasajeros);

            int i = 0;
            ArrayList<Habitacion> libres = BaseDeDatos.buscarAptas(fechaIngreso, fechaEgreso);
            Collections.sort(libres);
            Collections.reverse(libres);
            while(cantPasajeros > 0)
            {
                while(i < libres.size())
                {
                    if(cantPasajeros >= libres.get(i).getCapacidad())
                    {
                        cantPasajeros = cantPasajeros - libres.get(i).getCapacidad();
                        aux = libres.get(i).getNumHabitacion();
                        numerosHabitaciones.add(aux);

                    }
                    i++;
                }
                if(cantPasajeros > 0)
                {
                    i = libres.size()-1;
                    aux = libres.get(i).getNumHabitacion();
                    numerosHabitaciones.add(aux);


                    cantPasajeros = 0;
                }
            }



        }catch(FaltaDisponibilidadException e){

            e.getMessage();

        }catch(RuntimeException e) {

            e.getMessage();
        }finally {
            return numerosHabitaciones;
        }
    }
    public boolean verificarDisponibilidad(int cantPasajeros)throws FaltaDisponibilidadException
    {
        boolean verifica = BaseDeDatos.hayCapacidad(cantPasajeros);
        if (verifica  == false)
            throw new FaltaDisponibilidadException("No alcanza la capacidad del hotel para hospedar a los pasajeros");
        return verifica;

    }

    public int Reservar(String dni, PlainDate fechaIngreso, PlainDate fechaEgreso, ArrayList<String> numerosHabitaciones)
    {
        int numeroReserva = BaseDeDatos.obtenerUltimaReserva() + 1;
        Reserva nuevaReserva = new Reserva(numeroReserva, dni, fechaIngreso, fechaEgreso, numerosHabitaciones);
        BaseDeDatos.agregarReserva(nuevaReserva);

        for(int i = 0; i < numerosHabitaciones.size(); i++)
        {
            BaseDeDatos.buscarPorNumero(numerosHabitaciones.get(i)).ocupar(fechaIngreso, fechaEgreso);;

        }
        return numeroReserva;

    }
    public void realizarCheckIn(int numeroReserva)
    {
        BaseDeDatos.obtenerReserva(numeroReserva).hacerEfectiva();
    }
    public void realizarChekOut(int numeroReserva, double importe) //importe que ingresa en dinero el cliente
    {
        cobrar(BaseDeDatos.obtenerReserva(numeroReserva), importe);
        for(int i = 0; i < BaseDeDatos.obtenerReserva(numeroReserva).getNumerosHabitaciones().size(); i++)
        {
            BaseDeDatos.buscarPorNumero(BaseDeDatos.obtenerReserva(numeroReserva).getNumerosHabitaciones().get(i)).getFrigobar().cancelarSaldo();
        }

    }
    public void cobrar(Reserva reserva, double importe) //recibimos el importe que paga y chekeamos si cancelamos
    {

        if(importe == reserva.calcularMonto())
        {
            reserva.confirmarPago();
        }else if(importe < reserva.calcularMonto())
        {
            reserva.descontarSaldo(importe);
        }


    }

    public void cancelarReserva(int numeroReserva)
    {
        for(int i = 0; i < BaseDeDatos.obtenerReserva(numeroReserva).getNumerosHabitaciones().size(); i++)
        {
            BaseDeDatos.buscarPorNumero
                    (BaseDeDatos.obtenerReserva(numeroReserva).getNumerosHabitaciones().get(i)).desocupar(BaseDeDatos.obtenerReserva(numeroReserva).getFechaIngreso(),
                    BaseDeDatos.obtenerReserva(numeroReserva).getFechaEgreso());
        }
    }
    @Override
    public void darDeAltaUsuario() {
        Scanner scanner = new Scanner(System.in);
        String nombrePasajero = "";
        String apellidoPasajero = "";
        String dniPasajero = "";
        String celular = "";
        String eMail = "";
        String ciudadOrigen = "";
        String domicilio = "";
        char correcto = 'n';
        Pasajero pasajero1;
        int contador = 0;

        while(correcto == 'n' || correcto == 'N') {
            System.out.println("**Alta de nuevo Pasajero**");
            System.out.println("Ingrese nombre(s): ");
            nombrePasajero = scanner.next();
            System.out.println("Ingrese Apellido(s)");
            apellidoPasajero = scanner.next();
            System.out.println("Ingrese DNI");
            dniPasajero = scanner.next();
            System.out.println("Ingrese numero de telefono");
            celular = scanner.next();
            System.out.println("Ingrese e-mail");
            eMail = scanner.next();
            System.out.println("Ingrese ciudad de origen");
            ciudadOrigen = scanner.next();
            System.out.println("Ingrese domicilio");
            domicilio = scanner.next();
            System.out.println("Los datos ingresados son: \nNombre: " + nombrePasajero + "\nApellido: " + apellidoPasajero +
                    "\nDNI: " + dniPasajero + "\nCelular: " + celular + "\nE-Mail: " + eMail +
                    "Ciudad de origen: " + ciudadOrigen + "\nDomicilio: " + domicilio +
                    "\nEs correcta la informacion? oprimir 's' para guardar... 'n' para modificar... ");
            correcto = scanner.next().charAt(0);
        }
        scanner.close();


        pasajero1 = new Pasajero(nombrePasajero,apellidoPasajero,dniPasajero,celular,eMail,ciudadOrigen,domicilio);
        BaseDeDatos.agregarPasajero(pasajero1);//una vez agregado al arreglo se puede volver a crear otroconcerje con esta variable


    }

    @Override
    public void darDeBajaUsuario() {
        
    }
}