// https://github.com/KimYongJ
import java.util.Stack;
import java.util.HashSet;
class Solution {
    public int solution(int[] order) {
        
        HashSet<Integer> set = new HashSet<>(); // contains 확인을 위해 사용 stack의 contains는 느리다
        
        int result = 0, num=1;
        
        Stack<Integer> stack = new Stack<>();
        
        for(int i=0; i<order.length; i++){
            if(order[i] == num){ 
                num++;
                result++;
            }else if(!stack.isEmpty() && stack.peek() == order[i]){
                stack.pop();
                set.remove(order[i]);
                result++;
            }else if(set.contains(order[i])){
                break;
            }else{
                stack.push(num);
                set.add(num);
                num++;
                i--;
            }
        }
        return result;
    }
}