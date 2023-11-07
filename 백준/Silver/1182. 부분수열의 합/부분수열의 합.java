// https://github.com/KimYongJ/algorithm
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
class Main{
	
	static int N,S,cnt,arr[];
	static boolean[] visit;
    public static void main(String[] args)throws Exception{
    	BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    	StringTokenizer st = new StringTokenizer(br.readLine());
    	N = Integer.parseInt(st.nextToken());
    	S = Integer.parseInt(st.nextToken());
    	arr = new int[N];
    	visit = new boolean[N];
    	st = new StringTokenizer(br.readLine());
    	for(int i=0; i<N; i++)
    		arr[i] = Integer.parseInt(st.nextToken());
    	for(int i=1; i<=N; i++)
    		combination(i,0);//조합
    	
    	System.out.println(cnt);
    }
    public static void combination(int r, int start) {
    	if(r==0) {
    		int sum = 0;
    		for(int i=0; i<N; i++)
    			if(visit[i])
    				sum += arr[i];
    		if(S==sum)
    			cnt++;
    		return;
    	}
    	for(int i=start; i<N; i++) {
    		if(!visit[i]) {
    			visit[i] = true;
    			combination(r-1,i+1);
    			visit[i] = false;
    		}
    	}
    }

}