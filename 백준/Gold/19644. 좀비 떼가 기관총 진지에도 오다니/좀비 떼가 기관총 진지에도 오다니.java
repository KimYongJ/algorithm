//https://github.com/KimYongJ/algorithm
class Main{
	static int read() throws Exception {// 빠른 입력을 위한 함수
		int c, n = System.in.read() & 15;
		while ((c = System.in.read()) > 32) n = (n << 3 ) + (n << 1) + (c & 15);
		return n;
	}
	public static void main(String[] args)throws Exception{
		int L				= read();		// 기관총 앞쪽 길이 1<=3백만
		int effectiv_range	= read();		// 유효사거리
		int kill_power		= read();		// 살상력
		int grenade			= read();		// 수류탄개수
		int useGrend[]		= new int[L+2];	// 수류탄 사용시 유효범위 안에 몇개를 사용했는지 확인
		
		for(int i=1; i<=L; i++) 
		{
			useGrend[i] += useGrend[i-1]; // 누적합
			int energy = read();
			int cnt = Math.min(i, effectiv_range); // 유효범위 안에서 몇번을 기관총 쏠 수 있는지( 추후 사용한 폭탄과 뺌으로 써 구할 수 있게 됨 )
			
			if(energy > kill_power * (cnt-useGrend[i])) 
			{
				if(grenade > 0) // 폭탄을 쓸 수 있을 때
				{
					grenade--;	// 폭탄 한개 사용 
					int rangeEnd = Math.min(i+effectiv_range, L);
					useGrend[i]++;			// 누적합 공식(현 인덱스부터 쭉 +1을 한다는 마킹)
					useGrend[rangeEnd+1]--;	// 누적합 공식(마지막인덱스+1에 마이너스를 함으로 +1하는 것을 중료함)
				}
				else // 폭탄을 써야하는데 폭탄이 없다면 조기 종료
				{
					System.out.print("NO");
					return;
				}
			}
		}

		System.out.print("YES");
	}
}