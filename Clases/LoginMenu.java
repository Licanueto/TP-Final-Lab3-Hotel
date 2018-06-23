package Clases;

import org.json.JSONException;

import java.util.*;

public class LoginMenu {
    private HashMap<String,Usuario> logueo;
    public static Scanner scanner;

    public LoginMenu(){
        logueo = new HashMap<>();
       scanner = new Scanner(System.in);
    }

    public HashMap<String, Usuario> getLogueo() {
        return logueo;
    }

    //SIEMPRE ANTES DE LOGUEARSE SE DEBE CARGAR EL MAPA DE USUARIOS.........
    //SE ELIMINA AL TERMINAR EL LOGUEO

    public void cargarMapaUsuarios(){
        Iterator iterator = BaseDeDatos.obtenerPasajeros().entrySet().iterator();
        while(iterator.hasNext()){
            Map.Entry passengers = (Map.Entry) iterator.next();
            logueo.put((String)passengers.getKey(),(Pasajero)passengers.getValue());
        }
        iterator = BaseDeDatos.obtenerConcerjes().entrySet().iterator();
        while (iterator.hasNext()){
            Map.Entry conseryers = (Map.Entry) iterator.next();
            logueo.put((String)conseryers.getKey(),(Concerje)conseryers.getValue());
        }
        iterator = BaseDeDatos.obtenerAdministradores().entrySet().iterator();
        while (iterator.hasNext()){
            Map.Entry administrators = (Map.Entry) iterator.next();
            logueo.put((String)administrators.getKey(),(Administrador)administrators.getValue());
        }
    }
    public void login(){

        int contador = 0;
        char quedarse = 'x';
        String nombreUsuario = "";
        boolean estadoDeUsuario; //ver esto
        do {

            try {
                contador = 3;
                System.out.println("Ingrese dni");
                nombreUsuario = scanner.next();
                contador--;
                //estadoDeUsuario = estaDadoDeAlta(nombreUsuario);//ver una forma de usar la variable...
                boolean acceso = buscar(nombreUsuario);


                if (acceso) {
                    System.out.println("Bienvenido usuario: " + nombreUsuario);
                    System.out.println("Ingrese Password");
                    contador = 3;
                    do {

                        try {
                            String clave = scanner.next();

                            contador--;
                            boolean esLaClave = compruebaClave(clave, getLogueo().get(nombreUsuario));

                            if (esLaClave) {
                                System.out.println("INGRESO EXITOSO!!!!");
                                accesoAlSistema(getLogueo().get(nombreUsuario));
                                System.out.println("Ingresar como un usuario distinto?");
                                quedarse = scanner.next().charAt(0);

                            }
                        }
                        catch (ClaveIncorrectaException e){
                            e.printStackTrace();
                            System.out.println("Le quedan " + contador + " intentos mas.");
                        }
                        if(quedarse == 's' || quedarse == 'n') {
                            contador = 0;
                        }
                    }while(contador > 0 );

                }

            }
            catch (UsuarioInexistenteException e) {
                e.printStackTrace();
                System.out.println("Le quedan " + contador + " intentos mas.");
            }
            catch (UsuarioEnBajaException e){
                e.printStackTrace();
                return; //sale del login cuando esta en baja
            }
            catch (RuntimeException e){
                e.printStackTrace();
            }

            if(quedarse == 's') {
                contador = 1;

            }
            else if (quedarse == 'n'){
                contador = 0;
            }
        }while(contador > 0);
        System.out.println("Chau, hasta la proxima");
    }

    public boolean buscar(String documento){

        if(logueo.get(documento) == null){
            throw new UsuarioInexistenteException("Usuario incorrecto, vuelva a ingresar documento...");
        }
        return true;
    }
    public boolean compruebaClave(String clave,Usuario algunaCosa){
        if (!clave.equals(algunaCosa.getPassword())){
            throw new ClaveIncorrectaException("Clave incorrecta, vuelva a ingresar....");
        }
        return true;
    }

