//https://github.com/kimyongj/algorithm
//https://www.acmicpc.net/problem/1893
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;

class Main{
	
	public static void main(String[] args)throws Exception{
		BufferedReader	br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder	sb = new StringBuilder();
		
		int T = Integer.parseInt(br.readLine());// 테스트케이스(N)
		
		while(T-->0)
		{
			char[] A = br.readLine().toCharArray();			// 알파벳 순서(대소문자+숫자+모든문자가 서로다름), 3<=62
			char[] W = br.readLine().toCharArray();			// 원문	(1<=50,000)
			char[] S = br.readLine().toCharArray();			// 암호문	(3<=500,000)
			int alen = A.length;				// 알파벳 순서 길이
			int wlen = W.length;				// 원문 길이
			int slen = S.length;				// 암호문 길이
			
			ArrayList<Integer> ans				= new ArrayList<>();
			HashMap<Character, Character> conv	= new HashMap<>();
			
			// 알파벳 순서를 통해 특정 문자의 다음 문자를 저장함
			for(int i=0; i<alen; i++)
				conv.put( A[i], A[ (i+1)%alen ] );
			
			// 알파벳 순서 만큼 원문(W)을 탐색하면서 한칸씩 바꾼다.
			for(int shift=0; shift<alen; shift++)
			{
				if(shift != 0)
				{
					char next[] = new char[wlen];
					for(int i=0; i<wlen; i++)
						next[i] = conv.get(W[i]);
					W = next;
				}
				
				int pi[] = new int[wlen];
				
				for(int i=1, j=0; i<wlen; i++)	// 현재위치(i), 접두사 길이(j)
				{
					// 현재 문자와 접두사 끝 문자가 다르면 j를 이전 접두사정보로 돌아가게함(pi[j-1])
					while(0<j && W[i] != W[j])
						j = pi[j - 1];
					// 현재 문자와 접두사 끝 문자가 같으면 접두사 길이 증가(j+1) 후 값 저장
					if(W[i] == W[j])
						pi[i] = ++j;
				}
				// KMP 알고리즘으로 찾기
				int find = 0;
				for(int i=0, j=0; i<slen; i++)		// S의위치(i), W의 위치(j)
				{
					// 암호문과 원문이 일치하지 않을 때 원문의 인덱스를 이전으로 되돌린다.
					while(0<j && S[i] != W[j])
						j = pi[j - 1];
					// 암호문과 원문이 일치할 때, 원문의 끝인덱스면 찾은것이고, 아니면 원문의 인덱스를 추가한다.
					if(S[i] == W[j])
					{
						if(j == wlen - 1)
						{
							++find;
							j = pi[j];
						}
						else ++j;
					}
					
				}
				
				if(find == 1)
					ans.add(shift);
			}
			
			switch(ans.size())
			{
				case 0:
					sb.append("no solution");
					break;
				case 1:
					sb.append("unique: ");
					break;
				default:
					sb.append("ambiguous: ");
					Collections.sort(ans);
			}
			
			for(int a : ans)
				sb.append(a).append(' ');
			
			sb.append('\n');
		}
		
		System.out.print(sb);
	}
}