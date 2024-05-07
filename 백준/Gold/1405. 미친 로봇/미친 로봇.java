// https://github.com/kimyongj/algorithm

public class Main {
    static final int MAX = 29, LEN = 14; // 배열 크기 증가
    static int End;
    static double E, W, S, N, result;
    static boolean[][] visit;

    static int read() throws Exception {// 빠른 입력을 위한 함수
    	int c, n = System.in.read() & 15;
    	while ((c = System.in.read()) > 32) n = (n << 3 ) + (n << 1) + (c & 15);
    	return n;
    }
    static void DFS(int y, int x, int depth, double p) {
    	if(visit[y][x]) return;
        if (depth == End) 
        {
            result += p; // 현재까지의 확률을 결과에 더함
            return;
        }
        visit[y][x] = true;
    	DFS(y, x+1, depth + 1, p * E);
    	DFS(y, x-1, depth + 1, p * W);
    	DFS(y+1, x, depth + 1, p * S);
    	DFS(y-1, x, depth + 1, p * N);
    	visit[y][x] = false;
    }

    public static void main(String[] args) throws Exception {
        End 	= read();
        E 		= read() / 100.0;
        W 		= read() / 100.0;
        S 		= read() / 100.0;
        N 		= read() / 100.0;
        result 	= 0.0;
        visit 	= new boolean[MAX][MAX];
        DFS(LEN, LEN, 0, 1.0); // 시작 확률은 1.0
        System.out.println(result);
    }
}