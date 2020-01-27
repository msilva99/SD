package Teste.jan_2020.Ex1;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Aeroporto implements ControloTrafegoAereo
{
    private int num;
    private int[] pistas = new int[num];
    private Lock pista_lock = new ReentrantLock();
    private Condition utilizar_pista = pista_lock.newCondition();

    public Aeroporto(int num)
    {
        this.num = num;
        this.pistas = new int[num];
        for(int i = 0; i < this.num; i++)
            this.pistas[i] = 0;
    }

    private int encontra_pista() throws InterruptedException
    {
        int i, livre = -1;
        for(i = 0; i < this.num && this.pistas[i] == 1; i++);
        if (i == this.num) i--;
        if (this.pistas[i] == 0)
        {
            livre = i;
        }
        return livre;
    }

    @Override
    public int pedirParaDescolar() throws InterruptedException
    {
        pista_lock.lock();
        int pistaLivre;
        System.out.println("A procura de pista para descolar");
        while((pistaLivre = encontra_pista()) < 0)
        {
            utilizar_pista.await(); // espera enquanto nao ha pistas livres
            System.out.println("Esperando por uma pista");
        }
        this.pistas[pistaLivre] = 1; // reserva pista
        System.out.println("Pista " + pistaLivre + " livre para descolar");
        pista_lock.unlock();
        return pistaLivre; // devolve numero da pista
    }

    @Override
    public int pedirParaAterrar() throws InterruptedException
    {
        pista_lock.lock();
        int pistaLivre;
        System.out.println("A procura de pista para aterrar");
        while((pistaLivre = encontra_pista()) < 0)
        {
            utilizar_pista.await(); // espera enquanto nao ha pistas livres
        }
        this.pistas[pistaLivre] = 1; // reserva pista

        System.out.println("Pista " + pistaLivre + " livre para aterrar");
        pista_lock.unlock();
        return pistaLivre; // devolve numero da pista
    }

    @Override
    public void descolou(int pista)
    {
        pista_lock.lock();
        this.pistas[pista] = 0; // liberta pista
        System.out.println("Aviao descolou da pista " + pista);
        utilizar_pista.signal(); // avisa outros avioes
        pista_lock.unlock();
    }

    @Override
    public void aterrou(int pista)
    {
        pista_lock.lock();
        this.pistas[pista] = 0; // liberta pista
        System.out.println("Aviao aterrou na pista " + pista);
        utilizar_pista.signal(); // avisa outros avioes
        pista_lock.unlock();
    }
}
