
import java.util.concurrent.BlockingQueue;

public class Caja extends Thread{
	final int MONTE_MAXIMO = 500000;
	private BlockingQueue<Persona> lista;
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
		try{
			if(!abierto){
					abrir();
				}
				Persona temp = lista.take();
					synchronized(lista){
						while(lista.isEmpty()){
							try{
								lista.wait();
							} catch(InterruptedException e){
								e.printStackTrace();
							}
						}
						
						this.montoRecaudado += temp.getMonto();
						System.out.println("MONTO RECAUDADO "+this.montoRecaudado );
					}
					try{
						Thread.sleep(2000);
					} catch(InterruptedException e){
						e.printStackTrace();
					}
		}catch(InterruptedException e){
			e.printStackTrace();
		}
		System.out.println("Caja "+this.numeroCaja);
		System.out.println("Monto recaudado: "+this.montoRecaudado);
	}
	
	
	 
	public Caja(int x, boolean abierto ,BlockingQueue<Persona> colas) {
		this.numeroCaja = x;
		this.abierto = abierto;
		this.lista = colas;
	}
	
	public void agregar(Persona p){
		synchronized(lista){
			lista.add(p);
			lista.notify();
		}
	}

	public void abrir(){
		this.abierto = true;
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
