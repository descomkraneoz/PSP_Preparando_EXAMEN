package Monitor;

public class Puente {

    private boolean puenteLibre=true;

    public Puente(){

    }

    public synchronized void entraPuente(int i) throws InterruptedException{
        while (!puenteLibre){
            wait();
            System.out.println("El coche "+i+" llega al puente ");
            puenteLibre=false;
        }
    }

    public synchronized void salePuente(int i) throws InterruptedException{
        puenteLibre=true;
        System.out.println("El coche "+i+" sale del puente ");
        notify();
    }

}
