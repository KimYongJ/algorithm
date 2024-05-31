// https://github.com/kimyongj/algorithm
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

class Main {
    static int 		result = Integer.MAX_VALUE;
    static int 		N;
    static int 		map[][];
    static boolean 	visit[];
    static ArrayList<Integer> ateam, bteam;
    public static void main(String[] args)throws Exception{
	    BufferedReader 	br = new BufferedReader(new InputStreamReader(System.in));
	    StringTokenizer st;
	    N 		= Integer.parseInt(br.readLine());
	    map 	= new int[N][N];
	    visit 	= new boolean[N];
	    ateam 	= new ArrayList <> ();
	    bteam 	= new ArrayList <> ();

	    // bteam에 모든 팀을 먼저 추가해 놓는다.
	    for (int i = 0; i < N; i++) bteam.add(i);
	
	    for (int y = 0; y < N; y++) 
	    {
	        st = new StringTokenizer(br.readLine());
	        for (int x = 0; x < N; x++) 
	        {
	        	int num = Integer.parseInt(st.nextToken());
	            map[y][x] += num;
	            map[x][y] += num;
	        }
	    }
	    
	    // 조합을 구한다. a팀기준으로 1명일때, 2명일때,, N-1명일 때까지
	    for (int i = 1; i < N - 1; i++)
	        if(comb(i, 0)) 
	        	break;
	    
	    System.out.print(result);
	}
	public static boolean comb(int depth, int idx){
	    if (depth == 0) 
	        return cal(); // 계산

	    for (Integer i = idx; i < N; i++) {
	        if (!visit[i]) {
	            visit[i] = true;
	            ateam.add(i);
	            bteam.remove(i);
	            if(comb(depth - 1, i + 1))
	            	return true;
	            ateam.remove(i);
	            bteam.add(i);
	            visit[i] = false;
	        }
	    }
	    return false;
	}
	public static boolean cal(){
		int num		= 0,
			asize 	= ateam.size(),
			bsize 	= bteam.size();

	    for(int i=0; i<asize-1; i++)
	    	for(int j=i+1; j<asize; j++) 
	    		num += map[ateam.get(i)][ateam.get(j)];
	    for(int i=0; i<bsize-1; i++)
	    	for(int j=i+1; j<bsize; j++)
	    		num -= map[bteam.get(i)][bteam.get(j)];
	    result = Math.min(result, Math.abs(num));
	    return result == 0;
	}
}