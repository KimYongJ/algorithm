class Solution {
    public long solution(long n) {
        int[] count = new int[10];

        for(char c : String.valueOf(n).toCharArray())
            count[c-48]++;
            
        StringBuilder sb = new StringBuilder();
        
        for(int i=9; i>=0; i--)
            for(int j=0; j<count[i]; j++)
                sb.append(i);
        
        return Long.parseLong(sb.toString());
        
    }
}