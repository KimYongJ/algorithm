class Solution {
    public int[] solution(long n, long left, long right) {
        int len = (int)(right-left+1); // 리턴해야할 총 배열
        int[] result = new int[len];
        int idx = 0; // 리턴해야할 result배열의 idx를 담당
        int x = (int)((left -1) % n+1); // x좌표
        int y = (int)((left-1)/n); // y좌표
        for(int i=y; i<n; i++){
            for(int j=x; j<n; j++){
                if(idx==len) // idx가 len과 같아지면 리턴
                    return result;
                result[idx++] = Math.max(i+1,j+1); // 배열에 숫자를 넣을 때는 규칙이 있다. x와 y좌표의 최댓값+1을 넣는게 규칙이다.
            }
            x = 0; // x좌표는 첫 연산 후 0으로 바꿔준다.
        }
        
        return result;
    }
}