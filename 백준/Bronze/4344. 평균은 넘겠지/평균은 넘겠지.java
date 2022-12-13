import java.util.*;
import java.io.*;
class Main{
    public static void main(String[] args)throws Exception{
        BufferedReader in= new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        int len = Integer.parseInt(in.readLine());
        for(int i=0;i<len;i++){
            StringTokenizer st = new StringTokenizer(in.readLine()," ");
            int number = Integer.parseInt(st.nextToken());
            int[] arr = new int[number];
            int avg =0;
            for(int j=0;j<number;j++){
                arr[j]=Integer.parseInt(st.nextToken());
                avg+=arr[j];
            }
            avg/=number;
            int cnt=0;
            for(int j=0;j<number;j++){
                if(arr[j]>avg)cnt++;
            }
            float result =(float)cnt*100/number;
            sb.append(String.format("%.3f",result)).append("%").append("\n");
           
        }
        System.out.println(sb);
    }
}