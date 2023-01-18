import java.util.*;
class Solution {
    public int solution(int[] arr) {
        int cnt = 0;
        Stack<Integer> stack = new Stack<>();
        for(int x : arr){
            stack.push(x);
            if(stack.size()>=4){
                if(stack.get(stack.size()-1)==1
                && stack.get(stack.size()-2)==3
                && stack.get(stack.size()-3)==2
                && stack.get(stack.size()-4)==1){
                    cnt++;
                    stack.pop();stack.pop();stack.pop();stack.pop();
                }
            }
        }
        return cnt;
    }
}