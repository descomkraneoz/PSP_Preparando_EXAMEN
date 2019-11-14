package Monitor;

public class Barrera {
    private int entradaLibre=1;

    public synchronized void entraCoche(int i) throws InterruptedException {
        while (entradaLibre == 0) {
            wait();
            System.out.println("El coche " + i + " cruza la barrera de entrada");
            entradaLibre--;
        }
    }

    public synchronized void saleCoche(int i) throws InterruptedException {
        entradaLibre++;
        System.out.println("El coche " + i + " cruza la barrera de salida");
        notify();
    }
}
