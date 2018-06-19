public class SerVivo {
    private String dni;
    private String nombre;
    private String pass;

    public SerVivo(String dni,String nombre,String pass){
        this.dni = dni;
        this.nombre = nombre;
        this.pass = pass;
    }
    public String getDni() {
        return dni;
    }

    public String getPass() {
        return pass;
    }
}
