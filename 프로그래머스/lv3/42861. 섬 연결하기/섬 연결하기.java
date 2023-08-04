import java.util.Arrays;
class Solution {
    static int[] parent;
    public int solution(int n, int[][] costs) {
        parent = new int[n];
        int result = 0;
        for(int i=0; i<n; i++)
            parent[i] = i;
        Arrays.sort(costs,(int[] o1, int[] o2) -> o1[2]-o2[2]);
        
        for(int[] x : costs){
            int aParent = getParent(x[0]);
            int bParent = getParent(x[1]);
            if(aParent == bParent) continue;
            
            parent[aParent] = bParent;
            result+=x[2];
        }
        
        return result;
    }
    public int getParent(int x){
        if(parent[x]==x) return x;
        return getParent(parent[x]);
    }   
}
