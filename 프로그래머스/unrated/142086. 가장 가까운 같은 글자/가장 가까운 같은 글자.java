class Solution {
    public int[] solution(String s) {
        int len = s.length();
        int[] arr = new int[len];
        for(int i=0;i<len; i++){
            int p = 0;
            boolean check =false;
            for(int j=i-1; j>=0; j--){
                p++;
                if(s.charAt(i)==s.charAt(j)){
                    check =true;
                    break;
                }        
            }
            arr[i] = check ? p : -1;
        }
        return arr;
        
    }
}