import java.io.*;
import java.util.*;

class Solution
{
    public static StringBuilder sb = new StringBuilder();
    public static StringTokenizer st;
    public static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    public static short[] arr;
	public static void main(String args[]) throws Exception
	{
        int l = Integer.parseInt(br.readLine());
        for(int i=1; i<l+1; i++){ // 큰 틀에서 반복하는 것
            long sum = 0;
            insertArray(); // 배열에 값을 셋팅하는 함수
            sum += sumFucntion(); // 알고리즘의 핵심 부분
            sb.append("#").append(i).append(" ").append(sum).append("\n");// 값 넣어주는 코드작성
        } // 큰틀
        System.out.println(sb);
	}
    public static long sumFucntion(){ // 알고리즘의 핵심 , 배열을 뒤에서부터 체크하면서 더해온다.
        long sum = 0;
        int idx = arr.length-1;
        for(int i=arr.length-2; i>=0; i--){
            if(arr[idx]>arr[i])
                sum += arr[idx] - arr[i];
            else
                idx = i;
        }
        return sum;
    }
    public static void insertArray() throws Exception {// 배열에 값을 셋팅하는 함수
        int l = Integer.parseInt(br.readLine());
        arr = new short[l];
        st = new StringTokenizer(br.readLine());
        for(int i=0; i<l; i++){
            arr[i] = Short.parseShort(st.nextToken());
        }
    }
}