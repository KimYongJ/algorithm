class Solution {
    public String solution(String X, String Y) {
        int[] dpx = new int[11];
        int[] dpy = new int[11];
        String str = "";
        
        for(char c : X.toCharArray())
            dpx[c-'0']++;
        for(char c : Y.toCharArray())
            dpy[c-'0']++;
        
        for(int i=10; i>=0; i--){
            int n = dpx[i]>dpy[i] ? dpy[i] : dpx[i];
            str += String.valueOf(i).repeat(n);
        }
        str =  "".equals(str) ? "-1" : str.charAt(0)=='0' ? "0" : str;
        return str;       
    }
}