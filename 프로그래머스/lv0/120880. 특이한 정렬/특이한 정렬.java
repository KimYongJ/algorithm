class Solution {
    public int[] solution(int[] numlist, int n){
        int l = numlist.length, cnt = 0, z = 1;
        boolean[] count = new boolean[10001];
        for(int x : numlist)
            count[x]=true;
        if(count[n]) numlist[cnt++] = n;
        while(cnt<l){
            if(n+z<10001)
                if(count[n+z])  numlist[cnt++]=n+z;
            if(n-z>=0)
                if(count[n-z])  numlist[cnt++]=n-z;
            z++;
        }  
        return numlist;
    }
}