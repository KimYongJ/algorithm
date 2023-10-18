// // https://github.com/KimYongJ/algorithm
import java.util.*;
class Solution {
    int row,col;
    boolean[] visit;
    String[][] t;
    ArrayList<String> list = new ArrayList<>();
    ArrayList<String> resultList = new ArrayList<>();
    public int solution(String[][] t) {
        this.t = t;
        row = t.length;
        col = t[0].length;
        visit = new boolean[col];
        for(int i=1; i<=col; i++) {
            combination(col,i,0); // col갯수 중 i개의 조합을 list에 담는다.
            // 조합이 만들어 진 후 검사 진행 
            validate(); // list에 담긴 조합을 체크한다
            list.clear();// 조합을 초기화한다.
        }
        return resultList.size(); // 결과에 담긴 후보키들 갯수를 반환
    }
    // 조합 만드는 섹터
    public void combination(int n, int r, int start) {
        if(r==0) {
            StringBuilder sb = new StringBuilder();
            for(int i=0; i<n; i++)
                if(visit[i]) sb.append(i);
            list.add(sb.toString());
        }
        for(int i=start; i<n; i++) {
            visit[i] = true;
            combination(n,r-1,i+1);
            visit[i] = false;
        }
    }
    // 검증 섹터
    public void validate() {
        // 리스트의 조합 하나씩 순회
        for(String str : list) {
            if(checkMinimal(str) && checkUniq(str)) { // 최소성과 유니크 탐색
                resultList.add(str);
            }
        }
    }
    // 최소성 판단 섹터
    public boolean checkMinimal(String str) {
        // 최소성 판단시, 최종적으로 구한 후보키들과 새로만들어서 비교해야할 예비 후보키를 비교할 때 속도가 느려서 카운팅정렬을 사용해 속도를 높였다.
        boolean[] CountingSort = new boolean[9];
        for(char c : str.toCharArray()){
            CountingSort[c-'0'] = true;
        }
        
        for(String hubo : resultList){
            int cnt = 0;
            int len = hubo.length();
            for(int i=0; i<len; i++){
                if(CountingSort[hubo.charAt(i)-'0']){
                    cnt++;
                }
            }
            if(cnt == len){ // 확정된 후보키가 예비 후보키안에 모두 포함이 되어있을 때(이 경우 예비 후보키는 탈락이다.)
                return false;
            }
        }
        return true;
    }
    // 유일성 판단 섹터
    public boolean checkUniq(String str) {
        HashSet<String> set = new HashSet<>();
        for(int i=0; i<row; i++) {
            StringBuilder key = new StringBuilder();
            for(int j=0; j<str.length(); j++) {
                key.append(t[i][str.charAt(j)-'0']);
            }
            if(!set.add(key.toString())) { // set에 add할 때 중복일 경우 false를 반환함.
                return false;
            }
        }
        return true;
    }
}
