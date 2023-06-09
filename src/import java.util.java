import java.util.Random;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

class Cliente {
    private int id;
    private int dineroIngresado;

    public Cliente(int id, int dineroIngresado) {
        this.id = id;
        this.dineroIngresado = dineroIngresado;
    }

    public int getId() {
        return id;
    }

    public int getDineroIngresado() {
        return dineroIngresado;
    }
}

class Caja implements Runnable {
    private int id;
    private BlockingQueue<Cliente> colaClientes;
    private int limiteIngreso;
    private boolean abierta;
    private int dineroTotal;
    private boolean cajaCerrada;

    public Caja(int id, BlockingQueue<Cliente> colaClientes, int limiteIngreso, boolean abierta) {
        this.id = id;
        this.colaClientes = colaClientes;
        this.limiteIngreso = limiteIngreso;
        this.abierta = abierta;
        this.dineroTotal = 0;
        this.cajaCerrada = false;
    }

    public void abrirCaja() {
        this.abierta = true;
        System.out.println("Caja " + id + ": ¡Abierta!");
    }

    public void cerrarCaja() {
        this.abierta = false;
        this.cajaCerrada = true;
        System.out.println("Caja " + id + ": Cerrada");
    }

    @Override
    public void run() {
        while (true) {
            try {
                Cliente cliente = colaClientes.take();
                if (!abierta) {
                    abrirCaja();
                }
                int dineroIngresado = cliente.getDineroIngresado();
                System.out.println("Caja " + id + ": Atendiendo al cliente " + cliente.getId() +
                        " - Dinero ingresado: $" + dineroIngresado);
                // Simulación del tiempo de atención
                Thread.sleep(5000);

                dineroTotal += dineroIngresado;
                System.out.println("Caja " + id + ": Dinero total: $" + dineroTotal);

                if (!cajaCerrada && dineroTotal > limiteIngreso) {
                    cerrarCaja();
                }

                if (cajaCerrada && dineroTotal <= limiteIngreso) {
                    cajaCerrada = false;
                }

                if (cajaCerrada && colaClientes.isEmpty()) {
                    break;
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}

public class Banco {
    public static void main(String[] args) {
        BlockingQueue<Cliente> colaClientes = new ArrayBlockingQueue<>(10);
        Thread[] cajas = new Thread[5];
        Random random = new Random();

        // Crear la caja 1 y abrirla
        Caja caja1 = new Caja(1, colaClientes, 500000, true);
        cajas[0] = new Thread(caja1);
        cajas[0].start();

        // Crear las cajas restantes sin abrir
        for (int i = 1; i < 5; i++) {
            cajas[i] = new Thread(new Caja(i + 1, colaClientes, 500000, false));
        }

        // Generar clientes cada 3 a 5 segundos
        int contadorClientes = 1;
        while (true) {
            int tiempoEspera = random.nextInt(3001) + 3000; // Rango de 3000 a 6000 milisegundos
            int dineroIngresado = random.nextInt(150001) + 150000; // Rango de 150000 a 300000
            Cliente cliente = new Cliente(contadorClientes, dineroIngresado);
            try {
                colaClientes.put(cliente);
                System.out.println("Cliente " + cliente.getId() + " en cola - Dinero ingresado: $" + cliente.getDineroIngresado());
                contadorClientes++;

                Thread.sleep(tiempoEspera);

                // Verificar si la cantidad de clientes es múltiplo de 3
                if (contadorClientes % 3 == 0) {
                    // Abrir la siguiente caja cerrada
                    for (int i = 1; i < 5; i++) {
                        if (!cajas[i].isAlive()) {
                            cajas[i].start();
                            break;
                        }
                    }
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}