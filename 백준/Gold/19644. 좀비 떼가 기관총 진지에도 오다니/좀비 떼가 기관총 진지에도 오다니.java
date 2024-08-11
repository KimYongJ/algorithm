//https://github.com/KimYongJ/algorithm
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

class Main{
	public static void main(String[] args)throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int L = Integer.parseInt(st.nextToken()); // 기관총 앞쪽 길이 1<=3백만
		st = new StringTokenizer(br.readLine());
		int effectiv_range = Integer.parseInt(st.nextToken()); 		// 유효사거리
		int kill_power = Integer.parseInt(st.nextToken()); 			// 살상력
		int grenade = Integer.parseInt(br.readLine());				// 수류탄개수
		int useGrend[] = new int[L+2];								// 수류탄 사용시 유효범위 안에 몇개를 사용했는지 확인
		String result = "YES";
		int len1 = Math.min(effectiv_range,L);
		for(int i=1; i<=len1; i++) {
			useGrend[i] += useGrend[i-1]; // 누적합
			int energy = Integer.parseInt(br.readLine());
			if(energy > kill_power * (i-useGrend[i])) {
				if(grenade > 0) {
					grenade--;
					int rangeEnd = Math.min(i+effectiv_range, L);
					useGrend[i]++;
					useGrend[rangeEnd+1]--;
				}else {
					result = "NO";
				}
			}
		}
		if("YES".equals(result)) {
			for(int i=len1+1; i<=L; i++) {
				useGrend[i] += useGrend[i-1]; // 누적합
				int energy = Integer.parseInt(br.readLine());
				if(energy > kill_power * (effectiv_range-useGrend[i])) {
					if(grenade > 0) {
						grenade--;
						int rangeEnd = Math.min(i+effectiv_range, L);
						useGrend[i]++;
						useGrend[rangeEnd+1]--;
					}else {
						result = "NO";
					}
				}
			}
		}
		
		System.out.print(result);
	}
}