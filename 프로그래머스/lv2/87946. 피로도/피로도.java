class Solution {
    static int result,len;
    static boolean[] bool;
    public int solution(int k, int[][] dun) {
        len = dun.length;
        bool = new boolean[len];
        DFS(0,k,dun);
        return result;
    }
    public void DFS(int cnt,int k, int[][] dun){
        for(int i=0; i<len; i++)
            if(k>=dun[i][0]){
                if(!bool[i]){
                    bool[i] = true;
                    DFS(cnt+1,k-dun[i][1],dun);
                    bool[i] = false;
                }
            }else if(result<cnt){
                result = cnt;
            }
    }
}