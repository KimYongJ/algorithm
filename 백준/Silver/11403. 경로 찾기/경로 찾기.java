// https://github.com/KimYongJ/algorithm
import java.util.ArrayList;
import java.util.ArrayDeque;
class Main{

    public static void main(String[] args)throws Exception{
    	ArrayList<ArrayList<Integer>> list = new ArrayList<>(); // 인접 리스트를 담을 리스트
        int N = read();
        int[][] result = new int[N][N];
        
        // 입력된 인접 리스트를 담는 코드
        for(int i=0; i<N; i++){
            ArrayList<Integer> part = new ArrayList<>();
            for(int j=0; j<N; j++){
                int num = read();
                if(num==1) // 방문 가능 노드만 넣는다.
                    part.add(j);
            }
            list.add(part);
        }
        
        // 입력된 인접 리스트를 돌며 방문 가능한지를 체크하는 로직
        // ex) 0에서 0이 가능한지, 0에서 1이 가능한지, 0에서 2가 가능한지 등..
        for(int i=0; i<N; i++)
            for(int j=0; j<N; j++)
                check(i,j,N,result,list);
        
        StringBuilder sb = new StringBuilder();
        for(int i=0; i<N; i++){
            for(int j=0; j<N; j++)
                sb.append(result[i][j]).append(' ');
            sb.append('\n');
        }
        System.out.println(sb);
    }
    public static void check(int start, int end,int N, int[][] result, ArrayList<ArrayList<Integer>> list){   
    	
        ArrayDeque<Integer> q = new ArrayDeque<>();
        boolean[] visit = new boolean[N];
        
        ArrayList<Integer> part = list.get(start); // 인접 리스트를 꺼내온다.
        for(int i=0; i<part.size(); i++)
            q.add(part.get(i));
        
        while(!q.isEmpty()){
            int node = q.poll();
            if(!visit[node]) {
	            result[start][node] = 1;
	            visit[node] = true;
	            if(node==end) // 종료 조건, 큐에서 꺼낸 값이 방문하려하는 노드인지.
	                return;
	            ArrayList<Integer> part1 = list.get(node); // 인접 리스트를 꺼내온다.
	            for(int i=0; i<part1.size(); i++) // 인접 리스트를 다시 큐에 넣습니다.
	                q.add(part1.get(i));
            }
        }
        
    }
    private static int read() throws Exception {
        int c, n = System.in.read() & 15;
        while ((c = System.in.read()) > 32) n = (n << 3) + (n << 1) + (c & 15);
        return n;
    }
}