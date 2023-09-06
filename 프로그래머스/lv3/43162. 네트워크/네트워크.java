class Solution {
    private int result = 0;
    private boolean[] visit;
    
    public int solution(int n, int[][] computers) {
        visit = new boolean[n];
        
        for(int i=0; i<n; i++){
            if(!visit[i]){ // 노드를 방문하지 않았다면 이하 실행 
                result++;
                DFS(computers,i,n);
            }
        }
        return result;
    }
    public void DFS(int[][] computers, int index,int n){
        visit[index] = true; // 노드 방문처리 
        for(int i=0; i<n; i++) 
            if(!visit[i] && computers[index][i]==1){// 방문한 노드와 연결되어있는 것 체크하여 DFS처리
                DFS(computers,i,n);
            }
        
    }
}