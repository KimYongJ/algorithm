class Solution {
    public int solution(int x, int y, int k) {
        String str="";
        for(int i=x;i<y+1;i++){
            str+=i+"";
        }
        
        return str.length() - str.replace(k+"","").length();
    }
}