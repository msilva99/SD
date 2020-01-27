package Teste.jul_2019;

public class PT implements Runnable
{
    private OrganizacaoVisitas v;

    public PT(OrganizacaoVisitas v)
    {
        this.v = v;
    }

    @Override
    public void run()
    {
        try {
            v.enterPT();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
