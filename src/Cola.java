//Clase que no se utiliza actualmente, ya que utilizamos ArrayList
import java.util.ArrayList;
public class Cola<T> extends Thread {
	private ArrayList<T> cola;

	

	@Override
	public void run(){

	}
	public Cola() {
		cola = new ArrayList<>();	
	}

	public void agregarPersona(T persona){
		cola.add(persona);
	}

	public void eliminarPersona(T persona){
		cola.remove(persona);

	}

}
