// // https://github.com/KimYongJ/algorithm
import java.util.*;
class Solution {
    int row,col;
    boolean[] visit;
    String[][] t;
    ArrayList<ArrayList<Integer>> list = new ArrayList<>();
    HashSet<ArrayList<Integer>> resultList = new HashSet<>();
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
            ArrayList<Integer> part = new ArrayList<>();
            for(int i=0; i<n; i++)
                if(visit[i]) part.add(i);
            list.add(part);
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
        for(ArrayList<Integer> part : list) {
            if(checkMinimal(part) && checkUniq(part)) { // 최소성과 유니크 탐색
                resultList.add(part);
            }
        }
    }
    // 최소성 판단 섹터
    public boolean checkMinimal(ArrayList<Integer> part) {
        for(ArrayList<Integer> hubo : resultList){
            if(part.containsAll(hubo)){
                return false;
            }
        }
        return true;
    }
    // 유일성 판단 섹터
    public boolean checkUniq(ArrayList<Integer> part) {
        HashSet<String> set = new HashSet<>();
        for(int i=0; i<row; i++) {
            StringBuilder key = new StringBuilder();
            for(int j=0; j<part.size(); j++) {
                key.append(t[i][part.get(j)]);
            }
            if(!set.add(key.toString())) { // set에 add할 때 중복일 경우 false를 반환함.
                return false;
            }
        }
        return true;
    }
}
