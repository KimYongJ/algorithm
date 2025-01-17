//https://github.com/kimyongj/algorithm
//https://www.acmicpc.net/problem/6137
//1초 / 128MB
//요약 : 문자가 순서대로 주어지면 앞과 뒤에서 사전순으로 앞서는걸 투포인터로 빼서 새 문자열을 생성한다.
//해설 : 문자열을 80자마다 줄바꿈까지해줘야한다. 그리디하게 양끝에 포인터 넣고 작은것들을 먼저 추가하면된다
// 단, 문자가 같을 경우, 그 쪽의 문자가 작은 것을 먼저 넣어야한다. 그 안쪽도 같으면 그 안쪽의 안쪽까지 계속봐야함.
import java.io.BufferedReader;
import java.io.InputStreamReader;
class Main{
	public static void main(String[] args)throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		int N		= Integer.parseInt(br.readLine());// 문자열길이(1<=이천)
		char arr[]	= new  char[N];
		
		for(int i=0; i<N; i++)
			arr[i] = br.readLine().charAt(0);
		
		int s = 0;
		int e = N - 1;
		int cnt = 0;
		while(s<=e)
		{
			boolean isLeft = true;
			if(arr[s] == arr[e])
			{
				int s1 = s+1;
				int e1 = e-1;
				while(s1<=e1)
				{
					if(arr[s1] < arr[e1])
						break;
					else if(arr[s1] > arr[e1])
					{
						isLeft = false;
						break;
					}
					s1++;
					e1--;
				}
			}
			else if(arr[s] > arr[e])
				isLeft = false;
			
			
			if(isLeft)
				sb.append(arr[s++]);
			else
				sb.append(arr[e--]);
			if(++cnt % 80 == 0)
				sb.append('\n');
		}
		System.out.print(sb);
	}
}
