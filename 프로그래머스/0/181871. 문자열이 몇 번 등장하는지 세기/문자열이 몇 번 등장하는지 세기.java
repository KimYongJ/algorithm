class Solution {
    public int solution(String myString, String pat) {
        int cnt = 0;
        Loop : 
        for(int i=0; i<myString.length(); i++)
        {
            if(myString.charAt(i) == pat.charAt(0))
            {
                int iidx = i+1;
                for(int j=1; j<pat.length(); j++, iidx++)
                {
                    if(iidx==myString.length() || (myString.charAt(iidx) != pat.charAt(j))) 
                        continue Loop;
                }
                cnt++;
            }
        }
        return cnt;
    }
}