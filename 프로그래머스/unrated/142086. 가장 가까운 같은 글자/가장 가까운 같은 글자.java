class Solution {
    public int[] solution(String s) {
        int len = s.length();
        int[] arr = new int[len];
        for(int i=0;i<len; i++){
            arr[i] = -1;
            for(int j=i-1; j>=0; j--)
                if(s.charAt(i)==s.charAt(j)){
                    arr[i] = i-j;
                    break;
                }        
        }
        return arr;
        
    }
}
