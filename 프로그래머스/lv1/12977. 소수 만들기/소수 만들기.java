// https://github.com/KimYongJ
class Solution {
    static int result;
    public int solution(int[] nums) {
        int len = nums.length;
        for(int a=0; a<len-2; a++)
            for(int b=a+1; b<len-1; b++)
                for(int c=b+1; c<len; c++)
                    if(prime(nums[a]+nums[b]+nums[c]))
                        result++;

        return result;
    }
    public boolean prime(int n){
        if(n<2) return false;
        
        for(int i=2; i*i<=n; i++)
            if(n%i==0) return false;
        
        return true;
    }
}
