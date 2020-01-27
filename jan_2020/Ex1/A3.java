package Teste.jan_2020.Ex1;

public class A3 implements Runnable
{
    private Aeroporto aeroporto;

    public A3(Aeroporto a)
    {
        this.aeroporto = a;
    }

    @Override
    public void run()
    {
        int pista = -1;
        try {
            pista = this.aeroporto.pedirParaDescolar();
            this.aeroporto.descolou(pista);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        try {
            pista = this.aeroporto.pedirParaAterrar();
            this.aeroporto.aterrou(pista);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        try {
            pista = this.aeroporto.pedirParaAterrar();
            this.aeroporto.aterrou(pista);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
