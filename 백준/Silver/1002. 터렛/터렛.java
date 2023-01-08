import java.io.*;
import java.util.*;

class Main{
    public static void main(String[] args)throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st = new StringTokenizer(br.readLine());
        int l = Integer.parseInt(st.nextToken());
        for(int i=0; i<l; i++){
            st = new StringTokenizer(br.readLine());
            int x1 = Integer.parseInt(st.nextToken());
            int y1 = Integer.parseInt(st.nextToken());
            int r1 = Integer.parseInt(st.nextToken());
            
            int x2 = Integer.parseInt(st.nextToken());
            int y2 = Integer.parseInt(st.nextToken());
            int r2 = Integer.parseInt(st.nextToken());
            
            sb.append(find(x1,y1,r1,x2,y2,r2)).append("\n");
        }
        System.out.println(sb);
    }
    public static int find(int x1,int y1,int r1, int x2, int y2, int r2){
        int d = (int)(Math.pow(x1-x2,2)+Math.pow(y1-y2,2));
        int cnt = 2;
        if(x1==x2 && y1==y2 && r1==r2) cnt = -1;
        else if(d>Math.pow(r1+r2,2) || d<Math.pow(r1-r2,2)) cnt = 0;
        else if(d==Math.pow(r1+r2,2) || d==Math.pow(r1-r2,2)) cnt = 1;
        return cnt;
    }
}