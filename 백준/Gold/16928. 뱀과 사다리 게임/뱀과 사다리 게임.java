// https://github.com/KimYongJ/algorithm
import java.util.ArrayDeque;
class Main{
	
    public static void main(String[] args)throws Exception{
    	int N = read();
    	int M = read();
    	int[] transport = new int[101];
    	boolean[] visit = new boolean[101];
    	for(int i=0; i<N+M; i++) {
    		int idx = read();
    		int value = read();
    		transport[idx] = value;
    	}   	
    	ArrayDeque<int[]> q = new ArrayDeque<>();
    	q.add(new int[] {1,0});
    	visit[1] = true;
    	
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
    static int read() throws Exception {
        int c, n = System.in.read() & 15;
        while ((c = System.in.read()) > 32) n = (n << 3) + (n << 1) + (c & 15);
        return n;
    }
}