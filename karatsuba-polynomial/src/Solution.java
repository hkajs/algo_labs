import java.io.*;
import java.util.*;
import java.text.*;
import java.math.*;
import java.util.regex.*;

public class Solution {

    public static int[] karatsuba(int[] p1, int[] p2){
        int n = p1.length;
        int[] out = new int[(2*n)-1];

        if (n == 2){
            out[0] = p1[0] * p2[0];
            out[1] = (p1[0] * p2[1]) + (p2[0] * p1[1]);
            out[2] = p1[1] * p2[1];
            return out;
        }
        
        int[] low1 = Arrays.copyOfRange(p1,0,n/2);
        int[] high1 = Arrays.copyOfRange(p1,n/2,n);
        
        int[] low2 = Arrays.copyOfRange(p2,0,n/2);
        int[] high2 = Arrays.copyOfRange(p2,n/2,n);

        int[] k2 = new int[n/2];
        int[] k3 = new int[n/2];
        
        for (int x = 0; x < k2.length; x++){
            k2[x] = low1[x] + high1[x]; //p1 middle
            k3[x] = low2[x] + high2[x]; //p2 middle
        }
        
        int[] k0 = karatsuba(low1, low2); //correct
        int[] k1 = karatsuba(high1, high2); //correct
        int[] k2_k3 = karatsuba(k2,k3);

        for (int x = 0; x < k1.length; x++){
            out[x] = k0[x];
            out[x+n] = k1[x];
        }
        for (int x = 0; x < k2_k3.length;x++){
            out[x+n/2] += k2_k3[x] - k0[x] - k1[x];
        }
        return out;
        
    }
    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int d = Integer.parseInt(br.readLine());
        String p1 = br.readLine();
        String p2 = br.readLine();
        String[] holder1 = p1.split(" ");
        String[] holder2 = p2.split(" ");
        int[] coef_p1 = new int[d+1];
        int[] coef_p2 = new int[d+1];
        for (int x = 0; x < d+1; x++){
            coef_p1[x] = Integer.parseInt(holder1[x]);
            coef_p2[x] = Integer.parseInt(holder2[x]);
        }
        int[] test = karatsuba(coef_p1,coef_p2);
        for (int x = 0; x < test.length; x++){
            System.out.print(test[x] + " ");
        }
    }
}