    //ACA SEGUN EL TIPO DE INSTANCIA QUE TENGAMOS LE DAMOS ACCESO A DIFERENTES MENUS
    // EN ESTE METODO IRIAN ANIDADOS LOS 3 MENUS ADMIN, CONCERJE Y PASAJERO
    public void accesoAlSistema(Object cosa){
        if(cosa instanceof Pasajero){
            //eliminarMapa();
            Pasajero ingresa = (Pasajero) cosa;
            System.out.println("ACCESO AL MENU DE PASAJEROS " + ingresa.getApellido() + " " +
                    ingresa.getNombre());

        }
        if(cosa instanceof Concerje){
            //eliminarMapa();
            Concerje ingresa = (Concerje) cosa;
            menuConcerje(ingresa.getDni());
            System.out.println("ACCESO AL MENU DE CONCERJES " + ingresa.getApellido() + " " +
                    ingresa.getNombre());

        }
        if(cosa instanceof  Administrador){
            //eliminarMapa();
            Administrador ingresa = (Administrador) cosa;

            menuAdministrador(ingresa.getDni());

            System.out.println("ACCESO AL MENU DE CONCERJES " + ingresa.getApellido() + " " +
                    ingresa.getNombre());

        }
    }
    public void eliminarMapa(){
        logueo.clear();
    }
    public boolean estaDadoDeAlta(String dni){
        if(!logueo.get(dni).isEstadoDeAlta()){
            throw new UsuarioEnBajaException("Usted esta dado de baja, contacte al administrador");
        }
        return true;
    }

    public void menuAdministrador(String dni){
        String doc = dni;
        Administrador administrador = BaseDeDatos.obtenerAdministrador(doc);

        Integer opcion ;
        do {
            System.out.println("OPCIONES ADMINISTRADOR");
            System.out.println("-----------------");
            System.out.println("1- Alta y baja de concerjes");
            System.out.println("2- Alta y baja de habitaciones");
            System.out.println("3- Realizar BackUp (Persistir informacion)");
            System.out.println("4- Consultas");
            System.out.println("5- Obtener JSON");
            System.out.println("0- Volver atras");
            System.out.println("-----------------");
            opcion = scanner.nextInt();

            switch (opcion) {
                case 1:
                    submenuAbmConcerje(doc);
                    break;
                case 2:
                    submenuAbmHabitacion(doc);
                    break;
                case 3:
                    administrador.backUp();
                    break;
                case 4:
                    submenuConsultasAdmin(doc);
                case 5:
                    try {
                        administrador.getJson();
                    }
                    catch (JSONException e){
                        e.printStackTrace();
                    }
                    break;
                case 0:
                    return;
                default:
            }
        }while(opcion < 6 && opcion > -1);
    }
    public void submenuAbmConcerje(String dni){
        Administrador administrador = BaseDeDatos.obtenerAdministrador(dni);

        int opcion = 0;
        do{
            System.out.println("ABM DE CONCERJES");
            System.out.println("1- Crear y dar de alta un nuevo concerje");
            System.out.println("2- Dar de baja un concerje");
            System.out.println("3- Reactivar un concerje");
            System.out.println("0- Volver Atras");
            opcion = scanner.nextInt();
            switch (opcion){
                case 1:
                    administrador.darDeAltaUsuario();
                    break;
                case 2:
                    System.out.println("Ingrese DNI del concerje que quiere dar de baja");
                    String dniCon = scanner.next();
                    administrador.darDeBajaUsuario(dniCon);
                    break;
                case 3:
                    System.out.println("Ingrese el DNI del concerje que quiere reactivar");
                    String documento = scanner.next();

                    administrador.darDeAltaUsuario(documento);
                    break;
                case 0:
                    return;
            }

        }while(opcion < 4);
    }
    //HAY QUE VER SI SOBRECARGAR EL DAR DE ALTA HABITACION PARA QUE ADEMAS DE CREAR LA HABITACION
    //PUEDA SER QUE LA PASE DE DISPONIBLE A NO DISPONBIBLE
    public void submenuAbmHabitacion(String dni) {
        Administrador administrador = BaseDeDatos.obtenerAdministrador(dni);


        int opcion = 0;
        do {
            System.out.println("ALTA Y BAJA DE HABITACIONES");
            System.out.println("1- Dar de alta de habitacion");
            System.out.println("2- Dar de baja una habitacion");
            System.out.println("0- Volver atras");
            opcion = scanner.nextInt();

            switch (opcion) {
                case 1:
                    System.out.println("Ingrese numero para la habitacion a dar de alta");
                    String numHab = scanner.next();
                    administrador.darAltaHab(numHab);
                    break;
                case 2:
                    System.out.println("Ingrese numero de la habitacion a dar de baja");
                    String numeroHab = scanner.next();
                    administrador.darBajaHab(numeroHab);
                    break;
                case 0:
                    return;
            }

        }while (opcion < 2) ;
    }
    public void submenuConsultasAdmin(String dni){
        Administrador administrador = BaseDeDatos.obtenerAdministrador(dni);

        int opcion = 0;
        do {
            System.out.println("CONSULTAS");
            System.out.println("1- Ver listado de habitaciones");
            System.out.println("2- Ver listado de Empleados");
            System.out.println("3- Ver listado de reservas");
            System.out.println("4- Ver listado de clientes");
            System.out.println("0- volver");
            opcion = scanner.nextInt();
            switch (opcion){
                case 1:
                    ArrayList<Habitacion> habitaciones = BaseDeDatos.obtenerHabitaciones();
                    for(Habitacion habitacion: habitaciones){
                        System.out.println(habitacion.mostrarHabitacion()); //ver el nombre del metodo a usar(habia otros)
                    }
                    break;
                case 2:
                    Iterator iterator = BaseDeDatos.obtenerConcerjes().entrySet().iterator();
                    while (iterator.hasNext()) {
                        Map.Entry conseryers = (Map.Entry) iterator.next();
                        System.out.println(conseryers.getValue().toString());
                    }
                    break;
                case 3:
                    ArrayList<Reserva> reservas = BaseDeDatos.obtenerReservas();
                    for(Reserva reserva : reservas){
                        System.out.println(reserva.toString());
                    }
                    break;
                case 4:
                    Iterator iterador = BaseDeDatos.obtenerPasajeros().entrySet().iterator();
                    while(iterador.hasNext()) {
                        Map.Entry passengers = (Map.Entry) iterador.next();
                        System.out.println(passengers.getValue().toString());

                    }
                    break;
                case 0:
                    return;
            }
        }while(opcion < 5);
    }
    public void menuConcerje(String dni)
    {
        String doc = dni;

        int opcion = 0;
        do {
            System.out.println("OPCIONES DE CONCERJE");
            System.out.println("----------------------");
            System.out.println("1- Pasajero ABM");
            System.out.println("2- Gestion de reservas.");
            System.out.println("3- Consultas");
            System.out.println("4- Gestion de Habitaciones");
            System.out.println("0- Volver atras");
            System.out.println("----------------------");
            opcion = scanner.nextInt();
            switch(opcion) {
                case 1:
                    subMenuABMPasajeros(doc);
                    break;
                case 2:
                    subMenuReservas(doc);
                    break;

                case 3:
                    subMenuEstadiaPasajero(doc);
                    break;
                case 4:
                    subMenuGestionHabitaciones(doc);
                    break;

                case 0:

                    return;
                default:

            }

        }while(opcion < 5 && opcion > -1);

    }
    public void subMenuABMPasajeros(String doc)
    {
        Concerje concerje = BaseDeDatos.obtenerUnConcerje(doc);

        int opcion = 0;
        do {
            System.out.println("OPCIONES DE ABM PASAJEROS");
            System.out.println("----------------------");
            System.out.println("1- Dar de Alta a un Pasajero por primera vez.");
            System.out.println("2- Dar de Alta que ha sido dado de baja.");
            System.out.println("3- Dar de Baja a un Pasajero.");
            System.out.println("0- Volver atras");
            System.out.println("----------------------");
            opcion = scanner.nextInt();
            switch(opcion) {
                case 1:
                    concerje.darDeAltaUsuario();
                    break;
                case 2:
                    System.out.println("Ingrese por favor el dni: ");
                    String documento = scanner.next();
                    concerje.darDeAltaUsuario(documento);
                    break;
                case 3:
                    System.out.println("Ingrese por favor el dni: ");
                    String docu = scanner.next();
                    concerje.darDeBajaUsuario(docu);

                    break;

                case 0:

                    return;
                default:



            }


        }while(opcion < 4 && opcion > -1);


    }

