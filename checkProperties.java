import java.util.Arrays;

public class checkProperties {
    public static void main (String[] args){
        long[] arr = {
                3628800, 27820800, 100457280, 233397120, 404070588, 572202448, 706720634, 795256853, 838717290, 843223551, 815655100, 762901636, 691945121, 609781764, 523491582, 439568309, 362637839, 294920443, 236740766, 187411521, 146023777, 111838346, 84203724, 62356355, 45417994, 32506466, 22822564, 15690995, 10552637, 6937684, 4453458, 2784810, 1691089, 994334, 564540, 308300, 160973, 79745, 37158, 16101, 6374, 2248, 682, 168, 30, 3
        };
        isLogConcave(arr);
    }

    public static void isLogConcave(long[] arr){
        boolean isLogConcave = true;
        for (int i = 1; i < arr.length-1; i++){
            if ((long)Math.pow(arr[i], 2)<arr[i-1]*arr[i+1]){
                isLogConcave = false;
            }
        }
        if (arr.length<3){
            System.out.println("The length of array should at least 3.");
        }
        else{
            if (isLogConcave){
                System.out.println(Arrays.toString(arr)+"\nis log concave.");
            }
            else{
                System.out.println(Arrays.toString(arr)+"\nis not log concave.");
            }
        }
    }

    public static void maxAt(long[] arr){

    }
}
