// https://github.com/KimYongJ/algorithm
import java.io.BufferedReader;
import java.io.InputStreamReader;
class Main{
    public static void main(String[] args)throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        int M = Integer.parseInt(br.readLine());
        int result = 0;
        String str = br.readLine();
        
        char[] arr = new char[M+2]; // 여유를 두어 마지막에도 cal함수를 탈 수 있도록 함
        for(int i=0; i<M; i++)
        	arr[i] = str.charAt(i);
        
        int base = 1 + 2*N;
        int cnt = 0;
        boolean flage = false;
        for(int i=0; i<M+1; i++) {
        	if(flage) {
        		if(arr[i]=='O' && arr[i+1]=='I') {
        			cnt+=2;
        			i++;        			
        		}else {
        			result += cal(cnt,base);
        			flage = false;
        			i--;
        		}
        	}else {
        		// 최초 I가 나오면 카운팅 시작 : 카운팅이란, oi의 반복 횟수이다. 
        		if(arr[i]=='I') flage = true;
        		cnt = 1;
        	}
        }
        System.out.println(result);
        
    }
    public static int cal(int ioLen, int base) {
    	int num = ioLen - base;
    	if(num==0) {
    		return 1;
    	}else if(num>0) {
    		return num/2+1;
    	}
    	return 0;
    }
}