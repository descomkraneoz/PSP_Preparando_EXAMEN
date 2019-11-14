package Monitor;/*
* En la ciudad de Elche se está realizando reparaciones en el puente de la Virgen.
* El ayuntamiento ha decido establecer un sistema digital que permita los accesos y salidas de coches del puente,
* ya que solo hay un carril. El puente tiene una entrada y una salida,
* los coches que entran por la entrada sur salen por la salida norte y viceversa.
* En cada entrada existe un detector y una barrera. En cada salida existe un detector,
* (supón que cada coche tarda en salir del puente 2 segundos).

Realiza un programa en Java que gestione los accesos y salidas de coches en el puente de la Virgen,
* sabiendo que cuando entran por el norte, no pueden entrar por el sur y viceversa.
* (NOTA: elige entre el uso de semáforos o monitores)

* */

public class Main {
    public static void main(String[] args) {
        Barrera b=new Barrera();
        Puente p=new Puente();

        Coche[] coches=new Coche[5];
        for (int i = 0; i <coches.length ; i++) {
            coches[i]=new Coche(i,p,b);
        }

        for (int i = 0; i < coches.length; i++) {
            coches[i].start();
        }
    }
}
