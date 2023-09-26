// https://github.com/KimYongJ/algorithm
class Solution {
    
    public String solution(String p) {
        return isCorrect(p) ? p : recursion(p);
    }
    
    public String recursion(String w){
        
        if(w.isEmpty()){                        // 문자열이 비어있는지 체크
            return "";
        }
        
        StringBuilder sb = new StringBuilder(); // 빠른 연산을 위한 빌더 생성
        String[] uv = getUV(w);                 // 문자열에 대해 u와 v로 나눈다.
        
        if(isCorrect(uv[0])){                   // u가 "올바른 괄호 문자열" 이라면
            sb.append(uv[0]).append(recursion(uv[1]));
        }
        else{                                  // u가 "올바른 괄호 문자열" 이 아니라면
            sb.append('(').append(recursion(uv[1])).append(')').append(changeU(uv[0]));
        }
        
        return sb.toString();
    }
    
    public String changeU(String u){            // 문자 양옆을 자른 후 각인자에 대해 뒤집기
        StringBuilder sb = new StringBuilder();
        
        for(int i=1; i<u.length()-1; i++){      // 문자열 자체를 뒤집는 것이 아니라 각 인자의 반대 부호를 입력해야함
            if(u.charAt(i)=='('){               // 각 인자에 대한 반대 부호를 입력
                sb.append(')');
            }else{
                sb.append('(');
            }
        }
        return sb.toString();
    }
    
    public String[] getUV(String w){            // u와 v를 구하는 함수
        int idx = 0;
        int check = 0;
        for(;idx<w.length(); idx++){
            check += w.charAt(idx)=='(' ? 1 : -1; // (모양은 +1하고 ) 모양은 -1하여 값이 0이 될 때가 균형문자열이다.
            if(check==0)
                break;
        }
        String u = w.substring(0,idx+1);        // 균형잡힌 괄호 문자열
        String v = w.substring(idx+1);          // 나머지(빈문자열 가능)
        return new String[]{u,v};
    }
    
    public boolean isCorrect(String s){         // 올바른 괄호 문자열 체크
        int check = 0;
        for(int i=0; i<s.length(); i++){
            check += s.charAt(i)=='(' ? 1 : -1;
            if(check<0) return false;
        }
        return check==0 ? true : false;
    }
    
}