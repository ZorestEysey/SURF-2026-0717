import java.util.Arrays;

public class uniCSPF {

    static int n = 3;
    static int index = 1;
    static int carNum = 1;
    static int disp = 7;

    public static void main(String[] args) {
        // printAllCSPF();

        // printOneCSPF(index);

        // System.out.println(getDisp(index, carNum));

        // printUniCSPF();

        // System.out.println(countUniCSPF());

        // System.out.println(Arrays.toString(computeTotalDisp()));

        // printCSPFByDisp(disp);

        // System.out.println(computeLeftDisp(n));

        // System.out.println(computeRightDisp(n));

        // System.out.println(computeNetDisp(n));

    }

    public static int[] simulate(int[] pref) {
        int n = pref.length;
        boolean[] occupied = new boolean[n];
        int[] finalPos = new int[n];

        for (int i = 0; i < n; i++) {
            int p = pref[i] - 1;
            if (!occupied[p]) {
                finalPos[i] = p;
                occupied[p] = true;
            } else {
                int bestPos = -1;
                int bestDist = Integer.MAX_VALUE;
                for (int j = 0; j < n; j++) {
                    if (!occupied[j]) {
                        int dist = Math.abs(j - p);
                        if (dist < bestDist || (dist == bestDist && j < bestPos)) {
                            bestDist = dist;
                            bestPos = j;
                        }
                    }
                }
                finalPos[i] = bestPos;
                occupied[bestPos] = true;
            }
        }
        return finalPos;
    }

    public static int calcTotalDisp(int[] pref) {
        int[] finalPos = simulate(pref);
        int total = 0;
        for (int i = 0; i < pref.length; i++) {
            total += Math.abs(finalPos[i] - (pref[i] - 1));
        }
        return total;
    }

    public static int[] calcLeftRight(int[] pref) {
        int[] finalPos = simulate(pref);
        int left = 0, right = 0;
        for (int i = 0; i < pref.length; i++) {
            int diff = finalPos[i] - (pref[i] - 1);
            if (diff < 0) left += -diff;
            else if (diff > 0) right += diff;
        }
        return new int[]{left, right};
    }

    public static int[] computeTotalDisp() {
        checkN();
        int totalConfigs = (int) Math.pow(n, n);
        int maxTotalDisp = n * (n - 1) / 2;
        int[] freq = new int[maxTotalDisp + 1];

        for (int config = 0; config < totalConfigs; config++) {
            int[] pref = intoArr(config);
            int totalDisp = calcTotalDisp(pref);
            freq[totalDisp]++;
        }
        return freq;
    }

    public static int computeLeftDisp(int n){
        int[] totals = computeLeftRightTotals(n);
        return totals[0];
    }

    public static int computeRightDisp(int n){
        int[] totals = computeLeftRightTotals(n);
        return totals[1];
    }

    public static int computeNetDisp(int n){
        int[] totals = computeLeftRightTotals(n);
        return totals[1]-totals[0];
    }

    public static int[] computeLeftRightTotals(int n) {
        checkN();
        int totalLeft = 0, totalRight = 0;
        int totalConfigs = (int) Math.pow(n, n);

        for (int config = 0; config < totalConfigs; config++) {
            int[] pref = intoArr(config);
            int[] lr = calcLeftRight(pref);
            totalLeft += lr[0];
            totalRight += lr[1];
        }
        return new int[]{totalLeft, totalRight};
    }

    public static int getDisp(int index, int carNum) {
        checkN();
        checkIndex(index);
        checkCarNum(carNum);

        int[] pref = intoArr(index - 1);
        int[] finalPos = simulate(pref);
        return finalPos[carNum - 1] - (pref[carNum - 1] - 1);
    }

    public static int getLeftDisp(int n) {
        return computeLeftRightTotals(n)[0];
    }

    public static int getRightDisp(int n) {
        return computeLeftRightTotals(n)[1];
    }

    public static int getNetDisp(int n) {
        int[] totals = computeLeftRightTotals(n);
        return totals[0] - totals[1];
    }

    public static void printAllCSPF() {
        checkN();
        int total = (int) Math.pow(n, n);
        for (int i = 0; i < total; i++) {
            int[] pref = intoArr(i);
            int totalDisp = calcTotalDisp(pref);
            System.out.print((i + 1) + ". " + Arrays.toString(pref) + " -> D=" + totalDisp);
            System.out.println();
        }
    }

    public static void printOneCSPF(int index) {
        checkIndex(index);
        int[] pref = intoArr(index - 1);
        System.out.print(index + ". " + Arrays.toString(pref));
        System.out.println();
    }

    public static void printCSPFByDisp(int disp) {
        checkSingleDisp(disp);
        int totalConfigs = (int) Math.pow(n, n);
        for (int idx = 0; idx < totalConfigs; idx++) {
            int[] pref = intoArr(idx);
            int totalDisp = calcTotalDisp(pref);
            if (totalDisp == disp) {
                System.out.println((idx + 1) + ". " + Arrays.toString(pref));
            }
        }
    }

    public static boolean isUniCSPF(int index) {
        for (int k = 1; k <= n; k++) {
            if (Math.abs(getDisp(index, k)) > 1) {
                return false;
            }
        }
        return true;
    }

    public static void printUniCSPF() {
        checkN();
        int total = (int) Math.pow(n, n);
        for (int i = 1; i <= total; i++) {
            if (isUniCSPF(i)) {
                System.out.println(i + ". " + Arrays.toString(intoArr(i - 1)));
            }
        }
    }

    public static int countUniCSPF() {
        checkN();
        int count = 0;
        int total = (int) Math.pow(n, n);
        for (int i = 1; i <= total; i++) {
            if (isUniCSPF(i)) count++;
        }
        return count;
    }

    public static void checkN() {
        if (n <= 0) {
            throw new IllegalStateException("n must be a positive integer.");
        }
    }

    public static void checkIndex(int index) {
        long maxIndex = (long) Math.pow(n, n);
        if (index < 1 || index > maxIndex) {
            throw new IllegalArgumentException("Index must be between 1 and " + maxIndex + ".");
        }
    }

    public static void checkCarNum(int carNum) {
        if (carNum < 1 || carNum > n) {
            throw new IllegalArgumentException("carNum must be between 1 and " + n + ".");
        }
    }

    public static void checkSingleDisp(int disp) {
        if (disp < 0 || disp > n - 1) {
            throw new IllegalArgumentException("disp must be between 0 and " + (n - 1) + ".");
        }
    }


    public static int[] intoArr(int index) {
        int[] arr = new int[n];
        for (int j = 0; j < n; j++) {
            arr[n - 1 - j] = (index / (int) Math.pow(n, j)) % n + 1;
        }
        return arr;
    }
}
