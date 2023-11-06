//https://github.com/KimYongJ/algorithm
import java.util.Arrays;
class Main{
    public static void main(String[] args)throws Exception{
    	int N = read(); // 도현이의 집 개수
    	int C = read(); // 공유기 개수
    	int right = 0, left = 0;
    	int[] arr = new int[N]; // 집의 위치를 담을 배열
    	for(int i=0; i<N; i++)
    		arr[i] = read();
    	
    	Arrays.sort(arr); // 집 위치를 오름차순 정렬
        
    	right = arr[N-1]; // 가장 큰 값을 저장 
    	while(left<=right) {
    		int mid = right + left >> 1;
    		int cnt = 1;
    		int housePosition = arr[0];
    		// 공유기 갯수 카운트 로직
    		for(int i=1; i<N; i++) {
    			if(arr[i]-housePosition>=mid) { // 두집 사이의 거리가 mid보다 멀다면 
					cnt++;
    				housePosition = arr[i];
    			}
    		}

    		if(cnt<C) { // 공유기가 남았다 : mid를 줄여야 한다.
    			right = mid-1;
    		}else { // 공유기가 모자르거나 같다 : mid를 1늘린다.
    			left = mid+1;
    		}
    	} 

    	System.out.println(right);
    }
    private static int read() throws Exception {
        int value = 0;
        while (true) {
            int input = System.in.read();
            if (input == ' ' || input == '\n') {
                return value;
            } else {
                value = value * 10 + (input - 48);
            }
        }
    }
}