class Solution {
    public int[] solution(long n) {
        StringBuilder sb = new StringBuilder();
        sb.append(String.valueOf(n));
        String str = sb.reverse().toString();
        int[] arr = new int[str.length()];
        
        for(int i=0; i<str.length(); i++)
            arr[i] = str.charAt(i)-48;
        
        return arr;
    }
}