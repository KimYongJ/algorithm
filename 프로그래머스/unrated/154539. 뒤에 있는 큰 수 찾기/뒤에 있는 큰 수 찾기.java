import java.util.Stack;
class Solution {
    public int[] solution(int[] n) {
        int len = n.length;
        int[] result = new int[len];
        Stack<Integer> st = new Stack<>();
        
        for(int i=len-1; i>=0; i--){
            while(!st.isEmpty()){
                if(st.peek()>n[i]){
                    result[i] = st.peek();
                    break;
                }else{
                    st.pop();
                }
            }
            if(st.isEmpty()){
                result[i] = -1;
            }
            st.push(n[i]);
        }
 
        return result;
    }

}