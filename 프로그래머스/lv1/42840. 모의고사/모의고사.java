import java.util.*;
class Solution {
    static int count = 0;
    public int[] solution(int[] answers) {
        int[] p1 = {1,2,3,4,5};
        int[] p2 = {2,1,2,3,2,4,2,5};
        int[] p3 = {3,3,1,1,2,2,4,4,5,5};
        
        int a1 = find(p1,answers,0);    count = 0;
        int a2 = find(p2,answers,0);    count = 0;
        int a3 = find(p3,answers,0);
         
        return result(a1,a2,a3);
    }
    public int[] result(int a1,int a2, int a3){
        int[] arr = {a1,a2,a3};
        int max = 0;
        for(int i=0; i<3; i++)
            if(max<arr[i]) max = arr[i];
        
        List<Integer> list = new ArrayList<>();

        for(int i=0; i<3; i++)
            if(max==arr[i]) list.add(i+1);
        
        return list.stream().mapToInt(i->i).toArray();
    }
    public int find(int[] p,int[] answers, int idx){
        for(int x : p)
            if(idx==answers.length) return count;
            else if(x==answers[idx++])  count++;
        return find(p,answers,idx);
    }
}