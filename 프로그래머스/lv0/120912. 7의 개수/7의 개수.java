class Solution {
    public int solution(int[] array) {
        String str ="";
        
        for(int x : array)
            str+= Integer.toString(x);
        
        return str.length() - str.replaceAll("7","").length();
    }
}
