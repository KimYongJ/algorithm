import java.io.*;
import java.util.*;
class Main{
    public static void main(String[] args)throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        int len = Integer.parseInt(br.readLine());
        boolean[][] s = new boolean[100][100];
        for(int l=0; l<len; l++){
            st = new StringTokenizer(br.readLine());
            s = check(st.nextToken(),st.nextToken(),s);
        }
        System.out.println(count(s));
    }
    public static boolean[][] check(String a,String b,boolean[][] s){
        int x = Integer.parseInt(a);
        int y = Integer.parseInt(b);
        for(int i=x;i<x+10;i++)
            for(int j=y; j<y+10;j++)
                s[i][j]=true;
        return s;
    }
    public static int count(boolean[][] s){
        int cnt = 0; 
        for(int i=0;i<100;i++)
            for(int j=0; j<100;j++)
                if(s[i][j]) cnt++;
        return cnt;
    }
}