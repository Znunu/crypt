import java.util.Arrays;

public class SOE {

    private final boolean[] primes;

    public SOE(int max) {
        primes = algorithm(max);
    }

    public int[] primes() {

        int primeTally = 0;
        for (boolean isPrime : primes) {
            if (isPrime) {
                primeTally++;
            }
        }
        int[] primes2 = new int[primeTally];
        int j = 0;
        for (int i = 0; i < primes.length; i++) {
            if (primes[i]) {
                primes2[j] = i;
                j++;
            }
        }
        return primes2;
    }

    public int primeAt(int start) {

        for (int i = start; i < primes.length; i++) {
            if (primes[i]) {
                return i;
            }
        }
        return -1;

    }

    private boolean[] algorithm(int max) {

        boolean[] primes = new boolean[max + 1];
        Arrays.fill(primes, true);

        for (int i = 2; i < primes.length; i++) {
            if (primes[i] == true) {

                primes = markMult(primes, i);
                primes[i] = true;

            }

        }
        return primes;
    }

    private boolean[] markMult(boolean[] array, int value) {
        for (int i = 1; i < array.length; i++) {
            if (i%value == 0){

                array[i] = false;

            }
        }
        return array;
    }

    public boolean isPrime(int val) {
        return primes[val];
    }

}
