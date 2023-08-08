//https://github.com/KimYongJ
class Solution {
    public String solution(int n, int t, int m, int p) {
        StringBuilder sb = new StringBuilder();
        int cnt = 0;                                        // 미리 구한 숫자의 갯수
        int number = 0;                                     // 부를 숫자의 10진수
        int order = 1;                                      // 현재 순서
        
        Loop : while(true){
            String num = Integer.toString(number++,n);      // 숫자를 n진법으로 변경, 동시에 +1을함
            
            for(char part : num.toCharArray()){             // n진법의 하나하나를 말한다.
                order = (order % m) +( (order % m)==0 ? m : 0 );// order를 1~m까지로 항상 변경함.
                if(order++ == p){                           // 튜브의 순서와 같다면 if문 실행, 동시에 +1을함
                    sb.append(Character.toUpperCase(part)); // 미리 구할 숫자 저장
                    if(t==++cnt) break Loop;                  // 숫자를 다구하면 break, ++는 if문실행시마다 연산됨
                }
            }
        }
        return sb.toString();
    }
}