class Solution {
    static int cnt, zero;
    public int[] solution(String s) {
        int len=0;
        while(!s.equals("1")){
            zero += s.length();
            s = s.replaceAll("0","");
            zero -= s.length();
            s = Integer.toBinaryString(s.length()); 
            cnt++;
        }
        return new int[]{cnt,zero};
    }
}