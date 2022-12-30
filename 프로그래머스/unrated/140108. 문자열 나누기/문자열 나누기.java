class Solution {
    public int solution(String s) {
        char[] cl = s.toCharArray();
        int cnt=0,dum=1, l= cl.length;
        char comp=cl[0];
        
        for(int i=1; i<l; i++){
            if(comp==cl[i]){
                dum++;
            }else{
                dum--;
            }
            if(dum==0){
                cnt++;
                if(i<l-1){
                    comp=cl[i+1];
                }
            }
        }
        if(dum!=0)
            cnt++;
        
        return cnt;
    }
}