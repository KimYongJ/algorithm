import java.util.*;
class Solution {
    static int count = 0;
    public int[] solution(int[] answers) {
        int[][] p = {
            {1,2,3,4,5},
            {2,1,2,3,2,4,2,5},
            {3,3,1,1,2,2,4,4,5,5}
        };
        int[] arr = new int[3];
        for(int i=0; i<3; i++){
            arr[i] = find(p[i],answers,0);
            count = 0;
        }
        int max = 0;
        for(int i=0; i<3; i++)
            if(max<arr[i]) max = arr[i];
        
        List<Integer> list = new ArrayList<>();
        for(int i=0; i<3; i++) if(max==arr[i]) list.add(i+1);
        
        int[] result = new int[list.size()];
        for(int x : list) result[count++] = x;
        return result;
    }
    public int find(int[] p,int[] answers, int idx){
        for(int x : p)
            if(idx==answers.length) return count;
            else if(x==answers[idx++])  count++;
        return find(p,answers,idx);
    }
}