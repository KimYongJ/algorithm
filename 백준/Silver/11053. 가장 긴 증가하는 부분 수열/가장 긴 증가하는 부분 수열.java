//https://github.com/KimYongJ/algorithm
class Main{
    public static void main(String[] args)throws Exception{
        int n = read();
        int[] arr = new int[n+1];// 입력되는 값을 담을 배열
        int[] dp = new int[n+1];// 인덱스 마다의 최대길이를 dp로 담는다.
        int max = 0;
        
        for(int i=1; i<=n; i++)
            arr[i] = read();
        
        
        for(int i=1; i<=n; i++){
            // getIndex 함수 : arr안에서 인덱스가 i인 값보다 저장된 값이 작으면서 dp값이 최대인 것을 찾는다.
            dp[i] = dp[ getIndex(arr,dp,i) ] + 1;
            if(max<dp[i])
                max = dp[i];
        }
        System.out.println(max);
    }
    public static int getIndex(int[]arr,int[] dp ,int i){
        int idx = 0;
        int max = 0;
        for(int j=0; j<i; j++){
           if(arr[i]>arr[j] && dp[j]>max){
               max = dp[j];
               idx = j;
           }
        }
        return idx;
    }
    static int read() throws Exception {
		int c, n = System.in.read() & 15;
		while ((c = System.in.read()) > 32)
			n = (n << 3) + (n << 1) + (c & 15);
		if (c == 13)
			System.in.read();
		return n;
	}
    
}