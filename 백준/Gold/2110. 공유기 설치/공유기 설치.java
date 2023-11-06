//https://github.com/KimYongJ/algorithm
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
import java.util.Arrays;
class Main{
    public static void main(String[] args)throws Exception{
    	BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    	StringTokenizer st = new StringTokenizer(br.readLine());
    	int N = Integer.parseInt(st.nextToken()); // 도현이의 집 개수
    	int C = Integer.parseInt(st.nextToken()); // 공유기 개수
    	int right = 0;
    	int left = 0;
    	int[] arr = new int[N]; // 집의 위치를 담을 배열
    	for(int i=0; i<N; i++) {
    		arr[i] = Integer.parseInt(br.readLine());
    		if(right<arr[i]) right = arr[i]; // 가장큰 값을 저장 
    	}
    	
    	Arrays.sort(arr); // 집 위치를 오름차순 정렬
    	
    	while(left<=right) {
    		int mid = right + left + 1 >> 1;
    		int cnt = 1;
    		int housePosition = arr[0];
    		// 공유기 갯수 카운트 로직
    		for(int i=1; i<N; i++) {
    			if(arr[i]-housePosition<mid) { // 두집 사이의 거리가 중간 보다 작다면
    				for(i=i+1; i<N; i++) {
    					if(arr[i]-housePosition>=mid) {
    						cnt++;
    	    				housePosition = arr[i];
    	    				break;
    					}
    				}
    			}else {
    				housePosition = arr[i];
    				cnt++;
    			}
    		}
    		/////////////////
    		if(cnt<C) { // 공유기가 남았다 : mid를 줄여야 한다.
    			right = mid-1;
    		}else { // 공유기가 모자르거나 같다 : mid를 1늘린다.
    			left = mid+1;
    		}
    	} 
    	System.out.println(left - 1);
    }
}