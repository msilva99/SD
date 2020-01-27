package Teste.fev_2019;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Ponte implements Control
{
    private boolean levantada;
    private boolean carros_a_passar;
    private boolean barcos_a_passar;
    Lock ponte_lock = new ReentrantLock();
    Condition c = ponte_lock.newCondition();

    public Ponte()
    {
        this.levantada = false;
        this.carros_a_passar = false;
        this.barcos_a_passar = false;
    }

    @Override
    public void entra_carro() throws InterruptedException
    {
        ponte_lock.lock();
        while(this.levantada || this.barcos_a_passar)
        {
            c.await();
        }
        System.out.println("Carro a entrar");
        this.carros_a_passar = true;
        ponte_lock.unlock();
    }

    @Override
    public void sai_carro()
    {
        ponte_lock.lock();
        this.levantada = true;
        this.carros_a_passar = false;
        System.out.println("Carro saiu");
        c.signalAll();
        ponte_lock.unlock();
    }

    @Override
    public void entra_barco() throws InterruptedException
    {
        ponte_lock.lock();
        System.out.println("Esperando 5 min");
        Thread.sleep(3000); // para ser 5 min teria de ser 300000
        while(!this.levantada || this.carros_a_passar)
        {
            c.await();
        }
        System.out.println("Barco a passar");
        this.barcos_a_passar = true;
        ponte_lock.unlock();
    }

    @Override
    public void sai_barco()
    {
        ponte_lock.lock();
        this.levantada = false;
        this.barcos_a_passar = false;
        System.out.println("Barco saiu");
        c.signalAll();
        ponte_lock.unlock();
    }
}
