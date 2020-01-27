package Teste.fev_2019;

public class Carro implements Runnable
{
    private Ponte ponte;

    public Carro(Ponte p)
    {
        this.ponte = p;
    }

    @Override
    public void run()
    {
        try {
            this.ponte.entra_carro();
            this.ponte.sai_carro();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
