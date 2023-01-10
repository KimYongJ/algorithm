class Solution {
    public int solution(String[] str) {
        int cnt = 0;
        for(String s: str) if(check(s)) cnt++;
        return cnt;
    }
    public boolean check(String s){
        if(s.length()>10) return false;
        s = s.replace("woo","1").replace("aya","2").replace("ma","3").replace("ye","4");
        // 모두 숫자여야 함
        try{
            Integer.parseInt(s); //숫자가 아닐경우 Exception으로 return false;
            return true;
        }catch(Exception e){
            return false;
        }
    }
}