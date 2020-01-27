package Teste.jul_2019;

public class Poly implements Runnable
{
    private OrganizacaoVisitas v;

    public Poly(OrganizacaoVisitas v)
    {
        this.v = v;
    }

    @Override
    public void run()
    {
        try {
            v.enterPoly();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
