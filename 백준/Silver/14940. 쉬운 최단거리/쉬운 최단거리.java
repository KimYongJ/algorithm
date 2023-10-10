// https://github.com/KimYongJ/algorithm
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.StringTokenizer;
class Main{
	
    static int result[][], x,y,start[]= new int[2];
    static int[][] dxy = {{0,1},{0,-1},{1,0},{-1,0}};
    
    public static void main(String[] args)throws Exception{
    	BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    	StringTokenizer st = new StringTokenizer(br.readLine());
        y = Integer.parseInt(st.nextToken());
        x = Integer.parseInt(st.nextToken());
        result = new int[y][x];
        for(int i=0; i<y; i++) {
        	st = new StringTokenizer(br.readLine());
        	for(int j=0; j<x; j++) {
        		result[i][j] = Integer.parseInt(st.nextToken());
                if(result[i][j]==2) {
                    start[0] = i;
                    start[1] = j;
                    result[i][j]=0;
                }else if(result[i][j]!=0){
                    result[i][j] = -1;
                }
        	}
        }
        
        BFS();
        
        StringBuilder sb = new StringBuilder();
        for(int[] i : result){
            for(int j : i)
                sb.append(j).append(" ");
            sb.append("\n");
        }
        System.out.println(sb);
    }
    public static void BFS(){
        ArrayDeque<int[]> q = new ArrayDeque<>();
        q.add(new int[]{start[0],start[1],0});
        while(!q.isEmpty()){
            int[] position = q.poll();
            
            for(int[] xy : dxy){
                int y1 = position[0]+xy[0];
                int x1 = position[1]+xy[1];
                int dist = position[2]+1;
                
                if(y1<0 || x1<0 || y1>=y ||x1>=x || result[y1][x1] != -1){
                    continue;
                }
                
                q.add(new int[]{y1,x1,dist});
                result[y1][x1] = dist;
                
            }
        }
    }
}
