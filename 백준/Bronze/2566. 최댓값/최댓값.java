import java.io.*;
import java.util.*;

class Main{
    public static void main(String[] args)throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        int max=0, n, r=0, h=0;
        for(int i=0; i<9; i++){
            st = new StringTokenizer(br.readLine());
            for(int j=0; j<9; j++){
                n = Integer.parseInt(st.nextToken());
                if(max<n){
                    max = n;
                    r = i;
                    h = j;
                }
            }
        }
        System.out.println(max);
        System.out.println((r+1)+" "+(h+1));  
    }
}