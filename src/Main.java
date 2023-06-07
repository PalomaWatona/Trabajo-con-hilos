import java.util.Scanner;
import java.util.ArrayList;
import java.util.Random;

public class Main {  //Nuestro local
	private final int maximoPersonas = 20;
	
	
	public static void main(String[] args){
		int personas = 0;
		
		ArrayList<Persona> Cola0 = new ArrayList<Persona>();
		ArrayList<Persona> Cola1 = new ArrayList<Persona>();

		System.out.println("Ingrese cantidad de dias");


		Scanner scan = new Scanner(System.in);
		int tiempo = scan.nextInt();
		while(tiempo > 0 ){
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

			if(Cola0.size() % 3 == 0){
				Caja test = new Caja(rand.nextInt(5));
				test.start();
			}

			//System.out.println("Prio "+P.getPrioridad() + "Monto "+P.getMonto());
		tiempo--;
		}
		scan.close();
	}

}
