package Teste.fev_2019.v2;

import static java.lang.Thread.sleep;

public class Barco implements Runnable
{
    private Ponte ponte;
    private int wait_time;

    public Barco(Ponte p, int wait_time)
    {
        this.ponte = p;
        this.wait_time = wait_time * 1000; // no teste seria isto * 60 (=60000)
    }

    @Override
    public void run()
    {
        try {
            this.ponte.entra_barco();
            sleep(wait_time);
            this.ponte.sai_barco();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
