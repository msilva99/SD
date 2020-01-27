package Teste.fev_2019.v2;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Ponte implements Control
{
    private boolean ponte_levantada;
    private int carros_na_ponte;
    private boolean ha_barco_em_espera;

    Lock ponte_lock = new ReentrantLock();
    Condition c1 = ponte_lock.newCondition(); // espera enquanto ha um barco em espera ou avisa quando ja nao ha
    Condition c2 = ponte_lock.newCondition(); // espera enquanto ha carros na ponte ou avisa quando ??

    public Ponte()
    {
        this.ponte_levantada = false;
        this.carros_na_ponte = 0;
        this.ha_barco_em_espera = false;
    }

    @Override
    public void entra_carro() throws InterruptedException
    {
        ponte_lock.lock();
        while(ponte_levantada || ha_barco_em_espera)
        {
            c1.await();
        }
        this.carros_na_ponte++;
        System.out.println("Carro a entrar (" + carros_na_ponte + ")");
        ponte_lock.unlock();
    }

    @Override
    public void sai_carro()
    {
        ponte_lock.lock();
        this.carros_na_ponte--;
        System.out.println("Carro saiu (" + carros_na_ponte + ")");
        if(carros_na_ponte == 0)
        {
            c2.signalAll();
            System.out.println("A avisar que nao ha carros a passar");
        }
        ponte_lock.unlock();
    }

    @Override
    public void entra_barco() throws InterruptedException
    {
        System.out.println("Esperando 5 min");
        Thread.sleep(3000); // para ser 5 min teria de ser 300000
        ponte_lock.lock();
        while(ha_barco_em_espera)
        {
            c1.await();
        }
        ha_barco_em_espera = true;
        System.out.println("Barco em espera para passar");
        while(carros_na_ponte > 0)
        {
            c2.await();
        }
        this.ponte_levantada = true;
        System.out.println("Ponte levantada, barco a passar");
        ponte_lock.unlock();
    }

    @Override
    public void sai_barco()
    {
        ponte_lock.lock();
        System.out.println("Barco saiu, ponte desceu");
        this.ha_barco_em_espera = false;
        this.ponte_levantada = false;
        c1.signalAll();
        ponte_lock.unlock();
    }
}
