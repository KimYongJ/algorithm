class Solution {
    public int solution(String[] spell, String[] dic) {
        boolean check = false;
        for(String d : dic){
            for(String s : spell){
                if(check(d,s))
                    check = true;
                else{
                    check = false;
                    break;
                }
            }
            if(check)
                return 1;   
        }
        return 2;
    }
    public static boolean check(String d, String s){
        if(d.length()-d.replaceAll(s,"").length()==1)
            return true;
        else
            return false;
    }
    
}