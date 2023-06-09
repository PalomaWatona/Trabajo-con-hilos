import java.util.ArrayList;

public class Caja extends Thread{
	final int MONTE_MAXIMO = 500000;
	private ArrayList<Persona> lista;
	//private Persona persona;
	private int numeroCaja;
	public  boolean abierto;
	private boolean atendiendo;
	private int montoRecaudado;
	private int montoTotalRecaudado;
	private int personasAtendidas;
	private int numeroCierres;



	@Override
	public void run(){
		while(abierto){
			synchronized(lista){
				while(lista.isEmpty()){
					try{
						lista.wait();
					} catch(InterruptedException e){
						e.printStackTrace();
					}
				}
				Persona temp = lista.remove(0);
				this.montoRecaudado += temp.getMonto();
				System.out.println("Atendiendo con dinero: "+ temp.getMonto());
			}
			try{
				Thread.sleep(2000);
			} catch(InterruptedException e){
				e.printStackTrace();
			}
		}
		/* 
		System.out.println("Caja: "+this.numeroCaja);
		
		System.out.println("Monto total recaudado: "+this.montoTotalRecaudado);
		System.out.println("Personas atendidas: "+this.personasAtendidas);
		System.out.println("Atendiendo: "+this.atendiendo);
		*/
		System.out.println("Monto recaudado: "+this.montoRecaudado);
	}
	
	
	 
	public Caja(int x) {
		this.numeroCaja = x;
		this.abierto = true;
		lista = new ArrayList<>();
	}
	
	public void agregar(Persona p){
		synchronized(lista){
			lista.add(p);
			lista.notify();
		}
	}

	public void cerrar() {
        abierto = false;
        synchronized (lista) {
            lista.notifyAll(); 
        }
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

	public boolean getAbierto(){
		return this.abierto;
	}
}
