package Teste.fev_2019;

import Teste.jul_2019.EN;
import Teste.jul_2019.Guide;
import Teste.jul_2019.PT;
import Teste.jul_2019.Poly;

public class TestarPonte
{
    public static void main(String[] args) throws InterruptedException
    {
        Ponte p = new Ponte();

        int n_carros = 6;
        int n_barcos = 6;

        Thread[] carros = new Thread[n_carros];
        Thread[] barcos = new Thread[n_barcos];

        for (int i = 0; i < n_carros; i++) {
            carros[i] = new Thread(new Carro(p));
            carros[i].start();
        }
        for (int i = 0; i < n_barcos; i++) {
            barcos[i] = new Thread(new Barco(p));
            barcos[i].start();
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
