public class primeDivider {
    public static void main(String[] args) {
        int n = 1136;
        System.out.print(n + " = ");
        factorize(n);
    }
    public static void factorize(int n) {
        if (n <= 1) {
            System.out.println(n);
            return;
        }

        boolean first = true;
        int count = 0;
        while (n % 2 == 0) {
            n /= 2;
            count++;
        }
        if (count > 0) {
            printFactor(2, count, first);
            first = false;
        }
        for (int i = 3; i * i <= n; i += 2) {
            count = 0;
            while (n % i == 0) {
                n /= i;
                count++;
            }
            if (count > 0) {
                printFactor(i, count, first);
                first = false;
            }
        }
        if (n > 1) {
            printFactor(n, 1, first);
        }
    }
    private static void printFactor(int base, int exp, boolean first) {
        if (!first) {
            System.out.print(" * ");
        }
        System.out.print(base);
        if (exp > 1) {
            System.out.print("^" + exp);
        }
    }
}
