// https://github.com/kimyongj/algorithm
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
class Main{
	public static void main(String[] args)throws Exception{
		final char[] text = {'A','C','G','T'};
		BufferedReader	br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		StringBuilder	sb = new StringBuilder();
		int N		= Integer.parseInt(st.nextToken());
		int M		= Integer.parseInt(st.nextToken());
		int distSum = 0; // 해밍거리합
		String str[]= new String[N];
		
		for(int i=0; i<N; i++)
			str[i] = br.readLine();
		
		for(int i=0; i<M; i++) 
		{
			int arr[] = new int[4];
			
			for(int j=0; j<N; j++) 
			{
				char c = str[j].charAt(i);
				switch(c) {
				case 'A':arr[0]++; break;
				case 'C':arr[1]++; break;
				case 'G':arr[2]++; break;
				case 'T':arr[3]++; break;
				}
			}
			int max = Math.max(Math.max(Math.max(arr[0], arr[1]),arr[2]),arr[3]);
			int idx = 0;
			
			while(arr[idx] != max)
				idx++;
			
			sb.append(text[idx]);
			
			for(int j=0; j<4; j++)
				if(j!=idx)
					distSum += arr[j];
		}

		System.out.print(sb.append('\n').append(distSum).toString());
	}
}