// https://github.com/KimYongJ/algorithm
class Main{

    public static void main(String[] args)throws Exception{
    	int n = read();
    	int total = read();
    	int[] arr = new int[n];
    	int left = 0, right = 0, mid = 0;
    	for(int i=0; i<n; i++){
            if(right<(arr[i] = read()))
            	right = arr[i];
    	}

        while(left<right){
        	mid = (left+right)/2;
            long sum = 0;
            for(int a: arr){
                if(a-mid>0)
                    sum += a-mid;
                
                if(total<=sum){ // 자른 나무의 갯수가 구하려는 값보다 크거나 같다면 left값을 증가시킨다.
                    left = mid+1;
                    continue;
                }
            }
            if(sum<total)// 자른 나무의 갯수가 구하려는 값보다 작으면 right값을 감소시킨다.
                right = mid;
            
        }
        System.out.println(left-1);
    }
 	private static int read() throws Exception {
		int c, n = System.in.read() & 15;
		while ((c = System.in.read()) > 32)
			n = (n << 3) + (n << 1) + (c & 15);
		return n;
	}
}