import java.util.Stack;
class Solution {
    static int result,len;
    public int solution(String s) {
        len = s.length();
        for(int i=0; i<len; i++){
            if(check(s))
                result++;
            s = s.substring(1) + s.substring(0,1);
        }
        return result;
    }
    public boolean check(String s){
        Stack<Character> st = new Stack<>();
        for(char c : s.toCharArray()){
            if(c == '[' || c=='{' || c=='(')
                st.push(c);
            else if(!st.isEmpty()){
                switch(c){
                    case ']':
                        if(st.pop()!='[') return false;
                        break;
                    case '}':
                        if(st.pop()!='{') return false;
                        break;
                    case ')':
                        if(st.pop()!='(') return false;
                        break;
                }
            }else
                return false;
        }
        return st.isEmpty();
    }
}