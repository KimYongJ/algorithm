// https://github.com/KimYongJ/algorithm
import java.util.ArrayDeque;

class Person{
    int node, cnt;
    Person(int node, int cnt){
        this.node = node;
        this.cnt = cnt;
    }
}
class Main{
    
    static int resultNode=9999, sum=9999;
    
    private static int read() throws Exception {
        int c, n = System.in.read() & 15;
        while ((c = System.in.read()) > 32) n = (n << 3) + (n << 1) + (c & 15);
        return n;
    }
    public static void BFS(int baseNode, int N, int[][] arr){
        ArrayDeque<int[]> q = new ArrayDeque<>(){{ add(new int[] {baseNode,1}); }};
        boolean[] visit = new boolean[N+1];
        visit[baseNode] = true;
        int kevin = 0; // 케빈 베이컨 수 
        while(!q.isEmpty()){
            int[] node = q.poll();
            for(int i=1; i<N+1; i++){
                if(!visit[i] && arr[node[0]][i]==1){
                    visit[i] = true;
                    q.add(new int[] {i,node[1]+1});
                    kevin += node[1];
                }
            }
        }
        if(sum>kevin){
            resultNode = baseNode;
            sum = kevin;
        }else if(sum==kevin){
            resultNode = Math.min(resultNode, baseNode);
        }
    }
    
    public static void main(String[] args)throws Exception{
        int N = read();
        int M = read();
        
        int[][] arr = new int[N+1][N+1];
        
        for(int i=0; i<M; i++){
            int Anode = read();
            int Bnode = read();
            arr[Anode][Bnode] = 1;
            arr[Bnode][Anode] = 1;
        }
        
        for(int i=1; i<N+1; i++)
            BFS(i,N,arr);
        

        System.out.print(resultNode);
    }
}
