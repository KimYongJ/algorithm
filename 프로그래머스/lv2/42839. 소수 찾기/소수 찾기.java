import java.util.HashSet;
class Solution {
    
    private int len;
    private int result;
    private char[] list;
    private boolean[] visit;
    private HashSet<Integer> makeList = new HashSet<>();
    
    public int solution(String numbers) {
        len = numbers.length();
        list = numbers.toCharArray();
        visit = new boolean[len];
        
        DFS(0,"0");
        
        return result;
    }
    public void DFS(int depth,String str){
        int num = Integer.parseInt(str);
        
        if(makeList.contains(num)) return;
        if(isPrime(num)) result++;
        
        makeList.add(num);
        
        if(depth==len) return;
        
        
        for(int i=0; i<len; i++){
            if(!visit[i]){
                visit[i] = true;
                DFS(depth+1,str+list[i]);
                visit[i] = false;
            }
        }
        
    }
    public boolean isPrime(int x){
        if(x<=1) return false;
        for(int i=2; i*i<=x; i++)
            if(x%i==0)return false;
        return true;
    }
}
