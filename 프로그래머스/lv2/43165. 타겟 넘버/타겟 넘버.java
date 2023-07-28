class Solution {
    private int t,len,result =0;
    private int[] arr;
    
    public int solution(int[] numbers, int target) {
        this.arr = numbers;
        this.t = target;
        this.len = numbers.length;
        
        DFS(0,0);
        
        return result;
    }
    public void DFS(int sum,int depth){
        if(depth == len) {
            if(sum==t) result++;
            return;
        }else{
            DFS(sum+arr[depth],depth+1);
            DFS(sum-arr[depth],depth+1);
        }
    }
}