class Solution {
    public int solution(int[] a) {
        int rule =0;
        if(a[1]-a[0]==a[2]-a[1]){
            // 등차수열
            rule = a[1]-a[0];
            return a[a.length-1]+rule;
        }else{
            // 등비수열
            rule = a[1]/a[0];
            return a[a.length-1]*rule;
        }
    }
}