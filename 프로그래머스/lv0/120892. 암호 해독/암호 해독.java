class Solution {
    public String solution(String cipher, int code) {
        char[] c = cipher.toCharArray();
        String r = "";
        for(int i=code;i<=c.length;i++)
            if(i%code==0)
                r=r+c[i-1]+"";

        return r;
    }
}