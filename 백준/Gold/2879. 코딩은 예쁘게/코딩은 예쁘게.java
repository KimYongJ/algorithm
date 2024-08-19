// https://github.com/kimyongj/algorithm
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
class Main{
	public static void main(String[] args)throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		int N		= Integer.parseInt(br.readLine());
		int goal[]	= new int[N];
		int result	= 0;
		
		st = new StringTokenizer(br.readLine());
		for(int i=0; i<N; i++)
			goal[i] = Integer.parseInt(st.nextToken());
		
		st = new StringTokenizer(br.readLine());
		for(int i=0; i<N; i++)
			goal[i] -= Integer.parseInt(st.nextToken());
		
		for(int i=0; i<N; i++) 
		{
			int min = Math.abs(goal[i]);
			int j;
			if(goal[i] == 0) 
				continue;
			for(j = i+1; j<N; j++) {
				if(goal[j] == 0)
					break;
				if(!((goal[i]>0 && goal[j]>0) || (goal[i]<0 && goal[j]<0)))
					break;
				min = Math.min(min, Math.abs(goal[j]));
			}
			if(goal[i] < 0) 
				min = -min;
			while(--j>=0) 
				goal[j] -= min;
			
			result += Math.abs(min);
			if(goal[i] != 0) i--;
		}
		System.out.print(result);
	}
}