// https://github.com/KimYongJ/algorithm
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

class Main{
	public static int white,blue;
    public static void main(String[] args)throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        
        int n = Integer.parseInt(br.readLine());
        int[][] arr = new int[n+1][n+1];
        
        for(int i=0; i<n; i++) {
        	StringTokenizer st = new StringTokenizer(br.readLine());
        	for(int j=0; j<n; j++) {
        		arr[i][j] = Integer.parseInt(st.nextToken());
        	}
        }
        
        DivdeAndConquer(arr,n,0,0);
        
        System.out.println(white+"\n"+blue);
    }
    /*
     * 모두 같은지 체크 후 다르다면 1~4분면으로 나눠서 재귀적으로 함수 실행
     * */
    public static void DivdeAndConquer(int[][] arr,int len,int x,int y) {
    	if(allSame(arr,len,x,y)) {
    		return;
    	}else {
    		len /= 2;
    		DivdeAndConquer(arr,len,x,y+len);		// 1사분면
    		DivdeAndConquer(arr,len,x,y); 			// 2사분면
    		DivdeAndConquer(arr,len,x+len,y);		// 3사분면
    		DivdeAndConquer(arr,len,x+len,y+len);	// 4사분면
    	}
    }
    /*
     * 해당 좌표의 값이 모두 같은지 체크하는 함수
     * */
    public static boolean allSame(int[][] arr,int len,int x,int y) {
    	int color = arr[x][y];
    	for(int i=x; i<x+len; i++) {
    		for(int j=y; j<y+len; j++) {
    			if(color != arr[i][j])
    				return false;
    		}
    	}
    	if(color==0) {
    		white++;
    	}else {
    		blue++;
    	}
    	return true;
    }
}
