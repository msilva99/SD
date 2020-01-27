package Teste.jul_2019;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class OrganizacaoVisitas implements Museu
{
    private int min;
    private int max;
    private boolean[] guias; // se ja existe guia no grupo - 0: pt, 1: en
    private int[] visitantes; // numero de visitantes no grupo - 0: pt/poly, 1: en/poly

    // fazia sentido ter um lock para cada grupo ja que sao meio que "independentes" ?? nao sei, nao vou por
    private Lock visita_lock = new ReentrantLock();
    private Condition[] grupos;
    private Condition c_guia = visita_lock.newCondition();
    private Condition c_poly = visita_lock.newCondition();


    public OrganizacaoVisitas()
    {
        this.min = 5; // no teste seria 10
        this.max = 10; // no teste seria 15
        this.guias = new boolean[]{false,false};
        this.visitantes = new int[]{0,0};
        this.grupos = new Condition[]{visita_lock.newCondition(),visita_lock.newCondition()};
    }

    private int precisa_de_poly()
    {
        if (visitantes[0] >= max && visitantes[1] >= max)
        {
            return -1;
        }
        if (visitantes[0] >= max)
        {
            return 1;
        }
        if (visitantes[1] >= max)
        {
            return 0;
        }
        return ((visitantes[0] > visitantes[1]) ? 0 : 1);
    }

    private int precisa_de_guia()
    {
        if(!guias[0] && !guias[1]){
            return ((visitantes[0] > visitantes[1]) ? 0 : 1);
        }
        if(!guias[0])
        {
            return 0;
        }
        if(!guias[1])
        {
            return 1;
        }
        return -1;
    }

    @Override
    public void enterPT() throws InterruptedException
    {
        visita_lock.lock();

        while(visitantes[0] >= max)
        {
            grupos[0].await();
        }
        visitantes[0]++;
        System.out.println("Grupo 0 tem mais um visitante PT (" + visitantes[0] + ", " + guias[0] + ")");

        c_guia.signalAll();
        c_poly.signalAll();

        visita_lock.unlock();
    }

    @Override
    public void enterEN() throws InterruptedException
    {
        visita_lock.lock();

        while(visitantes[1] >= max)
        {
            grupos[1].await();
        }
        visitantes[1]++;
        System.out.println("Grupo 1 tem mais um visitante EN (" + visitantes[1] + ", " + guias[1] + ")");

        c_guia.signalAll();
        c_poly.signalAll();

        visita_lock.unlock();
    }

    @Override
    public void enterPoly() throws InterruptedException
    {
        visita_lock.lock();

        int indice;
        while((indice = precisa_de_poly()) < 0)
        {
            c_poly.await();
        }
        visitantes[indice]++;
        System.out.println("Grupo " + indice + " tem mais um visitante poly (" + visitantes[indice] + ", " + guias[indice] + ")");

        c_guia.signalAll();

        visita_lock.unlock();
    }

    @Override
    public void enterGuide() throws InterruptedException
    {
        visita_lock.lock();

        int indice;
        while((indice = precisa_de_guia()) < 0)
        {
            c_guia.await();
        }
        guias[indice] = true;
        System.out.println("Grupo " + indice + " encontrou guia (" + visitantes[indice] + ", " + guias[indice] + ")");
//        grupos[indice].signalAll();

        while(visitantes[indice] < this.min)
        {
            c_guia.await();
        }
        guias[indice] = false;
        visitantes[indice] = 0;
        System.out.println("Grupo " + indice + " pronto e voltou ao inicio");

        grupos[indice].signalAll();
        c_poly.signalAll();

        visita_lock.unlock();
    }
}