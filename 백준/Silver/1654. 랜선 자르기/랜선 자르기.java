// https://github.com/KimYongJ/algorithm
class Main{
    public static void main(String[] args)throws Exception{
        int k = read();
        int n = read();
        int arr[] = new int[k];
        long right=0,  // 가장 오른쪽 값
             left = 1, // 가장 왼쪽 값
             mid;      // 중간값
        for(int i=0; i<k; i++){
            arr[i] = read();
            if(right<arr[i]) right = arr[i];
        }
        
        while(left<=right){ // 왼쪽이 오른쪽을 넘을 때까지 반복
            mid = (right-left>>1)+left;// 가운데 값을 구해준다.
            
            long sum = 0;
            for(int a : arr){
                sum += a/mid; // 랜선의 갯수를 구해준다.
            }
            if(sum<n){ // 랜선이 부족할 때 
                right = mid-1;// 오른쪽 값을 더 줄여 랜선을 더 많이 만들 수 있게 한다.
            }else{ // 구한 랜선이답이거나 더 많을 때 
                left = mid+1;// 왼쪽 값을 증가시켜 mid값을 더 크게 만든다.
            }
        }
        System.out.println(left-1);
    }
    private static int read() throws Exception {
        int value = 0;
        int input;
        while (true) {
            input = System.in.read();
            if (input == ' ' || input == '\n') {
                return value;
            } else {
                value = value * 10 + (input - 48);
            }
        }
    }
}