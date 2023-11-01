    // https://github.com/KimYongJ/algorithm
    // 풀이 : 완전 탐색
    class Solution {
    	
    	private char[] arr = {'A', 'C', 'F', 'J', 'M', 'N', 'R', 'T'};
    	private char[] tmp = new char[8]; // 완전 탐색시 사용
    	private int result = 0;
    	private boolean[] visit = new boolean[8];// 완전 탐색시 사용
    	private int n;
    	private String[] data;
        public int solution(int n, String[] data) {
            this.n = n;
            this.data = data;
            DFS(0);
            return result;
        }
        public void DFS(int depth) {
        	if(depth==8) {
        		validate();
        		return;
        	}
        	for(int i=0; i<8; i++) {
        		if(!visit[i]) {
        			visit[i] = true;
        			tmp[depth] = arr[i];
        			DFS(depth+1);
        			visit[i] = false;
        		}
        	}
        }
        public void validate() {
        	for(String s : data) {
        		char c1 = s.charAt(0);
        		char c2 = s.charAt(2);
        		char cmd = s.charAt(3);
        		int interval1 = s.charAt(4)-'0';
        		
        		int c1Idx = 0;
        		int c2Idx = 0;
        		for(int i=0; i<8; i++) {
        			if(c1==tmp[i]) {
        				c1Idx = i;
        			}else if(c2==tmp[i]) {
        				c2Idx = i;
        			}
        		}
        		int interval2 = Math.abs(c1Idx-c2Idx)-1;
        		switch(cmd) {
        		case '=':
        			if(interval1 != interval2)
        				return;
        			break;
        		case '<':
        			if(interval1 <= interval2)
        				return;
        			break;
        		case '>':
        			if(interval1 >= interval2)
        				return;
        			break;
        		}
        	}
        	result++;
        }
    }