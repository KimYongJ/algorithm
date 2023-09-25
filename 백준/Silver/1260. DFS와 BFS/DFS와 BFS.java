// https://github.com/KimYongJ/algorithm
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Collections;
class Main{
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    private static StringBuilder sb = new StringBuilder();
    private static int N,M,start;
    private static ArrayList<ArrayList<Integer>> List = new ArrayList<>();
    private static boolean[] visit; // DFS와 BFS 진행시 방문 체크할 배열
   
    public static void main(String[] args)throws Exception{
        N = readInt();
        M = readInt();
        start = readInt();
        visit= new boolean[N+1];
        
        for(int i=0; i<=N; i++)
            List.add(new ArrayList<>()); // 노드 갯수만큼 리스트 생성
        
        for(int i=0; i<M; i++){
            int aNode = readInt();
            int bNode = readInt();
            
            List.get(aNode).add(bNode); // 양 방향 셋팅
            List.get(bNode).add(aNode); // 양 방향 셋팅
        }
        for(ArrayList<Integer> x : List){ // BFS진행시 오름차순 방문을 해야 하기 때문에 정렬
        	Collections.sort(x);
        }
        
        
        visit[start]= true;
        DFS(start);
        sb.append("\n");
        BFS();
        
        System.out.println(sb.toString());
    }
    public static void DFS(int start) {
    	if(visit[start])
    		sb.append(start).append(" ");
 
    	ArrayList<Integer> list = List.get(start); // DFS진행할 리스트를 꺼낸다.
    	
    	for(int i=0; i<list.size(); i++){
    		int node = list.get(i);
    		if(!visit[node]) {
    			visit[node] = true; // 방문 체크
    			DFS(node);
    		}
    	}
    }
    public static void BFS(){
        
        ArrayDeque<Integer> q = new ArrayDeque<>(); // BFS진행할 큐 선언
        visit = new boolean[N+1]; // BFS진행시 방문 체크할 배열 초기화
        q.add(start); // 초기 값 셋팅
        visit[start] = true; // 초기 값 방문 셋팅
        sb.append(start).append(" "); // 초기 값 문자 추가
        while(!q.isEmpty()){
            int baseNode = q.poll();
            ArrayList<Integer> list = List.get(baseNode);
            for(int i=0; i<list.size(); i++){
                int node = list.get(i);
                if(!visit[node]){
                    visit[node] = true;
                    sb.append(node).append(" ");
                    q.add(node);
                }
            }
        }
    }
    static int readInt() throws Exception {
        int sum = 0;
        boolean isNegative = false;
        while(true){
            int input = br.read();
            if(input=='\n' || input==' ' || input ==',' || input==']')
                return isNegative ? sum*-1 : sum;
            else if(input=='-')
                isNegative = true;
            else
                sum = (sum*10)+input-'0';
        }
    }
}
