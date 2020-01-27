package Teste.jan_2020.Ex1;

public class TestarAeroporto
{
    public static void main(String[] args) throws InterruptedException
    {
        Aeroporto a = new Aeroporto(3);
        A1 a1 = new A1(a);
        A2 a2 = new A2(a);
        A3 a3 = new A3(a);
        A1 a4 = new A1(a);
        A2 a5 = new A2(a);
        A3 a6 = new A3(a);

        Thread t1 = new Thread(a1);
        Thread t2 = new Thread(a2);
        Thread t3 = new Thread(a3);
        Thread t4 = new Thread(a4);
        Thread t5 = new Thread(a5);
        Thread t6 = new Thread(a6);

        t1.start();
        t2.start();
        t3.start();
        t4.start();
        t5.start();
        t6.start();
    }
}

//        new Thread(()->{
//        }).start();