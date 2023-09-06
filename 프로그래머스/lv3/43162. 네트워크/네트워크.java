class Solution {
    public int solution(int n, int[][] computers) {
        int result = 0;
        boolean[] visit = new boolean[n];
        
        for(int i=0; i<n; i++)
            if(!visit[i]){
                DFS(computers,i,visit);
                result++;
            }
    
        return result;
    }
    public void DFS(int[][] computers, int index , boolean[] visit){
        visit[index] = true;
        for(int i=0; i<computers[index].length; i++){
            if(!visit[i] && computers[index][i]==1)
                DFS(computers,i,visit);
        }
    }
}