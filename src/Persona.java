

public class Persona {
	
	private boolean Prioridad;
	private int monto;

	
	public Persona(Boolean P, int M){
		this.Prioridad = P;
		this.monto = M;

	}
	
	
	public boolean getPrioridad() {
		return this.Prioridad;
	}
	
	public int getMonto() {
		return this.monto;
	}

}
