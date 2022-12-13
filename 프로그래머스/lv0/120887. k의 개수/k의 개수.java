class Solution {
    public int solution(int x, int y, int k) {
        String str="";
        for(int i=x;i<y+1;i++){
            str+=Integer.toString(i);
        }
        String param = Integer.toString(k);
        return str.length() - str.replace(param,"").length();
    }
}