import java.io.*;
import java.util.*;

class Main{
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    public static void main(String[] args)throws Exception{
        ArrayDeque<int[]> j_q = new ArrayDeque<>();
        ArrayDeque<int[]> f_q = new ArrayDeque<>();
        StringTokenizer st = new StringTokenizer(br.readLine());
        int[][] dxy = {{0,1},{0,-1},{1,0},{-1,0}};
        int x = Integer.parseInt(st.nextToken());
        int y = Integer.parseInt(st.nextToken());
        char[][]arr = new char[x][y];
        for(int i=0; i<x;i++)
            for(int j=0; j<y; j++){
                arr[i][j] = read();
                if(arr[i][j]=='J'){
                    j_q.add(new int[]{i,j,0});
                }else if(arr[i][j]=='F'){
                    f_q.add(new int[]{i,j});
                }
            }
        
        while(!j_q.isEmpty()){
            int fire_q_len = f_q.size();
            for(int i=0; i<fire_q_len; i++){
                int[] f_qData = f_q.poll();
                for(int[] xy : dxy){
                    int x1 = f_qData[0] + xy[0];
                    int y1 = f_qData[1] + xy[1];
                
                    if(x1>=0 && y1>=0 && x1<x && y1<y &&
                      arr[x1][y1]!='F' && arr[x1][y1]!='#'){
                        arr[x1][y1]='F';
                        f_q.add(new int[]{x1,y1});
                    }
                }
            }
            int jihun_q_len = j_q.size();
            for(int i=0; i<jihun_q_len; i++){
                int[] j_qData = j_q.poll();
                for(int[] xy : dxy){
                    int x1 = j_qData[0] + xy[0];
                    int y1 = j_qData[1] + xy[1];
                    int dist = j_qData[2] + 1;
                    if(x1<0 || y1<0 || x1>=x || y1>=y){
                        System.out.print(dist);
                        return;
                    }
                    if(arr[x1][y1]=='.'){
                        arr[x1][y1]='J';
                        j_q.add(new int[]{x1,y1,dist});
                    }
                }
            }
        }
        System.out.println("IMPOSSIBLE");
    }
    public static char read()throws Exception{
        while(true){
            int num = br.read();
            if(num!='\n')
                return (char)num;
        }
    }
}