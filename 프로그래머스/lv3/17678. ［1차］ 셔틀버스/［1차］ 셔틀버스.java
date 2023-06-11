//https://github.com/KimYongJ
// 셔틀 운행횟수 N과 운행간격 T, 최대 크르 수 M을 갖고 크루들이 꽉차게 타는지 꽉차지 않게 타는지 구분한다.
// 꽉차게 탄다면 가장마지막 타는 사람 보다 -1분하면된다.
// 꽉차게 타지않는 다면 가장 마지막 시간을 탄다.
import java.util.ArrayList;
import java.util.List;
import java.util.Arrays;
import java.util.Collections;
class Solution {
    public String solution(int n, int t, int m, String[] timetable) {
        int[][] result = new int[n][m];
        List<Integer> list = new ArrayList<>();
        for(String str : timetable){
            int time = Integer.parseInt(str.split(":")[0]);
            int min = Integer.parseInt(str.split(":")[1]);
            list.add(time*60+min);
        }
        Collections.sort(list);
        int plusT = 9*60; // 셔틀 운행시간
        for(int i=0; i<n; i++){ // 셔틀 운행 횟수
            for(int j=0; j<m; j++){ // 한번에 넣을 수 있는 인원
                // 셔틀 도착시간 >= 그전에 도착한 사람들을 집어 넣는다.
                if(list.size()>0){
                    if(plusT>=list.get(0)){
                        result[i][j] = list.get(0);
                        list.remove(0);
                    }
                }
            }   
            plusT += t;// 다음 운행 시간을 만든다.
        }
        // result의 마지막 배열에 값이 없다면 셔틀의 마지막 도착시간을 넣는다.
        if(result[n-1][m-1]==0){ // 마지막에 값이 없다면 셔틀의 마지막 도착시간을 넣는다.
            result[n-1][m-1] = 540+(n-1)*t;
        }else{//  마지막에 값이 있다면 마지막 값의 -1을 해서 넣는다.
            result[n-1][m-1] = result[n-1][m-1]-1;
        }
        
        
        return conv(result[n-1][m-1]); // 마지막에 값을 String으로 변경해준다.
    }
    public String conv(int num){
        int t = num/60;
        int m = num%60;
        String ts = t<10 ? "0"+t : t+"";
        String ms = m<10 ? "0"+m : m+"";
        return ts+":"+ms;
    }
}