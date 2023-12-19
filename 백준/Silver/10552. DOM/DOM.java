// https://github.com/KimYongJ/algorithm
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

class Main{

	static int N, M, START, CNT;
	static int like, dislike;
	static int manlike[];
	static ArrayList<Integer>[] dislikeChannel;
	static boolean visitMan[],visitChannel[];
	
	public static int read() throws Exception{
		int c, n = System.in.read() & 15;
		boolean negative = n == 13;
		if(negative) n = System.in.read() & 15;
		while((c = System.in.read()) > 32) n = (n<<3) + (n<<1) + (c & 15);
		if(c == 13) System.in.read();
		return negative?~n+1:n;
	}
	
	public static void DFS(int channel) {
		if(dislikeChannel[channel].size()==0) // end condition
			return;
		if(visitChannel[channel]) { // cycle
			CNT = -1;
			return;
		}
		visitChannel[channel] = true;
		for(int man : dislikeChannel[channel]) {
			if(!visitMan[man]) {
				visitMan[man] = true;
				CNT++;
				DFS(manlike[man]);
				break;
			}
		}
		
	}

	public static void main(String[] args)throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		START = Integer.parseInt(st.nextToken());
		manlike = new int[N];
		visitMan = new boolean[N];
		visitChannel = new boolean[M+1];
		
		dislikeChannel = new ArrayList[M+1];
		for(int i=0; i<=M; i++)
			dislikeChannel[i] = new ArrayList<Integer>();
		for(int i=0; i<N; i++) {
			st = new StringTokenizer(br.readLine());
			like = Integer.parseInt(st.nextToken());
			dislike = Integer.parseInt(st.nextToken());
			manlike[i] = like;
			dislikeChannel[dislike].add(i);
		}
		
		DFS(START);
		
		System.out.println(CNT);
	}
}