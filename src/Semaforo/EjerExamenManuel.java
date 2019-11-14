package Semaforo;

import java.util.concurrent.Semaphore;

/*2- En la ciudad de Elche se está realizando reparaciones en el puente de la Virgen.
 El ayuntamiento ha decido establecer un sistema digital que permita los accesos y salidas de coches del puente,
 ya que solo hay un carril. El puente tiene una entrada y una salida, los coches que entran por la entrada sur
 salen por la salida norte y viceversa. En cada entrada existe un detector y una barrera. En cada salida existe un detector,
 (supón que cada coche tarda en salir del puente 2 segundos).

Realiza un programa en Java que gestione los accesos y salidas de coches en el puente de la Virgen,
sabiendo que cuando entran por el norte, no pueden entrar por el sur y viceversa.
(NOTA: elige entre el uso de semáforos o monitores) (4 puntos)*/

public class EjerExamenManuel {
    public static void main(String[] args) {
        Puente p=new Puente();
        Coche c=new Coche(p,"Coche 1");
        Coche c1=new Coche(p,"Coche 2");
        Coche c2=new Coche(p,"Coche 3");
        Coche c3=new Coche(p,"Coche 4");

        c.start();
        c1.start();
        c2.start();
        c3.start();
    }
}
class Puente{
    Semaphore semaforo;
    private boolean ocupado;
    Puente(){
        semaforo=new Semaphore(1);
        ocupado=false;
    }

    public boolean isOcupado() {
        return ocupado;
    }

    public void setOcupado(boolean ocupado) {
        this.ocupado = ocupado;
    }
}
class Coche extends Thread{
    Puente puente;
    Coche(Puente puente,String s){
        this.puente=puente;
        this.setName(s);
    }
    public void pasarSemaforo(){
        if(puente.semaforo.tryAcquire()){
            try {
                System.out.println(this.getName() + "esta pasando");
                this.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(this.getName()+"ha pasado");
            puente.semaforo.release();
        }else{
            System.out.println(this.getName() + "esta esperando");
        }
    }
    public synchronized void pasarMonitor(){
        while(puente.isOcupado()){
            try {
                System.out.println("El coche "+this.getName()+" esta esperando");
                wait();
            } catch (InterruptedException e) {
                System.out.println("Error al esperar que el coche terminara de pasar");
            }
        }
        if(puente.isOcupado()){
            throw new IllegalArgumentException("El coche" +this.getName()+"intentó pasar con el puente ocupado");
        }
        puente.setOcupado(true);
        System.out.println("El coche "+this.getName()+" esta pasando");
        try {
            this.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("El coche "+this.getName()+" ha pasado");
        puente.setOcupado(false);
        notifyAll();
    }
    @Override
    public void run() {
        while (true) {
            pasarSemaforo();
            try {
                this.sleep(4000);
            } catch (InterruptedException e) {
                System.out.println("Error al esperar para intentar volver a pasar");
            }
        }
    }
}
