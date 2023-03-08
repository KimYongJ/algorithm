// https://github.com/KimYongJ
class Solution {
    public String[] solution(int n, int[] arr1, int[] arr2) {
        String[] rString = new String[n];
        for(int i=0; i<n; i++){
            String str = String.format("%16s",
                                        Integer.toBinaryString(arr1[i] | arr2[i]));
            rString[i] = str.substring(str.length()-n)
                            .replaceAll("0"," ").replaceAll("1","#");
        }
        return rString;
    }
}