import java.util.Arrays;

public class CSPF {
    static int n = 4;
    static int index = 6;
    static int carNum = 1;
    static int disp = 7;

    static int[] pref = new int [n];

    static void main (String[] args){

        // printAllCSPF();

        // printOneCSPF(index);

        // System.out.println(getDisp(index, carNum));

        // printUniCSPF();

        // System.out.println(countUniCSPF());

        // System.out.println(Arrays.toString(computeTotalDisp()));

        // printCSPFByDisp(disp);

    }

    public static boolean isUniCSPF(int index) {
        for (int k = 1; k <= n; k++) {
            if (getDisp(index, k) > 1) {
                return false;
            }
        }
        return true;
    }

    public static void checkN(){
        if (n <= 0) {
            throw new IllegalStateException("n should be an positive integer.");
        }
    }

    public static void checkIndex(){
        long maxIndex = (long) Math.pow(n, n);
        if (index < 1 || index > maxIndex) {
            throw new IllegalArgumentException("Your index should between 1 and " + maxIndex + ".");
        }
    }

    public static void checkCarNum(){
        if (carNum < 1 || carNum > n) {
            throw new IllegalArgumentException("Your carNum should between 1 and " + n +".");
        }
    }

    public static void checkSingleDisp(){
        if (disp < 0 || disp > n-1){
            throw new IllegalArgumentException("Your disp should between 0 and " + (n-1) +".");
        }
    }
    public static void printUniCSPF() {
        checkN();
        int total = (int) Math.pow(n, n);
        for (int i = 1; i <= total; i++) {
            if (isUniCSPF(i)) {
                System.out.println(i + ". " + Arrays.toString(intoArr(i-1)));
            }
        }
    }

    public static int countUniCSPF(){
        checkN();
        int totalUnis = 0;
        for (int i = 1; i <= (int) Math.pow(n, n); i++){
            if (isUniCSPF(i)){
                totalUnis++;
            }
        }
        return totalUnis;
    }
    public static void printAllCSPF(){
        checkN();
        for (int i = 0; i < (int) Math.pow(n, n); i++){
            printOneCSPF(i+1);
        }
    }

    public static void printOneCSPF (int index){
        checkIndex();
        System.out.print(index + ". " + Arrays.toString(intoArr(index-1)));
        System.out.println();
    }

    public static int[] intoArr(int index) {
        int[] arr = new int[n];
        for (int j = 0; j < n; j++) {
            arr[n - 1 - j] = (index / (int) Math.pow(n, j)) % n + 1;
        }
        return arr;
    }

    public static int getDisp(int index, int carNum) {
        checkN();
        checkIndex();
        checkCarNum();

        pref = intoArr(index - 1);
        int[] path = new int[pref.length];

        for (int i = 0; i < carNum - 1; i++) {
            int prefer = pref[i] - 1;
            if (path[prefer] == 0) {
                path[prefer] = i + 1;
            } else {
                int left = prefer - 1, right = prefer + 1;
                while (true) {
                    boolean leftOk = left >= 0 && path[left] == 0;
                    boolean rightOk = right < path.length && path[right] == 0;
                    if (leftOk) {
                        path[left] = i + 1;
                        break;
                    } else if (rightOk) {
                        path[right] = i + 1;
                        break;
                    }
                    left--;
                    right++;
                }
            }
        }

        int prefer = pref[carNum - 1] - 1;
        if (path[prefer] == 0) {
            return 0;
        } else {
            int left = prefer - 1, right = prefer + 1;
            while (true) {
                boolean leftOk = left >= 0 && path[left] == 0;
                boolean rightOk = right < path.length && path[right] == 0;
                if (leftOk) {
                    return prefer - left;
                } else if (rightOk) {
                    return right - prefer;
                }
                left--;
                right++;
            }
        }
    }

    public static int[] computeTotalDisp() {
        checkN();

        int totalConfigs = (int) Math.pow(n, n);
        int maxTotalDisp = (n * (n - 1))/2;
        int[] freq = new int[maxTotalDisp + 1];

        for (int config = 0; config < totalConfigs; config++) {
            int[] pref = intoArr(config);
            int totalDisp = calcTotalDispHelper(pref);
            freq[totalDisp]++;
        }
        return freq;
    }

    private static int calcTotalDispHelper(int[] pref) {
        int[] path = new int[n];
        int totalDisp = 0;

        for (int i = 0; i < n; i++) {
            int prefer = pref[i] - 1;
            if (path[prefer] == 0) {
                path[prefer] = i + 1;
            } else {
                int left = prefer - 1, right = prefer + 1;
                int dist = 1;
                while (true) {
                    boolean leftOk = left >= 0 && path[left] == 0;
                    boolean rightOk = right < n && path[right] == 0;
                    if (leftOk) {
                        path[left] = i + 1;
                        totalDisp += dist;
                        break;
                    } else if (rightOk) {
                        path[right] = i + 1;
                        totalDisp += dist;
                        break;
                    }
                    left--;
                    right++;
                    dist++;
                }
            }
        }
        return totalDisp;
    }

    public static void printCSPFByDisp(int disp) {
        checkSingleDisp();

        int totalConfigs = (int) Math.pow(n, n);
        for (int index = 0; index < totalConfigs; index++) {
            int[] pref = intoArr(index);
            int totalDisp = calcTotalDispHelper(pref);
            if (totalDisp == disp) {
                System.out.println((index+1)+". "+Arrays.toString(pref));
            }
        }
    }
}
