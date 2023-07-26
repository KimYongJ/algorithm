class Solution {
    static int result;
    static boolean[] bool;
    public int solution(int k, int[][] dun) {
        bool = new boolean[dun.length];
        DFS(0,k,dun);
        return result;
    }
    public void DFS(int cnt,int k, int[][] dun){
        for(int i=0; i<dun.length; i++)
            if(k>=dun[i][0] && !bool[i]){
                bool[i] = true;
                DFS(cnt+1,k-dun[i][1],dun);
                bool[i] = false;
            }
        result = Math.max(cnt,result);
    }
}