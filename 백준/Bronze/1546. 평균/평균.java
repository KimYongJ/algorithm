import java.io.*;
import java.util.*;
class Main{
    public static void main(String[] args)throws Exception{
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        int len = Integer.parseInt(in.readLine());
        StringTokenizer st = new StringTokenizer(in.readLine()," ");
        int max = 0;
        int[] arr = new int[len];
        double result =0;
        
        for(int i=0;i<len;i++){
            arr[i] = Integer.parseInt(st.nextToken());
            if(max<arr[i]) max=arr[i];
        }
        for(int i=0;i<len;i++) result+=(double)arr[i]/max*100;
        
        System.out.println(result/len);
        
        
    }
}