// https://github.com/kimyongj/algorithm
class Main{
	
	static int read() throws Exception {// 빠른 입력을 위한 함수
		int c, n = System.in.read() & 15;
		while ((c = System.in.read()) > 32) n = (n << 3 ) + (n << 1) + (c & 15);
		return n;
	}
	
	public static void main(String[] args)throws Exception{
		StringBuilder sb = new StringBuilder();
		int L, P, V, idx = 1;
		while(true) 
		{
			L = read(); // 캠핑장 사용 가능 일수
			P = read(); // 캠핑장에 연속으로 있을 수 있는 날
			V = read(); // 총 휴일 개수
			
			if(L == 0 && P == 0 && V == 0) 
			{
				break;
			}
			
			sb.append("Case ").append(idx++)
				.append(": ").append(((V / P)*L) + Math.min(V % P,L))
				.append('\n');
		}
		System.out.print(sb);
	}
}