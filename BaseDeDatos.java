
public class BaseDeDatos<T> {
	/** Se podria buscar hacer alguna interfaz para que la implementen las 4 clases que usan la base de datos ,
	 * con metodos para buscar y mostrar con distintos parametros (ejemplo precio, cant de pasajeros para una habitacion etc)
	 *  asi podemos metodos de buscar mas personalizados por parametros que nosotros queramos y tambien sirve para
	 * restringir la genericidad de la base de datos solo a esas 4 clases
	 *
	 */
	private ArrayList <T> listado;

    public BaseDeDatos(){
        listado = new ArrayList<>();
    }

    public void agregar(T elemento){
        listado.add(elemento);
    }

    public void mostrar(T elemento){

    }
    
    public T obtener(T elemento) {     //hay que redefinir el equals de las 4 clases
        boolean encontrado = false;
        for (int i = 0; i < listado.size() && !encontrado; i++) {
            if (elemento.equals(listado.get(i))) {
                encontrado = true;
            }
        }
        return elemento;
    }
    
    public void borrar(T elemento){
        listado.remove(elemento);
    }
}
