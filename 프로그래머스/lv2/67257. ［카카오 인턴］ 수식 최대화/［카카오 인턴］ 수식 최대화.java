// https://github.com/KimYongJ/algorithm
import java.util.ArrayList;
class Solution {
    
    private long result = 0;                                        // 결과반환할 변수
    private String expression;                                      // 초기에 주어진 문자열을 전역변수로 사용
    private boolean[] visit = new boolean[3];
    private ArrayList<Long> number1 = new ArrayList<>();            // 초기에 주어진 문자열의 숫자만 담는 리스트
    private ArrayList<String>operStr1 = new ArrayList<>();          // 추기에 주어진 문자열의 연산자만 담는 리스트
    
    
    public long solution(String expression) {
        String[] numberArr = expression.split("[^0-9]");            // 정규식을 이용해  숫자만 담긴 list를 생성하는 과정
        String[] operatorArr = expression.trim().split("[0-9]+");   // 정규식을 이용해  연산자만 담긴 list를 생성하는 과정
        number1 = new ArrayList<>();
        operStr1 = new ArrayList<>();
        
        for(String s: numberArr)                                    // 정규식을 이용해  숫자만 담긴 list를 생성하는 과정
            number1.add(Long.parseLong(s));
        
        for(int i=1; i<operatorArr.length; i++)                     // 정규식을 이용해  연산자만 담긴 list를 생성하는 과정
            operStr1.add(operatorArr[i]);
        
        this.expression = expression;
        
        String[] base = getOperator(expression);                    // 문자열안의 연산자 종류를 중복을 제거해 가져오는 함수
        String[] priority = new String[base.length];                // 우선순위 순서를 담을 배열
        Back(base,0,base.length,priority);                          // 순서 : 조건에포함된 연산자 종류들, 깊이, 연산자갯수, 우선순위 순서를 담을 배열

        return result;
    }
    public void Back(String[] base,int depth,int len,String[] priority){// 순서 : 조건에포함된 연산자 종류들, 깊이, 연산자갯수, 우선순위 순서를 담을 배열
        if(depth == len){
            long num = calculate(priority); // 연산자 우선순위 배열을 전달해 해당 순서로 연산을 진행한다.
            result = Math.max( result, Math.abs(num) );
            return;
        }
        for(int i=0; i<len; i++)
            if(!visit[i]){
                visit[i] =true;
                priority[depth] = base[i];
                Back(base,depth+1,len,priority);
                visit[i] = false;
            }
    }
    public long calculate(String[] priority){                           // 연산자 순서대로 연산진행
        ArrayList<Long> number2 = new ArrayList<>();
        ArrayList<String>operStr2 = new ArrayList<>();
        
        number2.addAll(number1);                                        // 초기 숫자만 담긴 리스트를 복사해온다.
        operStr2.addAll(operStr1);                                      // 초기 연산자만 담긴 리스트를 복사해온다.
        
        for(String p :priority){
            for(int i=0; i<operStr2.size(); i++){
                if(p.contains(operStr2.get(i))){
                    long x = cal(number2.get(i),number2.get(i+1),p);
                    operStr2.remove(i);
                    number2.remove(i);
                    number2.remove(i);
                    number2.add(i,x);
                    i--;
                }
            }
        }
        return number2.get(0);
    }
    public long cal(long a, long b, String p){ // 연산자에따라 결과 반환해주는 함수
        if(p.contains("*")){
            return a*b;
        }else if(p.contains("+")){
            return a+b;
        }else if(p.contains("-")){
            return a-b;
        }
        return 0;
    }
    
    public String[] getOperator(String e){// 문자열안의 연산자 가져오는 함수
        ArrayList<String> list = new ArrayList<>();
        
        if(e.contains("*")) list.add("\\*");
        if(e.contains("+")) list.add("\\+");
        if(e.contains("-")) list.add("\\-");
        
        String[] oper = new String[list.size()];
        
        for(int i=0; i<oper.length; i++) 
            oper[i] = list.get(i);
        
        return oper;
    }
}