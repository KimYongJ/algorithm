class Solution {
    public int solution(int[] arr) {
        int[] cnt = new int[10001];
        int max = 0;
        for(int a : arr){
            if(max<a) max = a;
            cnt[a]++; // 카운팅 정렬, idx번 이상 인용된 논문의 갯수
        }
        int before = max;
        for(int h=max-1; h>=0; h--){
            cnt[h] += cnt[before]; //cnt[i] 값을 dp처럼사용해서 인용 횟수에 따른 논문 갯수를 cnt[i]안에 저장
            before = h;
            if(h<=cnt[h] && arr.length-cnt[h] <=h)
                return h;
        }
        return 0;
    }
}
