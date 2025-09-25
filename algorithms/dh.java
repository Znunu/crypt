import java.lang.Math;
import java.util.Random;

public class keyGen {

    private int p;
    private int a;
    private static int MAX_VALUE = 99999;
    private static Random rnd = new Random();


    public keyGen(int p, int a){
        this.p = p;
        this.a = a;
    }

    public int getP() {
        return p;
    }

    public int genA(int g) {
        return (int) (Math.pow(g,a)) % p;
    }

    public int genk(int B) {
        return (int) (Math.pow(B,a) % p);
    }

}
