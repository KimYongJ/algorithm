class Solution {
    public int solution(String[] s1, String[] s2) {
        int result =0;
        for(String x : s1){
            for(int i=0;i<s2.length;i++){
                if(x.equals(s2[i]))result++;
            }                
        }
        return result;
    }
}