// https://github.com/KimYongJ/algorithm
// 문제 개요 : 입력된 관계를 통해 전후 관계를 파악하고, 주어지는 값에 대해 구한 관계를 출력
// [ 해설 ]
// 플로이드 와샬 알고리즘으로 관계를 확인할 수 있다. 
// a > b , b > c 일 때 a > c 임을 알 수 있다. 이러한 공식을 통해 
// (a,b), (b,c)를 확인하여 (a,c)를 입력해주기만 하면된다. 이 때 전과 후의 관계를 표현하기 때문에
// 1과 3으로 입력함. 추후 -2를 해주어서 전일 때는 1-2 = -1 , 후일 때는 3-2=1로 출력되도록 함

class Main{
    public static void main(String[] args)throws Exception{
    	new Solution().solution(); 
    }
}
class Solution{
	
	StringBuilder sb = new StringBuilder();
	int a, b, i, j, k, N, K, S, result;
	char arr[][];
	Reader r;
	
	void solution()throws Exception{
		r 		= new Reader();
		N 		= r.read()+1;
		K 		= r.read();
		arr 	= new char[N][N];
		
		for(i=0; i<K; i++) {
			a 			= r.read();
			b 			= r.read();
			arr[a][b] 	= 1; // 추후 -2를 해주어 -1을 표현
			arr[b][a] 	= 3; // 추후 -2를 해주어 1을 표현
		}
		
		// 플로이드 와샬 알고리즘 실행
		for(k=1; k<N; k++) {
			for(i=1; i<N; i++) {
				
				if(k==i) continue; // 빠른 연산을 위한 스킵
				
				for(j=1; j<N; j++) {
					
					if(i==j || k==j)continue;// 빠른 연산을 위한 스킵
					
					// a>b && b>c 를 확인해 a>c를 입력함
					if(arr[i][k] == 1 && arr[k][j] == 1) 
						arr[i][j] = 1;
					
					// a<b && b<c 를 확인해 a<c를 입력함
					else if(arr[i][k] == 3 && arr[k][j] == 3) 
						arr[i][j] = 3;
				}
			}
		}
		
		// 알고자 하는 관계의 숫자 입력
		S 			= r.read();
		for(i=0; i<S; i++) {
			a 		= r.read();
			b 		= r.read();
			result 	= arr[a][b];
			
			if(result!=0) // 연결되어있는 관계인 경우-2를 해줌
				result -= 2;
			
			sb.append(result).append('\n');
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