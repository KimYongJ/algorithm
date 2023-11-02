    // https://github.com/KimYongJ/algorithm
    class Solution {
        public int solution(int n, long l, long r) {
            return execute(n, l, r, 1);
        }
        public int execute(int n, long start, long end, long idx) {
            if(n==0) { // n이 0일 때 종료한다.
                return 1;
            }
            int cnt = 0; // 반환할 cnt 값 
            long part = (long)Math.pow(5, n-1); // n의 한단계 전 값을 구한다.
            for(int i=0 ; i<5 ; i++) { // 5등분 한다.
            	// 먼저 인자로 전달되는 idx는 이전단계의 길이 * 구간에 자기자신을 더한 것으로써 구간 체크에 쓰인다. 
            	// 재귀를 타지 않을 조건은 아래 3가지 입니다. 
            	// (1) i==2일때, 이것은 구간이 모두 0일 경우를 의미한다.
            	// (2) (인덱스 + 이전 단계의 길이 * 5등분한 해당 구간)연산 값이 종료 인덱스보다 크면 재귀를 타지 않는다.
            	// (3) (인덱스 + 이전 단계의 길이 * 5등분한 해당 구간)연산 값이 시작 인덱스보다 작다면 재귀를 타지 않는다.
                if(i==2 || end<(idx+part*i) || (idx+part*(i+1)-1)<start) continue;            
                cnt += execute(n-1, start, end, idx+part*i);
            }
            return cnt;
        }
    }
