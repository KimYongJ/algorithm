import java.util.ArrayList;
class Solution {
    
    private int len;
    private int result;
    private char[] list;
    private boolean[] visit;
    private ArrayList<Integer> makeList = new ArrayList<>();
    
    public int solution(String numbers) {
        len = numbers.length();
        list = numbers.toCharArray();
        visit = new boolean[len];
        
        DFS(0,"0");
        
        return result;
    }
    public void DFS(int depth,String str){
        int num = Integer.parseInt(str);
        if(makeList.contains(num)) 
            return;
        makeList.add(num);
        if(depth==len){
            if(isPrime(num)) result++;
            return;
        }
        if(isPrime(num)) result++;
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
        for(int i=3; i*i<=x; i+=2)
            if(x%i==0)return false;
        return true;
    }
}