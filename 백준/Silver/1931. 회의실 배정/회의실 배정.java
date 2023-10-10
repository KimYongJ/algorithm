// https://github.com/KimYongJ/algorithm
import java.util.Arrays;
class Main{
    public static void main(String[] args)throws Exception{
        int N = read();
        int[][] arr = new int[N][2];
        for(int i=0; i<N; i++){
            arr[i][0] = read();
            arr[i][1] = read();
        }
        /*
         * 회의 종료 시간이 가장 빠른 것을 앞으로 한다. 
         * 회의 종료 시간이 같다면 회의 시작시간이 빠른 것을 앞으로 한다. 
         * */
        Arrays.sort(arr,(a,b)->{
            if(a[1]==b[1]){
                return a[0]-b[0];
            }
            return a[1]-b[1];
        });
        
        /*
         * 반복문을 돌며 회의 종료시간과 같거나 큰 바로 다음 회의 시작시간을 찾는다. 그 후 endtime을 갱신해준다.
         * */
        int result = 1, endtime = arr[0][1];
        
        for(int i=1; i<N; i++){
            if(arr[i][0]>=endtime){
                result++;
                endtime = arr[i][1];
            }
        }
        
        System.out.println(result);
    }
    private static int read() throws Exception {
        int c, n = System.in.read() & 15;
        while((c = System.in.read()) > 32) {
            n = (n << 3) + (n << 1) + (c & 15);
        }
        return n;
    }
}