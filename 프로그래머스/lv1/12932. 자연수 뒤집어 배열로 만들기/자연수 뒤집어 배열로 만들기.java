class Solution {
    public int[] solution(long n) {
        String str = new StringBuilder().append(String.valueOf(n)).reverse().toString();

        int[] arr = new int[str.length()];
        
        for(int i=0; i<str.length(); i++)
            arr[i] = str.charAt(i)-48;
        
        return arr;
    }
}