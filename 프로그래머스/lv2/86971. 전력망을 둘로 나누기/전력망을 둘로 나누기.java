// https://github.com/KimYongJ/algorithm
class Solution {
    
    private boolean[] visit;
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
            
            visit = new boolean[n+1];   // DFS를 진행할 때 방문 체크할 배열
            
            int cnt = DFS(aNode,1);     // DFS진행시 임의의 노드와 방문한 노드 갯수1을 전달
            
            result = Math.min(result,Math.abs(n-2*cnt));// 최소 노드를 result에 세팅
            
            matrix[aNode][bNode] = 1;   // 해당 간선을 인접행렬에서 다시 추가
            matrix[bNode][aNode] = 1;   // 해당 간선을 인접행렬에서 다시 추가
        }
        return result;
    }
    public int DFS(int start,int cnt){
        visit[start] = true; // start 노드 방문 체크
        for(int i=1; i<n+1; i++){ // 노드 순회
            if(!visit[i] && matrix[start][i]==1){ // 방문하지 않았고, 인접리스트일때(1) DFS진행
                cnt += DFS(i,1);// DFS진행시 node의 갯수를 카운팅해야하기 때문에 반환할 cnt에 결과를 더해주며 인자로 1을 전달한다.
            }
        }
        return cnt;
    }
    
}