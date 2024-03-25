// https://github.com/KimYongJ/algorithm

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

class Main{
	
	static int MAX_DEPTH, C, VowelsCnt, ConsonantsCnt;
	static char arr[], string[];
	static boolean visit[];
	static StringBuilder sb;
	public static void BackTracking(int idx, int depth)
	{
		if(depth == MAX_DEPTH) 
		{
			VowelsCnt = ConsonantsCnt = 0;
			for(int i=0; i<depth; i++) {
				if(string[i]=='a' || string[i]=='e' || string[i]=='i' || string[i]=='o' || string[i]=='u')
					VowelsCnt++;
				else ConsonantsCnt++;
			}
			if(VowelsCnt>=1 && ConsonantsCnt>=2)
				sb.append(new String(string)).append('\n');
			return;
		}
		for(int i=idx; i<C; i++) 
		{
			if(!visit[i]) 
			{
				visit[i] = true;
				string[depth] = arr[i];
				BackTracking(i, depth+1);
				visit[i] = false;
			}
		}
	}
	public static void main(String[] args)throws Exception{
		BufferedReader br 	= new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st 	= new StringTokenizer(br.readLine());
		MAX_DEPTH 			= Integer.parseInt(st.nextToken());
		C 					= Integer.parseInt(st.nextToken());
		arr 				= new char[C];
		string				= new char[MAX_DEPTH];
		visit 				= new boolean[C];
		sb 					= new StringBuilder();
		st 					= new StringTokenizer(br.readLine());
		for(int i=0; i<C; i++)
			arr[i] = st.nextToken().charAt(0);
		
		Arrays.sort(arr);
		
		BackTracking(0,0);
		
		System.out.println(sb);
	}
}