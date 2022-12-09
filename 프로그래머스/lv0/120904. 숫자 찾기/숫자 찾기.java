class Solution {
    public int solution(int num, int k) {
        String str = Integer.toString(num);
        String kk = Integer.toString(k);
        int i=0;
        
        for(char c : str.toCharArray()){
            if(c==kk.toCharArray()[0]){
                return i+1;
            }
            i++;
        }
        return -1;
    }
    
}