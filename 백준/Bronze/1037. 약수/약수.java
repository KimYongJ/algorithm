import java.io.*;
import java.util.*;

class Main{
    public static void main(String[] args)throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int l = Integer.parseInt(st.nextToken());
        int[] arr = new int[l];
        st = new StringTokenizer(br.readLine());
        for(int i=0; i<l; i++){
            arr[i] = Integer.parseInt(st.nextToken());
        }
        Arrays.sort(arr);
        if(l==1) System.out.println(arr[0]*arr[0]);
        else System.out.println(arr[0]* arr[l-1]);
        
    }
}