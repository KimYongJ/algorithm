class Solution {
    public int solution(String myString, String pat) {
        char[] arr = pat.toCharArray();
        for(int i=0; i<pat.length(); i++){
            arr[i] = arr[i]=='A' ? 'B' : 'A';
        }
        return myString.contains(String.valueOf(arr)) ? 1 : 0;
    }
}