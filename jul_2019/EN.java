package Teste.jul_2019;

public class EN implements Runnable
{
    private OrganizacaoVisitas v;

    public EN(OrganizacaoVisitas v)
    {
        this.v = v;
    }

    @Override
    public void run()
    {
        try {
            v.enterEN();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
