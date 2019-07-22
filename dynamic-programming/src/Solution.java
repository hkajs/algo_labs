import java.io.*;
import java.util.*;

public class Solution {
    
    public static void printMatrix(int[][] scores){
        for (int i = 0; i < scores.length; i++){
			for (int j = 0; j < scores[0].length; j++){
				System.out.print(scores[i][j] + " ");
			}
			System.out.println();
		}
    }
     
    public static int minimum(int i, int j){
        if (i <= j){
            return i;
        }else{
            return j;
        }
    }
    
    public static int maximum(int x, int y, int z){
        if (x >= y && x >= z){
            return x;
        }else if (y >= z){
            return y;
        }else{
            return z;
        }
    }
    
    public static void trace(String seq1, String seq2, int[][] scores){
        String retseq1 = "";
        String retseq2 = "";
        int i = seq1.length(); 
        int j = seq2.length();
        int score = 0;
        
        /*
   
   while (1) {
    last if $matrix[$i][$j]{pointer} eq "none"; # ends at first cell of matrix

    if ($matrix[$i][$j]{pointer} eq "diagonal") {
        $align1 .= substr($seq1, $j-1, 1);
        $align2 .= substr($seq2, $i-1, 1);
        $i--;
        $j--;
    }
    elsif ($matrix[$i][$j]{pointer} eq "left") {
        $align1 .= substr($seq1, $j-1, 1);
        $align2 .= "-";
        $j--;
    }
    elsif ($matrix[$i][$j]{pointer} eq "up") {
        $align1 .= "-";
        $align2 .= substr($seq2, $i-1, 1);
        $i--;
    }    
}

        
        */
        /*
        while(i > 0 || j > 0) {
            if (i > 0 && j > 0 && (scores[i][j] == scores[i-1][j-1]+2 || scores[i][j] == scores[i-1][j-1]-1)){
                i -= 1;
                j -= 1;
                retseq1 += seq1.charAt(i);
                retseq2 += seq2.charAt(j);
                //score += 2;
            }else if (j > 0 && scores[i][j] == scores[i][j-1]-1){ //column
                retseq1 += "_";
                j -= 1;
                retseq2 += seq2.charAt(j);
                //score -= 1;

            }else if (i > 0 && scores[i][j] == scores[i-1][j]-1){ //row
                retseq2 += "_";
                i -= 1;
                retseq1 += seq1.charAt(i);
                //score -= 1;

            }
        }
        */
        
        while(i > 0 || j > 0) {
            if (j > 0 && scores[i][j] == scores[i][j-1]-1){ //column
                retseq1 += "_";
                j -= 1;
                retseq2 += seq2.charAt(j);
                //score -= 1;

            }else if (i > 0 && scores[i][j] == scores[i-1][j]-1){ //row
                retseq2 += "_";
                i -= 1;
                retseq1 += seq1.charAt(i);
                //score -= 1;

            }else if (i > 0 && j > 0 && (scores[i][j] == scores[i-1][j-1]+2 || scores[i][j] == scores[i-1][j-1]-1)){
                i -= 1;
                j -= 1;
                retseq1 += seq1.charAt(i);
                retseq2 += seq2.charAt(j);
                //score += 2;
            }
        }
        
        
        //System.out.println(score);
        
        for (int x = retseq1.length()-1; x > -1; x--){
            System.out.print(retseq1.charAt(x));
        }
        
        System.out.println();
        
        for (int x = retseq2.length()-1; x > -1; x--){
            System.out.print(retseq2.charAt(x));
        }
    }

    public static void main(String[] args) throws Exception{
        /* Enter your code here. Read input from STDIN. Print output to STDOUT. Your class should be named Solution. */
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String seq1 = br.readLine();
        String seq2 = br.readLine();
        int[][] scores = new int[seq1.length()+1][seq2.length()+1];
        for (int i = 0; i <= seq1.length(); i++)
            scores[i][0] = i * -1;
        for (int i = 0; i <= seq2.length(); i++)
            scores[0][i] = i * -1;
        for (int i = 1; i <= seq1.length(); i++){
            for (int j = 1; j <= seq2.length(); j++){
                int score = -1;
                if (seq1.charAt(i-1) == seq2.charAt(j-1)){
                    //scores[i][j] = scores[i-1][j-1];
                    score = 2;
                }
                //}else{
                    scores[i][j] = Math.max(Math.max(scores[i-1][j-1]+score,scores[i-1][j]-1), scores[i][j-1]-1);
                    //scores[i][j] = minimum(scores[i-1][j], scores[i][j-1])+1;
                //}
            }
        }
        System.out.println(scores[seq1.length()][seq2.length()]);
        trace(seq1,seq2,scores);
    }
}