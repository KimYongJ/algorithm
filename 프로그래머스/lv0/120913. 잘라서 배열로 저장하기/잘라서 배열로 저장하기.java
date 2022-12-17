class Solution {
    public String[] solution(String s, int n) {
        int idx = 0 ;
        String dummy ="";
        for(char c : s.toCharArray()){
            dummy +=c+"";
            ++idx;
            if(idx==n){
                dummy +=" ";
                idx=0;
            }
        }
        return dummy.split(" ");
        
        
    }
}