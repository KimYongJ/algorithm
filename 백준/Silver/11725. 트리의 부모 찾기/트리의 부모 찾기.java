// https://github.com/KimYongJ/algorithm
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.StringTokenizer;
class Main{

	public static void main(String[] args)throws Exception{
	    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	    StringBuilder sb = new StringBuilder();
	    int N = Integer.parseInt(br.readLine());
	    ArrayList<Integer>[] list = new ArrayList[N+1];
	    int[] parent = new int[N+1];
	    boolean[] visit = new boolean[N+1];
	    
	    for(int i=0; i<N+1; i++) 
	    	list[i] = new ArrayList<Integer>();
	    
	    for(int i=0; i<N-1; i++) {
	    	StringTokenizer st = new StringTokenizer(br.readLine());
	    	int a = Integer.parseInt(st.nextToken());
	    	int b = Integer.parseInt(st.nextToken());
	    	list[a].add(b);
	    	list[b].add(a);
	    }
	    
	    ArrayDeque<Integer> q = new ArrayDeque<>();
	    q.add(1);
	    visit[1] = true;
	    while(!q.isEmpty()) {
	    	int nowNode = q.poll();
	    	for(int i=0; i<list[nowNode].size(); i++) {
	    		int nextNode = list[nowNode].get(i);
	    		if(!visit[nextNode]) {
	    			visit[nextNode] = true;
	    			parent[nextNode] = nowNode;
	    			q.add(nextNode);
	    		}
	    	}
	    }
	    
	    for(int i=2; i<=N; i++) {
	    	sb.append(parent[i]).append('\n');
	    }
	    
		System.out.println(sb);
	}
}