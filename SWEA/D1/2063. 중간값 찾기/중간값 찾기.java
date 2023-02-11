import java.io.*;
import java.util.*;

class Solution{
    public static void main(String[] args)throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int l = Integer.parseInt(br.readLine());
        int[] arr = new int[l];
        StringTokenizer st = new StringTokenizer(br.readLine());
        for(int i=0; i<l; i++)
            arr[i] = Integer.parseInt(st.nextToken());
        
        Arrays.sort(arr);
        
        System.out.println(arr[l/2]);
    }
}