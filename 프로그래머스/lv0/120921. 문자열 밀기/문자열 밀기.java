class Solution {
    public int solution(String a, String b) {
        int cnt = 0;
        int l = a.length()-1;
        while(cnt<l+1){
            if(a.equals(b))
                return cnt;
            a = a.charAt(l)+a.substring(0,l);
            cnt++;
        }
        return -1;
    }
}