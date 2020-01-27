package Teste.jul_2019;

public class TestarVisitas
{
    public static void main(String[] args) throws InterruptedException
    {
        OrganizacaoVisitas v = new OrganizacaoVisitas();

        int n_pt = 7;
        int n_en = 7;
        int n_poly = 6;
        int n_guide = 2;

        Thread[] pt = new Thread[n_pt];
        Thread[] en = new Thread[n_en];
        Thread[] poly = new Thread[n_poly];
        Thread[] guide = new Thread[n_guide];

        for (int i = 0; i < n_pt; i++) {
            pt[i] = new Thread(new PT(v));
            pt[i].start();
        }
        for (int i = 0; i < n_en; i++) {
            en[i] = new Thread(new EN(v));
            en[i].start();
        }
        for (int i = 0; i < n_poly; i++) {
            poly[i] = new Thread(new Poly(v));
            poly[i].start();
        }
        for (int i = 0; i < n_guide; i++) {
            guide[i] = new Thread(new Guide(v));
            guide[i].start();
        }

        for (int i = 0; i < n_pt; i++) {
            pt[i].join();
        }
        for (int i = 0; i < n_en; i++) {
            en[i].join();
        }
        for (int i = 0; i < n_poly; i++) {
            poly[i].join();
        }
        for (int i = 0; i < n_guide; i++) {
            guide[i].join();
        }

        System.out.println("Finished!");
    }
}
