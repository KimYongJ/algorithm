//https://github.com/KimYongJ/algorithm
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

class Main{

	static int A, B, win, score1, score2, arr[];
	static boolean same;
	public static int getScore(int n1, int n2) {
		return (n1+n2) % 10;
	}
	public static void main(String[] args)throws Exception{
		BufferedReader br    = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st    = new StringTokenizer(br.readLine());
		arr       = new int[21];
		A         = Integer.parseInt(st.nextToken());
		B         = Integer.parseInt(st.nextToken());
		same      = A==B;
		score1    = getScore(A,B);
		int num   = 0;
		for(int i=1; i<=20; i++) 
        {
			arr[i] = ++num;
			if(num == 10)
				num = 0;
		}
		arr[A] = arr[B+10] = 0;
		
		for(int i=1; i<20; i++) 
        {
			if(arr[i] == 0) continue;
			for(int j=i+1; j<=20; j++) 
            {
				if(arr[j] == 0) continue;
				if(same) 			                        // 내가 땡일 때 
				{
					if(arr[i]==arr[j] && arr[i] < A) win++; // 상대가 나보다 작은 땡일 때
					else if(arr[i]!=arr[j]) win++;			// 상대가 땡이 아닐 때
				}else 				                        // 내가 땡이 아닐 때
				{
					if(arr[i] != arr[j] && score1 > getScore(arr[i],arr[j]))// 상대가 땡이 아니고 나보다 점수가 작을 때  
						win++;
				}
			}
		}
		String str = String.valueOf(Math.round((double)win/153*1000)/1000.0);
		System.out.println(str + "0".repeat(5-str.length()));
	}
}