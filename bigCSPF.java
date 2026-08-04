import java.math.BigInteger;
import java.util.Arrays;

public class bigCSPF {
    static long[] account;

    public static void main(String[] args) {
        int n = 12; // 在这修改n值
        int maxDisp = (n * (n - 1)) / 2;
        account = new long[maxDisp + 1];
        long start = System.currentTimeMillis();
        getPrefer(n);
        long elapsed = System.currentTimeMillis() - start;

        System.out.println("n = " + n);
        System.out.println("t_m array (m = 0 to " + maxDisp + "):");
        System.out.println(Arrays.toString(account));

        // 校验：所有 t_m 之和应等于 n^n  
        long sum = 0;
        for (long v : account) sum += v;
        BigInteger expected = BigInteger.valueOf(n).pow(n);
        System.out.println("Sum check: " + sum + " (expected " + expected + ")");
        System.out.println("Time: " + elapsed / 1000.0 + " s");
    }

    public static void getPrefer(int n) {
        BigInteger total = BigInteger.valueOf(n).pow(n);
        int[] pref = new int[n];

        // 如果 n^n 能放进 long，走快速路径  
        if (total.compareTo(BigInteger.valueOf(Long.MAX_VALUE)) <= 0) {
            long totalLong = total.longValue();
            long reportInterval = totalLong / 100;
            for (long i = 0; i < totalLong; i++) {
                // 每 1% 汇报一次进度  
                if (i > 0 && i % reportInterval == 0) {
                    System.err.printf("Progress: %.0f%% (%d / %d)\n",
                            100.0 * i / totalLong, i, totalLong);
                }
                long temp = i;
                for (int j = 0; j < n; j++) {
                    pref[j] = (int) (temp % n) + 1;
                    temp /= n;
                }
                simulate(pref, n);
            }
        } else {
            // 超大整数回退路径（慢，但正确）  
            System.err.println("n^n exceeds Long.MAX_VALUE; using BigInteger loop (will be slow)");
            BigInteger reportInterval = total.divide(BigInteger.valueOf(100));
            for (BigInteger i = BigInteger.ZERO; i.compareTo(total) < 0; i = i.add(BigInteger.ONE)) {
                if (i.compareTo(BigInteger.ZERO) > 0
                        && i.mod(reportInterval).equals(BigInteger.ZERO)) {
                    System.err.printf("Progress: ~%s%%\n",
                            i.multiply(BigInteger.valueOf(100)).divide(total));
                }
                BigInteger temp = i;
                for (int j = 0; j < n; j++) {
                    pref[j] = temp.mod(BigInteger.valueOf(n)).intValue() + 1;
                    temp = temp.divide(BigInteger.valueOf(n));
                }
                simulate(pref, n);
            }
        }
    }

    /**
     * 模拟 CSPF 停车过程。     * 规则：车按顺序进入，若偏好车位已空则直接停入；     * 否则双向搜索最近的空车位，等距时选择离入口更近的（左侧）。     */    public static void simulate(int[] pref, int n) {
        int[] path = new int[n];  // 0 = 空，>0 = 第几辆车占用  
        int totalDist = 0;
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
                    if (leftOk && rightOk) {
                        // 等距：选离入口更近的（左侧）  
                        path[left] = i + 1;
                        totalDist += dist;
                        break;
                    } else if (leftOk) {
                        path[left] = i + 1;
                        totalDist += dist;
                        break;
                    } else if (rightOk) {
                        path[right] = i + 1;
                        totalDist += dist;
                        break;
                    }
                    left--;
                    right++;
                    dist++;
                }
            }
        }
        account[totalDist]++;
    }
}