    public void subMenuReservas(String dni)
    {
        Concerje concerje = BaseDeDatos.obtenerUnConcerje(dni);

        int opcion = 0;
        do {
            System.out.println("OPCIONES DE GESTION DE RESERVAS");
            System.out.println("---------------------------------");
            System.out.println("1- Reservar.");
            System.out.println("2- Cancelar Reserva.");
            System.out.println("3- Gestionar Estadía Pasajero.");
            System.out.println("4- Hacer Check In");
            System.out.println("5- Hacer Check Out");
            System.out.println("0- Volver atras");
            System.out.println("---------------------------------");
            opcion = scanner.nextInt();
            switch(opcion) {
                case 1:
                    concerje.gestionarReserva();
                    break;
                case 2:
                    System.out.println("Por favor, introduzca numero de reserva");
                    int numeroReserva = scanner.nextInt();
                    concerje.cancelarReserva(numeroReserva);
                    break;

                case 3:
                    subMenuEstadiaPasajero(dni);
                    break;
                case 4:
                    System.out.println("Por favor, introduzca numero de reserva");
                    numeroReserva = scanner.nextInt();
                    concerje.realizarCheckIn(numeroReserva);
                    break;
                case 5:
                    System.out.println("Por favor, introduzca numero de reserva");
                    numeroReserva = scanner.nextInt();
                    Reserva reserva = BaseDeDatos.obtenerReserva(numeroReserva);
                    System.out.println("Monto a pagar: " + reserva.calcularMonto());
                    System.out.println("Por favor, introduzca importe a pagar");
                    double importe = scanner.nextDouble();
                    concerje.realizarChekOut(numeroReserva, importe);

                case 0:

                    return;
                default:



            }


        }while(opcion < 6 && opcion > -1);

    }
    public void subMenuEstadiaPasajero(String dni)
    {
        Concerje concerje = BaseDeDatos.obtenerUnConcerje(dni);

        int opcion = 0;
        do {
            System.out.println("OPCIONES DE ESTADIA DE PASAJERO");
            System.out.println("----------------------");
            System.out.println("1- Cargar Consumo Frigobar.");
            System.out.println("2- Realizar Pago Parcial.");
            System.out.println("0- Volver atras");
            System.out.println("----------------------");
            opcion = scanner.nextInt();
            switch(opcion) {
                case 1:
                    System.out.println("Por favor, introduzca numero de habitacion");
                    String numHabitacion = scanner.next();
                    Habitacion hab = BaseDeDatos.buscarPorNumero(numHabitacion);
                    int indice = BaseDeDatos.obtenerIndiceHabitacion(numHabitacion);
                    System.out.println("Introduzca un producto:");
                    String producto = scanner.next();
                    System.out.println("Introduzca la cantidad:");
                    byte cantidad = scanner.nextByte();
                    hab.getFrigobar().consumirProduto(producto, cantidad);
                    break;
                case 2:
                    System.out.println("Por favor, introduzca numero de reserva");
                    int numeroReserva = scanner.nextInt();
                    Reserva reserva = BaseDeDatos.obtenerReserva(numeroReserva);
                    int index = BaseDeDatos.obtenerIndiceReserva(numeroReserva);
                    System.out.println("Introduzca el importe que desea pagar por adelantado");
                    double importe = scanner.nextDouble();
                    reserva.descontarSaldo(importe);
                    break;

                case 0:

                    return;
                default:



            }


        }while(opcion < 3 && opcion > -1);

    }
    public void subMenuConsultas(String dni)
    {
        Concerje concerje = BaseDeDatos.obtenerUnConcerje(dni);

        int opcion = 0;
        do {
            System.out.println("MENU DE CONSULTAS.");
            System.out.println("---------------------------------");
            System.out.println("1- Consultar Disponibilidad.");
            System.out.println("2- Ver todas las habitaciones disponibles.");
            System.out.println("3- Ver todas las habitaciones ocupadas.");
            System.out.println("4- Consultar por habitación en particular.");
            System.out.println("0- Volver atras");
            System.out.println("---------------------------------");
            opcion = scanner.nextInt();
            switch(opcion) {
                case 1:
                    concerje.consultarTelefonicamente();
                    break;
                case 2:
                    System.out.println(concerje.verDisponibles());

                    break;

                case 3:
                    System.out.println(concerje.verOcupadas());
                    break;
                case 4:
                    System.out.println("Ingrese el numero de habitacion");
                    String numeroHab = scanner.next();
                    concerje.consultarHabitacion(numeroHab);
                    break;
                case 5:

                case 0:

                    return;
                default:



            }


        }while(opcion < 5 && opcion > -1);

    }
    public void subMenuGestionHabitaciones(String dni)
    {
        Concerje concerje = BaseDeDatos.obtenerUnConcerje(dni);

        int opcion = 0;
        do {
            System.out.println("GESTION DE HABITACIONES.");
            System.out.println("---------------------------------");
            System.out.println("1- Dar de alta habitacion..");
            System.out.println("2- Dar de baja habitación.");
            System.out.println("0- Volver atras");
            System.out.println("---------------------------------");
            opcion = scanner.nextInt();
            switch(opcion) {
                case 1:
                    System.out.println("Ingrese el numero de habitacion");
                    String numeroHab = scanner.next();
                    concerje.darAltaHab(numeroHab);
                    break;
                case 2:
                    System.out.println("Ingrese el numero de habitacion");
                    String numHab = scanner.next();
                    concerje.darBajaHab(numHab);
                    break;


                case 0:

                    return;
                default:



            }


        }while(opcion < 3 && opcion > -1);


    }

}