// https://github.com/KimYongJ/algorithm
import java.util.ArrayList;

class Solution {
    public int[] solution(int n, long k) {
        int[] answer = new int[n];
        ArrayList<Integer> list = new ArrayList<>();
        long factorial = 1;
        for (int i = 1; i <= n; i++) {
            list.add(i);
            factorial *= i;
        }

        int i = 0;
        long remain = k - 1;
// k가 1부터 시작하므로

        while (i < n) {
            
            factorial = factorial / (n - i);
            
            long value = remain / factorial;
            
            answer[i++] = list.get((int)value);
            
            list.remove((int)value);
            
            remain = remain % factorial;
            
        }

        return answer;
    }
}

// 이하 시간 초과

// class Solution {
//     private int[] result; // 결과를 반환할 배열
//     private int[] arr; // 1~n번까지 순회할 배열
//     private boolean[] visit; // 방문체크
//     private boolean endCondition; // 종료를 알리는 변수
//     private long cnt = 0; // 몇번 dfs를 돌았는지 체크하는 변수
//     public int[] solution(int n, long k) {
//         arr = new int[n];
//         result = new int[n];
//         visit = new boolean[n];
        
//         for(int i=0; i<n; i++){
//             arr[i] = i+1; // 순회할 배열에 1~n까지 넣어준다.
//         }
        
//         DFS(0,k);
        
//         return result;
//     }
//     public void DFS(int depth,long k){
//         if(depth == arr.length){ // result배열에 값이 다들어 갔을 때
//             if(++cnt==k){ // 몇번째 만들어진 것인지 체크, k번째와 같다면 종료조건을 true로 변경
//                 endCondition = true;
//             }
//             return;
//         }
//         for(int i=0; i<arr.length; i++){ // arr순회
//             if(!visit[i] && !endCondition){ // 방문하지 않았고, 종료조건이 false일 때 반복
//                 visit[i] = true; // 방문 체크
//                 result[depth] = arr[i]; 
//                 DFS(depth+1,k);// DFS진행
//                 visit[i] = false;// 방문 해제
//             }
//         }
//     }
// }