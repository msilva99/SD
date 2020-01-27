package Teste.fev_2019.v2;

import static java.lang.Thread.sleep;

public class TestarPonte
{
    public static void main(String[] args) throws InterruptedException
    {
        Ponte p = new Ponte();

        int n_carros = 10;
        int n_barcos = 6;

        Thread[] carros = new Thread[n_carros];
        Thread[] barcos = new Thread[n_barcos];

        for (int i = 0; i < n_barcos; i++) {
            barcos[i] = new Thread(new Barco(p,1));
            barcos[i].start();
            sleep(1000);
        }
        for (int i = 0; i < n_carros; i++) {
            carros[i] = new Thread(new Carro(p,1));
            carros[i].start();
            sleep(1000);
        }

        for (int i = 0; i < n_carros; i++) {
            carros[i].join();
        }
        for (int i = 0; i < n_barcos; i++) {
            barcos[i].join();
        }

        System.out.println("Finished!");
    }
}
