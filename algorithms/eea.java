public class EEA {

    int a;
    int b;
    int x;
    int y;
    int gcd;


    public EEA(int a, int b) {

        this.a = a;
        this.b = b;
        int[] data = algorithm(a,b);
        gcd = data[0];
        x = data[1];
        y = data[2];

    }

    public Integer inverse(String option) {

        if (!areCoprime()) {
            return null;
        } else if (option.equals("a")) {
            return x;
        } else if (option.equals("b")) {
            return y;
        } else {
            return null;
        }

    }

    public boolean areCoprime() {
        return (gcd == 1);
    }

    public int getLcm() {
        return a * b * gcd;
    }

    private static int[] algorithm(int a, int b) {

        int[] r = new int[999];
        int[] t = new int[999];
        int[] s = new int[999];
        int i;

        r[0] = a;
        r[1] = b;
        s[0] = 1;
        s[1] = 0;
        t[0] = 0;
        t[1] = 1;


        for (i = 1; r[i] != 0 ; i++) {
            int q = r[i - 1]/r[i];
            r[i + 1] = r[i - 1] - q * r[i];
            s[i + 1] = s[i - 1] - q * s[i];
            t[i + 1] = t[i - 1] - q * t[i];

        }

        return new int[] {r[i - 1], s[i - 1], t[i - 1]};
    }


}
