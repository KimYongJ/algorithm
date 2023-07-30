import java.util.Stack;

class Solution {
    public String[] solution(String[] s) {
     // 110의 갯수를새고 110을 제외한 문자열을 만든다.
     // 앞에서부터 111이 반복되는 숫자를 찾아서 찾을 경우 그곳에 110을 넣고 하나도 없으면 뒤에 계속 붙인다.
        String[] result = new String[s.length];  
        
        for(int i=0; i<s.length; i++){
            int cnt = 0;
            
            Stack<Character> st = new Stack<>();
            // 문자열에서 110의 갯수와, 110을 제거한 문자열을 스텍에 담는다.
            for(int j=0; j<s[i].length(); j++){
                char a = s[i].charAt(j);
                if(st.size()>=2){
                    char b = st.pop();
                    char c = st.pop();
                    if(c=='1' && b=='1' && a=='0')
                        cnt++;
                    else{
                        st.push(c);
                        st.push(b);
                        st.push(a);
                    }
                }else{
                    st.push(a);
                }
            }
            StringBuilder sb = new StringBuilder();
            while(!st.isEmpty())
                sb.append(st.pop()); // 스택에 담긴 char들을 string으로 바꾼다.
            sb = new StringBuilder(sb.reverse().toString());// 110을 제외한 문자열을 구한다.
            
            int idx = sb.lastIndexOf("0")+1; // 0의 마지막 위치를 구한다.

            while(cnt!=0){
                sb.insert(idx,"110"); // 0의 마지막위치가 없으면 자동적으로 맨앞에 110이 추가된다.
                cnt--;
            }
            
            result[i] = sb.toString();
        }
        return result;
    }
}