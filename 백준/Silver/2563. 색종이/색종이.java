import java.io.*;
import java.util.*;
class Main{
    public static boolean[][] s = new boolean[100][100];
    public static void main(String[] args)throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        int len = Integer.parseInt(br.readLine()), cnt = 0;
        for(int l=0; l<len; l++){
            st = new StringTokenizer(br.readLine());
            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());
            for(int i=x;i<x+10;i++)
                for(int j=y; j<y+10;j++)
                    if(!s[i][j]){
                        s[i][j]=true;
                        cnt++;
                    }
        }
        System.out.println(cnt);
    }
}
