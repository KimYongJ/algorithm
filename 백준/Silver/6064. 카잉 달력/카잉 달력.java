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
//그 이유는 최소 공배수 이상이면 나머지 연산에서 무한 반복에 빠지기 때문입니다. 최소 공배수 처럼 큰수로 해도 됩니다. ex) M*N처럼.
//구현시 주의사항 : S%N을 할 때 답이 0이면 0이 아니라 N으로 계산해 Y와 비교해봐야 합니다.
class Main{
	
 public static void main(String[] args)throws Exception{
 	StringBuilder sb = new StringBuilder();
 	int T = read();
 	for(int i=0; i<T; i++) {
		int M = read();
        int N = read();
        int X = read();
        int Y = read();
        
        int MAX = M*N/GCD(M,N);
        int result = -1;
        
        for(int S=X; S<=MAX ; S+=M)
        	if( Y == (S%N == 0 ? N : S%N)){
                result = S;
                break;
            }
        
        sb.append( result ).append('\n');
 	}
     System.out.println(sb);
   }
   public static int GCD(int x,int y){
     if(y==0) return x;
     return GCD(y,x%y);
   }    
    static int read() throws Exception {
        int c, n = System.in.read() & 15;
        while ((c = System.in.read()) > 32) n = (n << 3) + (n << 1) + (c & 15);
        return n;
    }
}