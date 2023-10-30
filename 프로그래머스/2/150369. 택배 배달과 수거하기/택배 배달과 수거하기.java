//https://github.com/KimYongJ/algorithm
class Solution {
    public long solution(int cap, int n, int[] d, int[] p) {
        long answer = 0;
        for(int i=n-1; i>=0; i--){
            if(d[i]!=0 || p[i]!=0){
                answer += (i+1)*2;
                minus(d,i,cap);
                minus(p,i,cap);
                if(d[i]!=0 || p[i]!=0) i++;
            }
        }
        return answer;   
    }
    public void minus(int[] arr, int idx,int cap){
        while(idx>-1){
            if(arr[idx]>=cap){
                arr[idx] -= cap;
                break;
            }else{
                cap -= arr[idx];
                arr[idx] = 0;
            }
            idx--;
        }
    }
}