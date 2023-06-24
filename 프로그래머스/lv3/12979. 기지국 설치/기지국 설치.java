// https://github.com/KimYongJ
class Solution {
    static int result;
    public int solution(int n, int[] stations, int w) {
        int start =1;  // 시작
        int spread = w*2+1; // 전파의 총 범위
        for(int i=0; i<stations.length; i++){
            find(start,stations[i]-w-1,spread);
            start = stations[i]+w+1;
        }
        find(start,n,spread);
        return result;
    }
    public void find(int start, int end,int spread){
        int len = end - start + 1;
        if(len<=0) return;
        result += (len/spread) + (len%spread !=0 ? 1 : 0);
    }
}