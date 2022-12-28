class Solution {
    public int solution(String t, String p) {
        int cnt = 0;
        int plen = p.length();
        for(int i=0; i<t.length()-plen+1; i++){
            String num = t.substring(i,i+plen);
            if(num.compareTo(p)<=0)
                cnt++;
        }
        return cnt;
    }
}