import java.io.*;
import java.util.*;
class Main{
    public static void main(String[] args)throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int x = Integer.parseInt(st.nextToken());
        int y = Integer.parseInt(st.nextToken());
        int day = y;
        int[] cnt = {31,28,31,30,31,30,31,31,30,31,30};
        String[] str = {"SUN","MON","TUE","WED","THU","FRI","SAT"};
       
        for(int i=1; i<x; i++)
            day += cnt[i-1];
        
        System.out.println(str[day%7]);
    }
}