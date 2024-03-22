class Solution {
    public String solution(String my_string, int[][] queries) {
        char[] c = my_string.toCharArray();
        for(int q[] : queries){
            for(int i=q[0]; i<q[1]; q[1]--,i++){
                char a = c[i];
                c[i] = c[q[1]];
                c[q[1]] = a;
            }
        }
        return new String(c);
    }
}