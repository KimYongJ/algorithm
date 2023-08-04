import java.util.Arrays;
import java.util.Comparator;
class Solution {
    static int [] parent;
    static int result,max;
    public int solution(int n, int[][] costs) {
        parent = new int[n];
        for(int i=0; i<n; i++) parent[i] = i;
        Arrays.sort(costs,new Comparator<int[]>(){
            public int compare(int[] o1, int[] o2){
                return o1[2]-o2[2];
            }
        }); 
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
    public void union(int a, int b){
        int p1 = getParent(a);
        int p2 = getParent(b);
        if(p1<p2) parent[b] = p1;
        else parent[a] = p2;
    }
    public boolean CheckSameParent(int a, int b){
        int p1 = getParent(a);
        int p2 = getParent(b);
        if(p1==p2)return true;
        return false;
    }
   
}