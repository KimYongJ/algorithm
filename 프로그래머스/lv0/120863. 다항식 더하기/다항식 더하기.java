class Solution {
    public String solution(String p) {
        String[] str = p.split(" \\+ ");
        int x = 0, n = 0;
        for(String s : str){
            if(s.contains("x"))
                x += s.matches(".*[0-9].*") ?  Integer.parseInt(s.replace("x","")) : 1;
            else
                n += Integer.parseInt(s);
        }
        String result="";
        if(x>1){
           result += String.valueOf(x)+"x"; 
        }else if(x==1)
            result = "x";
        else
            return String.valueOf(n);
        if(n!=0) result+=" + " +String.valueOf(n);
        
        return result;
    }
}