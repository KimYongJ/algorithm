class Solution {
        public int solution(int[][] dots) {
            int answer = 0;

            // 1,2 번째 점
            if((dots[0][1] - dots[1][1]) * (dots[2][0] - dots[3][0]) ==
            (dots[2][1] - dots[3][1]) * (dots[0][0] - dots[1][0]))
            return 1;

            //1,3 번째 점
            if((dots[0][1] - dots[2][1]) * (dots[1][0] - dots[3][0])==
            (dots[1][1] - dots[3][1]) * (dots[0][0] - dots[2][0]))
            return 1;

            // 1,4 번째 점
            if((float)(dots[0][1] - dots[3][1]) * (dots[2][0] - dots[1][0])==
            (float)(dots[2][1] - dots[1][1]) * (dots[0][0] - dots[3][0]))
            return 1;

            return answer;
        }
    }