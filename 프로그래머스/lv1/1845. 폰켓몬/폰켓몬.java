// https://github.com/KimYongJ

import java.util.HashSet;
class Solution {
    public int solution(int[] nums) {
        HashSet<Integer> hs = new HashSet<>();
        
        int len = nums.length/2;
        
        for(int n : nums)
            hs.add(n);
        
        return len > hs.size() ? hs.size() : len;
    }
}