public class LCG {

    long mod;
    long mul;
    long inc;
    long val;
    boolean fullRange;

    public LCG(int mod, int mul, int inc, int see) {
        this.mod = mod;
        this.mul = mul;
        this.inc = inc;
        this.val = see;
        this.fullRange = false;
    }

    public LCG(int range, int see) {

        this.mod = (int) Math.pow(2, (int) (Math.log10(range)/Math.log10(2) + 1));
        this.inc = (mod + 1);
        this.mul = 4 * (mod + 1) + 1;
        this.val = see;
        this.fullRange = true;
    }

    public long rangeNext(long max) {
        return (next()*max)/mod;
    }

    public long isFullRange() {

        // -1 can't be full range, 0 might be full range, 1 is full range

        if (fullRange) {return 1;}
        boolean type1 = (new SOE((int) mod).isPrime((int) mod) && inc == 0);
        boolean type2 = (new EEA((int) mod, (int) inc).areCoprime() && ((!((mul - 1) % 4 == 0) && !(mod % 4 == 0)) || (((mul - 1) % 4 == 0) && (mod % 4 == 0))));
        if (type1 || type2) {return 0;};
        return -1;

    }

    public long maxValue() {
        return mod;
    }

    public long next() {
        long tmpVal = val;
        val = (mul * val + inc) % mod;
        return tmpVal;
    }

    public void skip(int steps) {
        for (int i = 0; i < steps; i++) {
        next();
        }
    }


}
