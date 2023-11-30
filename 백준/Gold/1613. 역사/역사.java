// https://github.com/KimYongJ/algorithm
// 문제 개요 : 입력된 관계를 통해 전후 관계를 파악하고, 주어지는 값에 대해 구한 관계를 출력
// [ 해설 ]
// 플로이드 와샬 알고리즘으로 관계를 확인할 수 있다. 
// a > b , b > c 일 때 a > c 임을 알 수 있다. 이러한 공식을 통해 
// (a,b), (b,c)를 확인하여 (a,c)를 입력해주기만 하면된다. 이 때 전과 후의 관계를 표현하기 때문에
// 1과 3으로 입력함. 추후 -2를 해주어서 전일 때는 1-2 = -1 , 후일 때는 3-2=1로 출력되도록 함


import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

class Main{
    public static void main(String[] args)throws Exception{
    	new Solution().solution(); 
    }
}
class Solution{
	void solution()throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int N = Integer.parseInt(st.nextToken())+1;
		int K = Integer.parseInt(st.nextToken());
		int a, b, result;
		char arr[][] = new char[N][N];
		
		for(int i=0; i<K; i++) {
			st = new StringTokenizer(br.readLine());
			a = Integer.parseInt(st.nextToken());
			b = Integer.parseInt(st.nextToken());
			
			arr[a][b] = 1; // 추후 -2를 해주어 -1을 표현
			arr[b][a] = 3; // 추후 -2를 해주어 1을 표현
		}
		// 플로이드 와샬 알고리즘 실행
		for(int k=1; k<N; k++) {
			for(int i=1; i<N; i++) {
				if(k==i) continue;
				for(int j=1; j<N; j++) {
					if(i==j || k==j)
						continue;
					if(arr[i][k] == 1 && arr[k][j] == 1) arr[i][j] = 1;
					else if(arr[i][k] == 3 && arr[k][j] == 3) arr[i][j] = 3;
				}
			}
		}
		
		// 알고자 하는 관계의 숫자 입력
		int S = Integer.parseInt(br.readLine());
		
		for(int i=0; i<S; i++) {
			st = new StringTokenizer(br.readLine());
			a = Integer.parseInt(st.nextToken());
			b = Integer.parseInt(st.nextToken());
			result = arr[a][b];
			if(result!=0)
				result -= 2;
			sb.append(result)
			  .append('\n');
		}
		System.out.println(sb);
	}
}

// 빠른 입력을 위해 만듦
class Reader {
    final int SIZE = 1 << 13;
    byte[] buffer = new byte[SIZE];
    int index, size;
    int read() throws Exception {
        int n = 0;
        byte c;
        while ((c = get()) <= 32);
        do n = (n << 3) + (n << 1) + (c & 15);
        while (isNumber(c = get()));
        return n;
    }
    boolean isNumber(byte c) {
        return 47 < c && c < 58;
    }
    byte get() throws Exception {
        if (index == size) {
            size = System.in.read(buffer, index = 0, SIZE);
            if (size < 0) buffer[0] = -1;
        }
        return buffer[index++];
    }
}