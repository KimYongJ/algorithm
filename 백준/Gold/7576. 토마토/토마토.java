import java.io.*;
import java.util.*;

class Main{
    static BufferedReader br =  new BufferedReader(new InputStreamReader(System.in));
    static ArrayDeque<int[]> q = new ArrayDeque<>();
    static int[][] dxy = {{0,1},{0,-1},{1,0},{-1,0}};
    static char[][] arr;
    static int x,y , result = 0 , noneTomato = 0;

    public static void main(String[] args)throws Exception{
        StringTokenizer st = new StringTokenizer(br.readLine());
        y = Integer.parseInt(st.nextToken());
        x = Integer.parseInt(st.nextToken());
        arr = new char[x][y];
        for(int i=0; i<x; i++){
            for(int j=0; j<y; j++){
                arr[i][j] = read();
                if(arr[i][j]=='1'){
                    q.add(new int[]{i,j,0});
                }
            }
        }
        if(result==x*y){
            System.out.println(0);
            return;
        }
        while(!q.isEmpty()){
            int[] qData = q.poll();
            for(int[] d : dxy){
                int x1 = d[0]+qData[0];
                int y1 = d[1]+qData[1];
                int dist = qData[2]+1;
                
                if(y1<0 || x1<0 || y1>=y || x1>=x ||
                   arr[x1][y1]=='1' || arr[x1][y1]=='-')
                    continue;
                arr[x1][y1]='1';
                noneTomato--;
                q.add(new int[]{x1,y1,dist});
                
            }
            result = qData[2];
        }
        System.out.println(noneTomato==0 ? result : -1);
    }
    private static char read() throws IOException {
		while(true) {
			int now = br.read();
            if(now=='-'){
                br.read();
                return (char)now;
            }else if(now=='0'){
                noneTomato++;
                return (char)now;
            }else if(now=='1'){
                result++;
                return (char)now;
            }
		}
	}
}