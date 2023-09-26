// https://github.com/KimYongJ/algorithm
import java.util.ArrayDeque;
class Solution {
    
    private int result = Integer.MAX_VALUE;
    private int[][] matrix;
    private int n;
    
    public int solution(int n, int[][] wires) {
        this.n = n;
        matrix = new int[n+1][n+1];
        
        for(int[] w: wires){
            matrix[w[0]][w[1]] = 1; // 양방향 인접리스트 생성 
            matrix[w[1]][w[0]] = 1; // 양방향 인접리스트 생성
        }
        
        for(int i=0; i<wires.length; i++){
            int aNode = wires[i][0];    // 제외할 노드
            int bNode = wires[i][1];    // 제외할 노드
            
            matrix[aNode][bNode] = 0;   // 해당 간선을 인접행렬에서 제거
            matrix[bNode][aNode] = 0;   // 해당 간선을 인접행렬에서 제거
            
            result = BFS(aNode);        // 임의의점을 전달하며 BFS 실행
            
            matrix[aNode][bNode] = 1;   // 해당 간선을 인접행렬에서 다시 추가
            matrix[bNode][aNode] = 1;   // 해당 간선을 인접행렬에서 다시 추가
        }
        return result;
    }
    public int BFS(int start){
        ArrayDeque<Integer> q = new ArrayDeque<>(); // BFS 진행할 큐
        boolean[] visit = new boolean[n+1]; // 방문 체크할 배열
        int cnt = 1; // start노드를 방문했기 때문에 초기 값은 1 이다.
        
        q.add(start); // start노드를 큐에 넣어준다.
        while(!q.isEmpty()){ // 큐가 빌때까지 반복
            int node = q.poll(); // 큐의 노드를 꺼낸다.
            visit[node] = true;  // 꺼낸 노드 방문 처리
            for(int i=1; i< n+1; i++){ // 노드의 갯수를 순회한다.
                if(!visit[i] && matrix[node][i]==1){// 노드를 방문하지 않았고, 연결된 노드라면 q에 넣고 방문노드 1추가(cnt+1)
                    q.add(i);
                    cnt++;
                }
            }

        }
        return Math.min(result,Math.abs(n-2*cnt));// 네트워크가 2개일 경우는 방문 노드의 차가 가장 작을 경우이기 때문에 해당 코드로도 네트워크가 2개인 경우를 걸러낼 수 있다.
    }
    
}