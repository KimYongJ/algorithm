class Solution {
    
    char[] arr = {'1','2','4'};
    
    public String solution(int n) {
        StringBuilder sb = new StringBuilder();
        while(n>=1){
            int m = n%3;
            n = n/3;
            if(m==0){
                sb.append(4);
                n--;
            }else{
                sb.append(m);
            }
            
        }
        
        
        return sb.reverse().toString();
    }
}