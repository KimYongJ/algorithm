import java.io.*;
import java.util.*;

class Main{
    public static void main(String[] args)throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st1 = new StringTokenizer(br.readLine(),":");
        StringTokenizer st2 = new StringTokenizer(br.readLine(),":");
        int[] a = new int[3];
        int[] b = new int[3];
        for(int i=0; i<3; i++){
            a[i] = Integer.parseInt(st1.nextToken());
            b[i] = Integer.parseInt(st2.nextToken());
        }
        int time1 = a[0] * 3600 + a[1]* 60 + a[2];
        int time2 = b[0] * 3600 + b[1]* 60 + b[2];
        
        if(time1>time2) time2 += 24*3600;
        
        int result = time2-time1;
        int hour = result/3600;
        int min = (result%3600)/60;
        int second = result %60;
        
        if(hour==0 && min == 0 && second == 0){
            System.out.println("24:00:00");
        }else{
        sb.append(hour<10 ? "0"+hour : hour).append(":")
          .append(min<10 ? "0"+min : min).append(":")
          .append(second<10 ? "0"+second : second);
        System.out.println(sb);
        }
        
    }
}
