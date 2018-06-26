package App;

import Clases.Administrador;
import Clases.BaseDeDatos;
import Clases.Hotel;

public class Main {



    public static void main(String[] args) {


        Hotel hotel = Hotel.getSingletonInstance("Hotel","calle 123","2222222");
        //Administrador administrador1 = new Administrador("4321","Pepe","Sapo","1234");

        BaseDeDatos.levantarHabitaciones();
        BaseDeDatos.levantarAdministradores();
        BaseDeDatos.levantarConcerjes();
        BaseDeDatos.levantarReservas();
        BaseDeDatos.levantarPasajeros();

       // BaseDeDatos.agregarAdministrador(administrador1);




        hotel.getLoginMenu().cargarMapaUsuarios();
        hotel.getLoginMenu().login();

    }
}