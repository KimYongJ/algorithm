class Solution {
    public String solution(String code) {
        StringBuilder ret = new StringBuilder();
        boolean mode = false;
        for(int i=0; i<code.length(); i++)
            if(code.charAt(i)=='1'){
                mode = !mode;
            }else if(mode && i%2==1){
                ret.append(code.charAt(i));               
            }else if(!mode && i%2==0){
                ret.append(code.charAt(i));
            }
        
        return ret.toString().length()==0 ? "EMPTY" : ret.toString();
    }
}