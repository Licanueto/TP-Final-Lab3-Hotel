import java.util.HashMap;

public class Logueo {
    private HashMap<String,SerVivo> mapeo;

    public Logueo(){
        mapeo = new HashMap<>();
    }
    public void agregar(SerVivo serVivo){
        mapeo.put(serVivo.getDni(),serVivo);
    }

    public HashMap<String,SerVivo> getMapeo() {
        return mapeo;
    }
    public boolean buscar(String documento){
        if(mapeo.get(documento) == null){
            throw new UsuarioInexistenteException("Usuario incorrecto, vuelva a ingresar documento...");
        }
        return true;
    }
    public boolean compruebaClave(String clave,SerVivo algunaCosa){
        if (!clave.equals(algunaCosa.getPass())){
            throw new ClaveIncorrectaException("Clave incorrecta, vuelva a ingresar....");
        }
        return true;
    }
}
