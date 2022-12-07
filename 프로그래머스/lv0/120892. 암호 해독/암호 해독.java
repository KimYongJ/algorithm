class Solution {
    public String solution(String cipher, int code) {
        char[] c = cipher.toCharArray();
        String r = "";
        for(int i=code;i<=c.length;i+=code)
                r=r+c[i-1]+"";

        return r;
    }
}