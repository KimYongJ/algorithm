// https://github.com/KimYongJ
class Solution {
    public String[] solution(int n, int[] arr1, int[] arr2) {
        String[] rString = new String[n];
        for(int i=0; i<n; i++){
            String str = Integer.toBinaryString(arr1[i] | arr2[i]);
            while(n!=str.length()) str = "0"+str;
            str = str.replaceAll("0"," ");
            str = str.replaceAll("1","#");
            rString[i] = str;
        }
        return rString;
    }
}