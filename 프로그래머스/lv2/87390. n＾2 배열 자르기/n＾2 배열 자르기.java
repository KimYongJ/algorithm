class Solution {
    static int x,y,len;
    public int[] solution(long n, long left, long right) {
        len = (int)(right-left+1); // 리턴해야할 총 배열
        int[] result = new int[len];
        for(int i=0; i<len; i++){
            left++;
            x = (int)((left -1) % n); // x좌표
            y = (int)((left-1)/n); // y좌표
            result[i] = Math.max(x+1,y+1); // x와 y좌표 중 큰 숫자를 넣어주면됨
        }

        return result;
    }
}