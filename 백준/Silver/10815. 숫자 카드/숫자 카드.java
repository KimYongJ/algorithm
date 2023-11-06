// https://github.com/KimYongJ/algorithm

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
import java.util.Arrays;
class Main{

	static StringBuilder sb = new StringBuilder();
	static int N,M, left, right, mid, arr[];
	
    public static void main(String[] args)throws Exception{
    	BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    	N = Integer.parseInt(br.readLine());
    	arr = new int[N];
    	StringTokenizer st = new StringTokenizer(br.readLine());
    	for(int i=0; i<N; i++) // 상근이의 숫자카드를 넣는다.  
    		arr[i] = Integer.parseInt(st.nextToken());
    	
    	Arrays.sort(arr); // 상근이 카드를 정렬 한다.
    	
    	M = Integer.parseInt(br.readLine());
    	st = new StringTokenizer(br.readLine());
    	for(int i=0; i<M; i++) {
    		int num = Integer.parseInt(st.nextToken());
    		sb.append(find(num)).append(" ");
    				
    	}
    	System.out.println(sb.toString());
    }
    public static int find(int num) {
    	int result = 0;
    	left = 0;
    	right = N-1;
    	while(left<=right) {
    		mid = right+left>>1;
    		if(arr[mid]==num) {
    			result = 1; break;
    		}else if(arr[mid]<num) {
    			left = mid+1;
    		}else {
    			right = mid-1;
    		}
    	}
    	return result;    	
    }
}
