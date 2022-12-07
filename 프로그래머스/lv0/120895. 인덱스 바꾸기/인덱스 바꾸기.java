class Solution {
    public String solution(String my_string, int num1, int num2) {
        char[] c = my_string.toCharArray();
        char x = c[num1];
        c[num1]=c[num2];
        c[num2]=x;
        return new String(c);
    }
}