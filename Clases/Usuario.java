package Clases;

import org.json.JSONException;
import org.json.JSONObject;

public abstract class  Usuario {

    private String dni;
    private String nombre;
    private String apellido;
    private boolean estadoDeAlta;
    private String password;

    public Usuario()
    {
        dni = " ";
        nombre =" ";
        apellido = " ";
    }


    public Usuario(String dni, String nombre, String apellido,String password)
    {
        this.dni = dni;
        this.nombre = nombre;
        this.apellido = apellido;
        this.estadoDeAlta = true;
        this.password = password;

    }

    public boolean isEstadoDeAlta() {
        return estadoDeAlta;
    }

    public String getPassword() {
        return password;
    }

    public void setDni(String dni)
    {
        this.dni = dni;
    }
    public String getDni()
    {
        return dni;
    }
    public void setNombre(String nombre)
    {
        this.nombre = nombre;
    }

    public void setEstadoDeAlta(boolean estadoDeAlta) {
        this.estadoDeAlta = estadoDeAlta;
    }

    public String getNombre()
    {
        return nombre;
    }
    public void setApellido(String apellido)
    {
        this.apellido = apellido;
    }
    public String getApellido()
    {
        return apellido;
    }
    public void ponerEnBaja(){
        if(estadoDeAlta){
            estadoDeAlta = false;
        }
        //else.... avisar o lanzar excepcion....
    }
    public void ponerEnAlta(){
        if(!estadoDeAlta){
            estadoDeAlta = true;
        }
        //else...ver que se hace aca
    }

    @Override
    public String toString() {

        //return "\nDNI: " + dni + "\nNombre: " + nombre + "\nApellido: " + apellido;
        return "\nDNI: " + dni + "\nNombre: " + nombre + "\nApellido: " + apellido+"\n Password: "+password;
    }

    public abstract JSONObject getJson() throws JSONException;





}
