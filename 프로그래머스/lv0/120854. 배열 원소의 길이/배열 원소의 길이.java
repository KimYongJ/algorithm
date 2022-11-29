class Solution {
    public int[] solution(String[] strlist) {
        int[] result = new int[strlist.length];
        for(int i=0;i<strlist.length;i++){
            result[i]=strlist[i].length();
        }
        return result;
        
    }
}