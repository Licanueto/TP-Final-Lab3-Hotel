import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        Logueo logueo = new Logueo();
        Persona pepe = new Persona("20123123","Pepe","12345");
        Persona cacho = new Persona("12345678","Cacho","abcdef");
        Perro salchicha = new Perro("22222222","salchicha","salchicha");
        Perro chihuahua = new Perro("11111111","chihuahua","chi");

        logueo.agregar(pepe);
        logueo.agregar(cacho);
        logueo.agregar(chihuahua);
        logueo.agregar(salchicha);



        Scanner scanner = new Scanner(System.in);
        int contador = 3;
        do {

            try {

                System.out.println("Ingrese dni");
                String nombreUsuario = scanner.next();
                contador--;
                boolean acceso = logueo.buscar(nombreUsuario);


                if (acceso) {
                    System.out.println("Bienvenido usuario: " + nombreUsuario);
                    System.out.println("Ingrese Password");
                    contador = 3;
                    do {
                        try {
                            String clave = scanner.next();
                            contador--;
                            boolean esLaClave = logueo.compruebaClave(clave, logueo.getMapeo().get(nombreUsuario));

                            if (esLaClave) {
                                System.out.println("INGRESO EXITOSO!!!!");
                                accesoAlSistema(logueo.getMapeo().get(nombreUsuario));
                                return;
                            }
                        }
                        catch (ClaveIncorrectaException e){
                            e.printStackTrace();
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

    //ACA SEGUN EL TIPO DE INSTANCIA QUE TENGAMOS LE DAMOS ACCESO A DIFERENTES MENUS
    // EN ESTE METODO IRIAN LOS 3 MENUS ADMIN, CONCERJE Y PASAJERO
    static void accesoAlSistema(Object cosa){
        if(cosa instanceof Persona){
            System.out.println("ACCESO AL MENU DE PERSONAS");
        }
        if(cosa instanceof Perro){
            System.out.println("ACCESO AL MENU DE PERROS");
        }
    }

}
