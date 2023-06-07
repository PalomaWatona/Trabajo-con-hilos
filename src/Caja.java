



public class Caja extends Thread {
	final int MONTE_MAXIMO = 500000;
	private int numeroCaja;
	private boolean atendiendo;
	private int montoRecaudado;
	private int montoTotalRecaudado;
	private int personasAtendidas;
	private int numeroCierres;
	
	@Override
	public void run(){
		System.out.println("aaaaaaaaa");
	}
	 
	public Caja(int x) {
		this.numeroCaja = x;
	}
	
	public void setMontoCajaTotal(int a) {
		this.montoTotalRecaudado = a;
	}
	
	public void setAtenciones(int a) {
		this.personasAtendidas = a;
	}
	
	public void setCierres(int a) {
		this.numeroCierres = a;
	}
	
	public void setMontoCaja(int a) {
		this.montoRecaudado = a;
	}
	
	public void setAtendiendo(boolean x) {
		this.atendiendo = x;
	}
	
	public int getMontoCajaTotal() {
		return this.montoTotalRecaudado;
	}
	
	public int getAtenciones() {
		return this.personasAtendidas;
	}
	
	public int getCierres() {
		return this.numeroCierres;
	}
	
	public int getMontoCaja() {
		return this.montoRecaudado;
	}
	
	public boolean getAtendiendo() {
		return this.atendiendo;
	}

	public int getNumeroCaja(){
		return this.numeroCaja;
	}
}
