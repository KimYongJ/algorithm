class Solution {
    public int solution(String[] str) {
        int cnt = 0;
        for(String s: str) if(check(s)) cnt++;
        return cnt;
    }
    public boolean check(String s){
        if(s.length()>10) return false;
        s = s.replace("woo","1").replace("aya","2").replace("ma","3").replace("ye","4");
        // 모두 숫자여야 하며 1,2,3,4가 한개씩만 있어야한다.
        try{
            Integer.parseInt(s); //숫자가 아닐경우 Exception으로 return false;
            for(int i=1; i<5; i++){
               int x = find(i,s);
                if(x>=2)
                    return false;
            }
            return true;
        }catch(Exception e){
            return false;
        }
    }
    public int find(int i,String s){
        int cnt  = 0;
        for(char c : s.toCharArray()){
            if(c==i-'0') cnt++;
        }
        return cnt;
    }
}