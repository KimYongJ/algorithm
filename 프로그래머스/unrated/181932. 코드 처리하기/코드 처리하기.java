class Solution {
    public String solution(String code) {
        StringBuilder ret = new StringBuilder();
        int mode = 0;
        for(int i=0; i<code.length(); i++)
            if(code.charAt(i)=='1'){
                mode = mode==0 ? 1:0;
            }else if(i%2==mode){
                ret.append(code.charAt(i));               
            }
        
        return ret.toString().length()==0 ? "EMPTY" : ret.toString();
    }
}