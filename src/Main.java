import java.util.Scanner;
import java.util.ArrayList;
import java.util.Random;
//Las personas con prioridad 1 son las que tienen más prioridad
public class Main extends Thread {  //Nuestro local
	private final int maximoPersonas = 20;
	
	
	public static void main(String[] args){
		int personas = 0;
		Caja[] caja = new Caja[5]; 
		ArrayList<Persona> listaPersonas = new ArrayList<Persona>(); 
		ArrayList<Persona> listaPersonasPrioridad = new ArrayList<Persona>(); 
		int cant_personas = 0; 
		int contador = 0; 
		caja[0] = new Caja(1); 
		caja[1] = new Caja(2); 
		caja[2] = new Caja(3); 
		caja[3] = new Caja(4); 
		caja[4] = new Caja(5); 
		System.out.println(caja[0].MONTE_MAXIMO);
		ArrayList<Persona> Cola0 = new ArrayList<Persona>();
		ArrayList<Persona> Cola1 = new ArrayList<Persona>();

		System.out.println("Ingrese cantidad de dias");

		//Cada dia tiene una duración de 2 minutos
		Scanner scan = new Scanner(System.in);
		int tiempo = scan.nextInt();
		//Todo el sistema debe estar acá dentro
		while(tiempo > 0 ){
			//Se abre una caja 
			if(cant_personas % 3 == 0){ 
				caja[contador].start(); 
				contador++; 
				if (contador == 5){ 
					contador = 0; 
				} 
			} 
			Random rand = new Random();
			int prioridad = rand.nextInt(2);
			int monto = rand.nextInt(500000);
			Persona P = new Persona(prioridad, monto);
			personas++;

			if(P.getPrioridad() == 0){
				Cola0.add(P);
			}else{
				Cola1.add(P);
			}
			System.out.println("Prio "+P.getPrioridad() + "Monto "+P.getMonto());
		tiempo--;
		}
		scan.close();
	}

}
