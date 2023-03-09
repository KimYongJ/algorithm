// https://github.com/KimYongJ
class Solution {
    static boolean[] bool = new boolean[1001];
    static int result;
    public int solution(int[] nums) {
        
        check(nums,0,new int[3]);

        return result/6;
    }
    public void check(int[] nums,int depth,int[] arr){
        if(depth==3){
            if(prime(arr[0]+arr[1]+arr[2])) result++;
            return;
        }
        for(int i=0; i<nums.length; i++){
            if(!bool[nums[i]]){
                arr[depth] = nums[i];
                bool[nums[i]] = true;
                check(nums,depth+1,arr);
                bool[nums[i]] = false;
            }
        }
    }
    public boolean prime(int n){
        if(n<2) return false;
        
        for(int i=2; i*i<=n; i++)
            if(n%i==0) return false;
        
        return true;
    }
}