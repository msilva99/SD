package Teste.fev_2019;

public class Barco implements Runnable
{
    private Ponte ponte;

    public Barco(Ponte p)
    {
        this.ponte = p;
    }

    @Override
    public void run()
    {
        try {
            this.ponte.entra_barco();
            this.ponte.sai_barco();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
