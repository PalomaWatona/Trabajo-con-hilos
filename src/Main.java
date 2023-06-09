
import java.util.Random;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
//Las personas con prioridad 1 son las que tienen más prioridad
public class Main extends Thread {  //Nuestro local
	private final int maximoPersonas = 20;
	
	public static void main(String[] args){
		BlockingQueue<Persona> Cola = new ArrayBlockingQueue<Persona>(20); //maximo de personas
		Caja caja1 = new Caja(1, true,Cola);

		Thread[] cajas = new Thread[5];
		cajas[0] = new Thread(caja1);

		for (int i = 1; i < 5; i++) {
            cajas[i] = new Thread(new Caja(i + 1,false,Cola));
        }

		

		int count = 1;
		int c = 0;
		while (true) {
			Random rand = new Random();
			Boolean prioridad = rand.nextBoolean();
			int monto = rand.nextInt(300000);
            Persona nueva = new Persona(prioridad, monto);
            try {
                Cola.add(nueva);

                System.out.println("Cliente " + count + " en cola - Dinero ingresado: $" + nueva.getMonto());
                count++;

                Thread.sleep(5000);

                // Verificar si la cantidad de clientes es múltiplo de 3
                if (count % 3 == 0) {
                    // Abrir la siguiente caja cerrada
                    for (int i = 1; i < 5; i++) {
                        if (cajas[i].isAlive() == false) {
							if (cajas[i].getState() == Thread.State.NEW) {
								System.out.println("CAJA " + i);
                        		cajas[i].start();
								break;
                    		}
                        }
                    }
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }


		//caja.start();


			// Generar personas cada 3 segundos
			/* 
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

			*/

	}

}
