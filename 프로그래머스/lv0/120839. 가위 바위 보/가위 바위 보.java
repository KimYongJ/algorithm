class Solution {
    public String solution(String rsp) {
        char[] x = {'5','0','2'};
        String r ="";
        for(char c : rsp.toCharArray()){
            r+=x[(c-'0')/2]+"";
        }
        return r;        
    }
}