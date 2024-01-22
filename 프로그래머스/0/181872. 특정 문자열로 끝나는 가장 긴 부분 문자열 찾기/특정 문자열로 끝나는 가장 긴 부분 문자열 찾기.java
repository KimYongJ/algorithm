class Solution {
    public String solution(String myString, String pat) {
        int end = 0;
        Loop :
        for(int i=myString.length()-1; i>=0 ;i--)
            if(myString.charAt(i) == pat.charAt(pat.length()-1))
            {
                end = i+1;
                for(int j=pat.length()-2; j>=0; j--)
                    if(myString.charAt(--i) != pat.charAt(j))
                        continue Loop;
                break;
            }
        return myString.substring(0,end);
    }
}