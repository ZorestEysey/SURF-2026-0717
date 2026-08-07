import java.math.BigInteger;

public class calculateSum {
    public static void main(String[] args) {
        long[] arr = {39916800, 339292800, 1366817760, 3547967280L, 6832956174L,
                10664174901L, 14343954001L, 17369660460L, 19521117840L,
                20783950132L, 21239440165L, 20993564892L, 20159256217L,
                18858736654L, 17221049549L, 15380497452L, 13470662650L,
                11606716696L, 9869488429L, 8301735076L, 6914999429L,
                5702305863L, 4650518570L, 3747424947L, 2982707910L,
                2345568293L, 1822979788L, 1400200611L, 1062315483L,
                795442565L, 587310678L, 427308732L, 306235566L,
                216088775L, 150026072L, 102372463L, 68564094L,
                45013840L, 28935858L, 18189157L, 11160622L,
                6668152L, 3868364L, 2172303L, 1176619L,
                611812L, 303482L, 142509L, 62764L,
                25606L, 9505L, 3131L, 883L, 201L, 33L, 3L};

        BigInteger sum = BigInteger.ZERO;
        for (int i = 0; i < arr.length; i++) {
            sum = sum.add(BigInteger.valueOf(i).multiply(BigInteger.valueOf(arr[i])));
        }
        System.out.println(sum);
    }
}
