class Solution {
    public String solution(String p) {
        String[] str = p.split(" \\+ ");
        int x = 0, n = 0;
        for(String s : str){
            if(s.contains("x"))
                n += s.matches(".*[0-9].*") ?  Integer.parseInt(s.replace("x","")) : 1;
            else
                x += Integer.parseInt(s);
        }
        String result="";
        if(n>1){
           result += String.valueOf(n)+"x"; 
        }else if(n==1)
            result = "x";
        else
            return String.valueOf(x);
        if(x!=0) result+=" + " +String.valueOf(x);
        
        return result;
    }
}