import java.util.Arrays;
class Solution {
    int len, answer=0, brr[];
    public int solution(int[] arr) {
        len = arr.length;
        while(true){
            brr = arr.clone();
            for(int i=0; i<len; i++)
                if(arr[i]>=50 && arr[i]%2==0) arr[i]/=2;
                else if(arr[i]<50 && arr[i]%2==1) arr[i] = arr[i]*2+1;
            if( Arrays.equals(arr,brr)) return answer;
            answer++;
        }
    }
}