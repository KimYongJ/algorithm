class Solution {
    public int solution(String s) {
        char[] cl = s.toCharArray();
        int cnt=0,left=1, right=0;
        char comp=cl[0];
        
        for(int i=1; i<cl.length; i++){
            if(comp==cl[i]){
                left++;
            }else{
                right++;
            }
            if(left==right){
                cnt++;left=0;right=0;
                if(i<cl.length-1){
                    comp=cl[i+1];
                }
            }
        }
        if(!(left==right))
            cnt++;
        
        return cnt;
    }
}