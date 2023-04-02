class Solution {
    boolean solution(String s) {
        s = s.toLowerCase();
        int n = 0;
        for(char c : s.toCharArray()){
            if(c=='p')
                n++;
            if(c=='y')
                n--;
        }        
        return n==0 ? true : false;
    }
}