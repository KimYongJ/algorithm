class Solution {
    public int solution(int x, int y, int k) {
        int cnt=0;
        char param = Character.forDigit(k,10);
        for(int i=x;i<y+1;i++){
            char[] c = String.valueOf(i).toCharArray();
            for(char a : c){
                if(a==param) cnt++;
            }
        }
        return cnt;
    }
}