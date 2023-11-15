// https://github.com/KimYongJ/algorithm
import java.util.ArrayList;
import java.util.ArrayDeque;
import java.util.Collections;
import java.util.StringTokenizer;
import java.io.BufferedReader;
import java.io.InputStreamReader;

class Person{
    int node, cnt;
    Person(int node, int cnt){
        this.node = node;
        this.cnt = cnt;
    }
}
class Main{
    public static void BFS(int baseNode, int N, ArrayList<Person>list, int[][] arr){
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
        list.add( new Person(baseNode,kevin) );
    }
    
    public static void main(String[] args)throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        ArrayList<Person> list = new ArrayList<>();
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        
        int[][] arr = new int[N+1][N+1];
        
        for(int i=0; i<M; i++){
            st = new StringTokenizer(br.readLine());
            int Anode = Integer.parseInt(st.nextToken());
            int Bnode = Integer.parseInt(st.nextToken());
            arr[Anode][Bnode] = 1;
            arr[Bnode][Anode] = 1;
        }
        
        for(int i=1; i<N+1; i++)
            BFS(i,N,list,arr);
        
        Collections.sort(list,(a,b)->{
           if(a.cnt==b.cnt){
               return a.node - b.node;
           } 
           return a.cnt-b.cnt;
        });
        System.out.print(list.get(0).node);
    }
}
