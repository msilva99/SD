package Teste.fev_2019.v2;

import static java.lang.Thread.sleep;

public class Carro implements Runnable
{
    private Ponte ponte;
    private int wait_time;

    public Carro(Ponte p, int wait_time)
    {
        this.ponte = p;
        this.wait_time = wait_time * 1000; // no teste seria isto * 60 (=60000)
    }

    @Override
    public void run()
    {
        try {
            this.ponte.entra_carro();
            sleep(wait_time);
            this.ponte.sai_carro();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
