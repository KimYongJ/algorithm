// https://github.com/KimYongJ/algorithm
import java.util.Arrays;
class Solution {
    
    private int result = 0; // 방의 갯수
    private int[] last_time = new int[1001];// 방갯수마다 최종 시간을 담을 배열
    
    public int solution(String[][] book_time) {
        int[][] arr = new int[book_time.length][2]; // 초기 HH:mm 을 분(숫자)로 바꿔준다.
        
        for(int i=0; i<arr.length; i++){
            arr[i][0] = getMin(book_time[i][0]);
            arr[i][1] = getMin(book_time[i][1]);
        }
        
        Arrays.sort(arr, (a,b)->a[0]-b[0]);         // 시작 시간을 기준으로 오름차순 한다.
        
        last_time[result++] = arr[0][1]+10;         // 첫 입실을 미리 넣는다. 이때 종료 시간을 넣으며 종료시간 +10(청소시간)을한다.
        
        Loop : for(int i=1; i<arr.length; i++){
            for(int j=0; j<result; j++){            // 종료시간이 담긴 배열을 순회한다.
                if(last_time[j]<=arr[i][0]){        // 앞으로 들어올 사람의 시작시간이 종료시간과 같거나 크다면 
                    last_time[j] = arr[i][1]+10;    // 종료시간을 갱신한다.
                    continue Loop;
                }
            }
            last_time[result++] = arr[i][1]+10;     // 새로운 방이 필요한 경우
        }
        
        
        return result;
    }
    public int getMin(String str){                  // HH:mm을 분으로 바꿔 주는 함수
        int hour = Integer.parseInt(str.split(":")[0]);
        int min = Integer.parseInt(str.split(":")[1]);
        return min + hour*60;
    }
}