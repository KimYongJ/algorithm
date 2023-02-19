import java.io.*;
import java.util.*;

class Main{
    static int[] dx = {-1,1,-2};
    public static void main(String[] args)throws Exception{
        BufferedReader br  = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        boolean[] visit = new boolean[100001];
        int x    = Integer.parseInt(st.nextToken());
        int base = Integer.parseInt(st.nextToken());
        if(x==base){
            System.out.println(0);
            return;
        }else if(base<x){
            System.out.println(x-base);
            return;
        }
        ArrayDeque<int[]> q = new ArrayDeque<>();
        q.add(new int[]{x,0});
        visit[x]=true;
        while(!q.isEmpty()){
            int[] qData = q.poll();
            for(int d : dx){
                int x1 = d==-2 ? qData[0]*2 : qData[0]+d;
                int time = qData[1] + 1;
                if(x1==base){
                    System.out.println(time);
                    return;
                }else if(0<=x1 && x1<100001 && !visit[x1]){
                    visit[x1]=true;
                    q.add(new int[]{x1,time});
                }
            }
        }
    }
}