package Clases;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Scanner;

public class LoginMenu {
    private HashMap<String,Usuario> logueo;

    public LoginMenu(){
        logueo = new HashMap<>();
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
        Scanner scanner = new Scanner(System.in);
        int contador = 3;
        do {

            try {

                System.out.println("Ingrese dni");
                String nombreUsuario = scanner.next();
                contador--;
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
                                return;
                            }
                        }
                        catch (ClaveIncorrectaException e){
                            e.printStackTrace();
                            System.out.println("Le quedan " + contador + " intentos mas.");
                        }
                    }while(contador > 0);

                }

            }
            catch (UsuarioInexistenteException e) {
                e.printStackTrace();
                System.out.println("Le quedan " + contador + " intentos mas.");
            }
        }while(contador > 0);
        System.out.println("Maximo de intentos alcanzado");
    }

    public boolean buscar(String documento){
        if(logueo.get(documento) == null){
            throw new UsuarioInexistenteException("Usuario incorrecto, vuelva a ingresar documento...");
        }
        return true;
    }
    public boolean compruebaClave(String clave,Usuario algunaCosa){
        if (!clave.equals(algunaCosa.getDni())){
            throw new ClaveIncorrectaException("Clave incorrecta, vuelva a ingresar....");
        }
        return true;
    }

    //ACA SEGUN EL TIPO DE INSTANCIA QUE TENGAMOS LE DAMOS ACCESO A DIFERENTES MENUS
    // EN ESTE METODO IRIAN ANIDADOS LOS 3 MENUS ADMIN, CONCERJE Y PASAJERO
    static void accesoAlSistema(Object cosa){
        if(cosa instanceof Pasajero){
            Pasajero ingresa = (Pasajero) cosa;
            System.out.println("ACCESO AL MENU DE PASAJEROS " + ingresa.getApellido() + " " +
            ingresa.getNombre());
        }
        if(cosa instanceof Concerje){
            Concerje ingresa = (Concerje) cosa;
            System.out.println("ACCESO AL MENU DE CONCERJES " + ingresa.getApellido() + " " +
                    ingresa.getNombre());
        }
        if(cosa instanceof  Administrador){
            Administrador ingresa = (Administrador) cosa;
            System.out.println("ACCESO AL MENU DE CONCERJES " + ingresa.getApellido() + " " +
                    ingresa.getNombre());
        }
    }

}
