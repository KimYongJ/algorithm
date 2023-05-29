//https://github.com/KimYongJ
class Solution {
    public int solution(int n, int m, int[] section) {
        int result = 0;
        int next = section[0];
        
        for(int s : section){
            if(s>=next){
                next = s+m;
                result++;
            }
            
        }
        
        return result;   
    }
}