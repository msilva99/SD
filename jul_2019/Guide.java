package Teste.jul_2019;

public class Guide implements Runnable
{
    private OrganizacaoVisitas v;

    public Guide(OrganizacaoVisitas v)
    {
        this.v = v;
    }

    @Override
    public void run()
    {
        try {
            v.enterGuide();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
