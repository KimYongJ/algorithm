//https://github.com/KimYongJ/algorithm
//해설 (설명할 내용에서 S는 최종적으로 구하고자 하는 답을 의미합니다.)
//구하려는 답을 S로 정의할 때 이 문제는 
//S%M = X이면서 S%N = Y인 S를 구하는 문제 입니다.
//위 식에서 M과 X, N과 Y는 초기값으로 주어집니다. 그렇기에 S를 구하기 위해서는 아래와 같은 구조로 계산을 합니다.
//S의 초기값은 X로 정의합니다.
//그 이유는 M으로 나눈 나머지가 X여야 하기 때문에 초기 값이 X가 됩니다.ex) 3%10=3
//X를 초기 값으로한 S를 N으로 나누어 나머지가 Y인지 확인합니다. 아니라면 S에 M을 더해줍니다.
//그 이유는 M으로 나눈 나머지가 X여야 하기 때문에 초기 값이 X+M이 됩니다. ex) 13%10=3
//X+M 값으로 한 S를 N으로 나눠 나머지가 Y인지 확인합니다. 아니라면 S에 M을 더해 갑니다.
//위 과정 반복..
//반복 문의 탈출 조건은 S가 M과 N의 최소 공배수 이상일 때 종료합니다.
//그 이유는 최소 공배수 이상이면 나머지 연산에서 무한 반복에 빠지기 때문입니다.
//구현시 주의사항 : S%N을 할 때 답이 0이면 0이 아니라 N으로 계산해 Y와 비교해봐야 합니다.
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
class Main{
	
 public static void main(String[] args)throws Exception{
 	BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
 	StringBuilder sb = new StringBuilder();
 	int T = Integer.parseInt(br.readLine());
 	
 	for(int i=0; i<T; i++) {
 		StringTokenizer st = new StringTokenizer(br.readLine());
		int M = Integer.parseInt(st.nextToken());
        int N = Integer.parseInt(st.nextToken());
        int X = Integer.parseInt(st.nextToken());
        int Y = Integer.parseInt(st.nextToken());
        int S = X;
        int LCM = getLCM(M,N,M,N);
        for(; S<=LCM ; S+=M)
        	if( Y == (S%N == 0 ? N : S%N))break;
        	
        if(S>LCM){
            S = -1;
        }
        sb.append(S).append('\n');
 	}
     System.out.println(sb.toString());
 }
 public static int getLCM(int x,int y, int X,int Y){// 최대 공약수와 최소 공배수 한꺼번에 구함
     if(y==0) return X*Y/x;
     return getLCM(y,x%y,X,Y);
 }
}