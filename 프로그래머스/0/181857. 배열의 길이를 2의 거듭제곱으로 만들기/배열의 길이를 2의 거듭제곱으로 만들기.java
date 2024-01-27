import java.util.ArrayList;
class Solution {
    public int[] solution(int[] arr) {
        int alen = arr.length;
        int rlen = 1;
        while(rlen<alen){
            rlen *= 2;
        }
        
        if(alen==rlen)
            return arr;
        
        int[] answer = new int[rlen];
        for(int i=0; i<arr.length; i++)
            answer[i] = arr[i];
        
        return answer;
    }
}