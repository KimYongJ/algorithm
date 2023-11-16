// https://github.com/KimYongJ/algorithm
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.StringTokenizer;
class Main{
	
	static int dist=Integer.MAX_VALUE,transport[];
	static boolean[] visit;
    public static void main(String[] args)throws Exception{
    	BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    	StringTokenizer st = new StringTokenizer(br.readLine());
    	int N = Integer.parseInt(st.nextToken());
    	int M = Integer.parseInt(st.nextToken());
    	transport = new int[101];
    	visit = new boolean[101];
    	for(int i=0; i<N+M; i++) {
    		st =  new StringTokenizer(br.readLine());
    		int idx = Integer.parseInt(st.nextToken());
    		int value = Integer.parseInt(st.nextToken());
    		transport[idx] = value;
    	}   	
    	BFS(1,0); // 순서 : 시작인덱스, 한번에이동한 칸의 수  
    }
    public static void BFS(int startIndex, int dist) {
    	ArrayDeque<int[]> q = new ArrayDeque<>();
    	q.add(new int[] {startIndex,dist});
    	visit[startIndex] = true;
    	
    	while(!q.isEmpty()) {
    		int[] node = q.poll();
    		for(int i=1; i<=6; i++) {
    			int newIndex = node[0]+i;
    			int newDist = node[1]+1;
    			
    			if(newIndex == 100){ // 종료 조건
    				System.out.println(newDist);
    				return;
    			}else if(newIndex>100 || visit[newIndex]) continue;
    			
    			if(transport[newIndex] != 0) {
    				newIndex = transport[newIndex];
    			}
    			
    			visit[newIndex] = true;
    			
    			q.add(new int[] {newIndex,newDist});
    		}
    		
    	}
    }
}