package Monitor;

public class Coche extends Thread {
    private int id;
    private Puente virgen;
    private Barrera b;

    public Coche(int id, Puente virgen, Barrera b){
        this.id=id;
        this.virgen=virgen;
        this.b=b;
    }

    public void run(){
        while (true) {
            try{
                //Llega el coche al puente
                System.out.println("El coche "+id+" llega al puente");
                virgen.entraPuente(id);
                //LLega a la barrera
                System.out.println("El coche "+id+" espera en la barrera del puente");
                b.entraCoche(id);
                System.out.println("El coche "+id+" recorre el puente");
                Thread.sleep(3000);
                virgen.salePuente(id);
                b.saleCoche(id);
                System.out.println("El coche "+id+" sale del puente");
            }catch (InterruptedException e){
                e.printStackTrace();
            }
        }

    }

}
