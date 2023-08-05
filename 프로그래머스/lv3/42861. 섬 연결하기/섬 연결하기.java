import java.util.Arrays;
class Solution {
    static int result;
    static int[]parent;
    public int solution(int n, int[][] c) {
        Arrays.sort(c,(a,b)->a[2]-b[2]);
        parent = new int[n];
        for(int i=0; i<n; i++) parent[i] = i; // 자기자신으로 초기화
        
        for(int[] x : c){
            int aParent = getParent(x[0]);
            int bParent = getParent(x[1]);
            
            if(aParent != bParent){
                result += x[2];
                parent[aParent] = bParent;
            }
        }
        return result;
    }
    public int getParent(int x){
        if(parent[x]==x) return x;
        return getParent(parent[x]);
    }
    
}