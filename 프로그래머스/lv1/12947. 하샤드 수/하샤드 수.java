class Solution {
    public boolean solution(int x) {
        int sum = 0;
        
        for(char c : String.valueOf(x).toCharArray())
            sum += c-48;
        
        return x%sum==0 ? true : false;
    }
}