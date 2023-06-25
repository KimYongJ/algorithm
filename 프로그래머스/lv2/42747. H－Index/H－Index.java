import java.util.Arrays;
class Solution {
    public int solution(int[] arr) {
        Arrays.sort(arr);
        int max = arr[arr.length-1];
        int[] cnt = new int[max+1];
        for(int a : arr){
            cnt[a]++; // 카운팅 정렬, idx번 이상 인용된 논문의 갯수
        }
        for(int h=max-1; h>=0; h--){
            cnt[h] += cnt[max]; //cnt[i] 값을 dp처럼사용해서 인용 횟수에 따른 논문 갯수를 cnt[i]안에 저장
            max = h;
            if(h<=cnt[h] && arr.length-cnt[h] <=h)
                return h;
        }
        return 0;
    }
}
