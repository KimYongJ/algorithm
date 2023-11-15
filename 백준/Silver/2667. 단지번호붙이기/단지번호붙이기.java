// https://github.com/KimYongJ/algorithm

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Collections;

class Main{
    
    static int cnt,N;
    static char[][] arr;
    static int[] dx = {0,0,1,-1};
    static int[] dy = {1,-1,0,0};
    static ArrayDeque<Node> q = new ArrayDeque<>();
    static ArrayList<Integer> list = new ArrayList<>();
    
    public static void main(String[] args)throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        arr = new char[N][N];
        
        for(int i=0; i<N; i++)
            arr[i] = br.readLine().toCharArray();

        for(int i=0; i<N; i++){
            for(int j=0; j<N; j++){
                if(arr[i][j] != '0'){
                    cnt++;
                    q.add(new Node(i,j,arr[i][j]));
                    arr[i][j] = '0';
                    list.add( BFS() );
                }
            }
        }
        
        StringBuilder sb = new StringBuilder();
        sb.append(cnt).append('\n');
        Collections.sort(list);
        for(int i=0; i< list.size() ; i++)
            sb.append( list.get(i) ).append('\n');
        
        System.out.print(sb);
    }

    public static int BFS(){
        int area = 1;
        while(!q.isEmpty()){
            Node node = q.poll();
            for(int i=0; i<4; i++){
                int x1 = node.x + dx[i];
                int y1 = node.y + dy[i];
                
                if(x1<0 || y1<0 || x1>=N || y1>=N || arr[x1][y1] != node.c)
                    continue;
                
                arr[x1][y1] = '0';
                q.add(new Node(x1,y1,node.c));
                area++;
            }
        }
        return area;
    }

}
class Node{
    int x,y;
    char c;
    Node(int x,int y, char c){
        this.c = c;
        this.x = x;
        this.y = y;
    }
}