// https://github.com/KimYongJ/algorithm

import java.util.Arrays;

class Main{
	static int N, M, arr[], result[];
	static StringBuilder sb;
    static int read() throws Exception {
        int c, n = System.in.read() & 15;
        while ((c = System.in.read()) > 32) n = (n << 3 ) + (n << 1) + (c & 15);
        return n;
    }
    public static void print(){
        for(int i=0; i<M; i++)
			sb.append(result[i]).append(' ');
		sb.append('\n');
    }
	public static void Back(int depth) {
		if(depth == M) 
		{
			print();
			return;
		}
		for(int i=0; i<N; i++) 
		{
			result[depth] = arr[i];
			Back(depth+1);
		}
	}
	public static void main(String[] args)throws Exception{
		sb 		= new StringBuilder();
		N 		= read();
		M 		= read();
		arr 	= new int[N];
		result 	= new int[M];
		for(int i=0; i<N; i++)
			arr[i] = read();
		
		Arrays.sort(arr);
		
		Back(0);
		
		System.out.println(sb);
	}
}