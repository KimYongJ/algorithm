// https://github.com/kimyongj/algorithm
class Main{
	 private static int read() throws Exception{
	        int val = 0;
	        int c = System.in.read();
	        while (c <= ' ') {
	            c = System.in.read();
	        }
	        boolean minus = false;
	        if (c == '-') {
	            minus = true;
	            c = System.in.read();
	        }
	        do {
	            val = 10 * val + c - 48;
	        } while ((c = System.in.read()) >= 48 && c <= 57);
	        if (minus) return -val;
	        return val;
	    }
	public static void main(String[] args)throws Exception{
		int N		= read();
		int goal[]	= new int[N];
		int result	= 0;
		
		for(int i=0; i<N; i++)goal[i] = read();
		for(int i=0; i<N; i++)goal[i] -= read();
		
		for(int i=0; i<N; i++) 
		{
			if(goal[i] == 0) 
				continue;
			
			int min = Math.abs(goal[i]);
			int j;
			
			for(j = i+1; j<N; j++) 
			{
				if((goal[j] == 0) || !((goal[i]>0 && goal[j]>0) || (goal[i]<0 && goal[j]<0)))
					break;
				min = Math.min(min, Math.abs(goal[j]));
			}
			
			if(goal[i] < 0) 
				min = -min;
			
			while(--j>=0) 
				goal[j] -= min;
			
			if(goal[i] != 0) 
				i--;
			
			result += Math.abs(min);
		}
		System.out.print(result);
	}
}
