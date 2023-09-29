// https://github.com/KimYongJ/algorithm
class Solution {
    
    private static final int CLEAN_TIME = 10; // 청소시간
    private static final int MAX_TIME = 1450; // 최대 시간(24*60 + CLEAN_TIME)
    
    public int solution(String[][] book_time) {
        
        int rooms = 0;
        
        int[] time = new int[MAX_TIME]; // 예약 가능한 시간을 배열로 선언, 겹치는 부분을 카운팅
        
        for(String[] book : book_time){ 
            String start = book[0];
            String end = book[1];
            /*
            *   누적합을 구하기 위해 시작 시간과 종료 시간에 각각 1, -1로 마킹을 해준다.
            */
            time[ getMinute(start) ] +=1;
            
            time[ getMinute(end) + CLEAN_TIME ] -=1;
        }
        
        for(int i=1; i<MAX_TIME; i++){
            time[i]+=time[i-1];
            rooms = Math.max(rooms,time[i]);
        }
        
        return rooms;
    }
    public int getMinute(String str){                  // HH:mm을 분으로 바꿔 주는 함수
        int hour = Integer.parseInt(str.split(":")[0]);
        int min = Integer.parseInt(str.split(":")[1]);
        return min + hour*60;
    }
}