import java.util.Scanner;
import java.util.ArrayList;
import java.util.Random;
//Las personas con prioridad 1 son las que tienen más prioridad
public class Main extends Thread {  //Nuestro local
	private final int maximoPersonas = 20;
	
	public static void main(String[] args){
		int personas = 0;
		//Caja[] caja = new Caja[5]; 
		ArrayList<Persona> listaPersonas = new ArrayList<Persona>(); 
		ArrayList<Persona> listaPersonasPrioridad = new ArrayList<Persona>(); 
		int cant_personas = 0; 
		int contador = 0; 
		//Cajas disponibles
		/*caja[0] = new Caja(1); 
		caja[1] = new Caja(2); 
		caja[2] = new Caja(3); 
		caja[3] = new Caja(4); 
		caja[4] = new Caja(5);*/
		//System.out.println(caja[0].MONTE_MAXIMO);
		
		//Lista de personas en la cola
		ArrayList<Persona> Cola = new ArrayList<Persona>();
		//ArrayList<Persona> Cola1 = new ArrayList<Persona>();

		System.out.println("Ingrese segundos de ejecucion");

		//Cada dia tiene una duración de 2 minutos

		Scanner scan = new Scanner(System.in);
		int tiempo = scan.nextInt() * 1000;
		

		//while(tiempo > 0 ){
			//Se abre una caja 

			
			//Persona P = new Persona(prioridad, monto);

			Caja caja = new Caja(2);
			caja.start();


			// Generar personas cada 3 segundos
			Thread threadGenerador = new Thread(() -> {
				while (caja.getAbierto()) {
					Random rand = new Random();
					int prioridad = rand.nextInt(2);
					int monto = rand.nextInt(300000);
					Persona persona = new Persona(prioridad,monto);
					caja.agregar(persona);
	
					try {
						Thread.sleep(3000); // Esperar 3 segundos
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
			});
			threadGenerador.start();

			// Esperar un tiempo antes de cerrar la caja
			try {
				Thread.sleep(tiempo); 
			} catch (InterruptedException e) {
				e.printStackTrace();
			}

			caja.cerrar();

			try {
				caja.join(); 
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		//}


	}

}
